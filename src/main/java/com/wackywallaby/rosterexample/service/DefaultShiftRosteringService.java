package com.wackywallaby.rosterexample.service;

import com.wackywallaby.rosterexample.factory.ShiftFactory;
import com.wackywallaby.rosterexample.model.*;
import com.wackywallaby.rosterexample.repository.*;
import com.wackywallaby.rosterexample.request.ShiftRequest;
import com.wackywallaby.rosterexample.response.EmployeeDetails;
import com.wackywallaby.rosterexample.response.RosteredShiftResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

@Service
public class DefaultShiftRosteringService implements ShiftRosteringService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private RegionalAreaRepository regionalAreaRepository;

    @Autowired
    private RoleQualificationsRepository roleQualificationsRepository;

    @Autowired
    private ShiftRepository shiftRepository;

    @Autowired
    private ShiftFactory shiftFactory;

    @Override
    public EmployeeDetails availableEmployeeDetails(String roleName, String areaName, LocalDateTime startTime, LocalDateTime endTime) {
        Role role = roleRepository.findByName(roleName);
        List<Qualification> roleQualifications =
            roleQualificationsRepository.findAllByRole(role).stream().map(RoleQualification::getQualification).collect(Collectors.toList());

        Area area = areaRepository.findByName(areaName);
        List<Employee> allStaffInArea = employeeRepository.findAllByArea(area);
        List<Employee> bestMatches = allStaffInArea.stream().filter(employee -> employee.getRole().equals(role)).collect(Collectors.toList());

        List<Employee> otherLocalStaff = allStaffInArea.stream().filter(employee -> !employee.getRole().equals(role)).collect(Collectors.toList());
        List<Employee> otherStaff =
            Stream.of(otherLocalStaff, getOtherStaffWithinRegion(area)).flatMap(Collection::stream).collect(Collectors.toList());

        List<Long> employeeIds = Stream.of(bestMatches, otherStaff).flatMap(Collection::stream).map(Employee::getId).collect(Collectors.toList());
        List<Shift> shifts = shiftRepository.findShiftsForEmployeeIdsWithinTimePeriod(employeeIds, startTime, endTime);

        return new EmployeeDetails(filterSortEmployees(shifts, role, roleQualifications, bestMatches),
                                   filterSortEmployees(shifts, role, roleQualifications, otherStaff));
    }

    @Transactional
    @Override
    public RosteredShiftResponse create(ShiftRequest shiftRequest) {
        Long employeeId = shiftRequest.getEmployeeId();
        List<Shift> existingShifts = shiftRepository.findShiftsForEmployeeIdsWithinTimePeriod(newArrayList(employeeId), shiftRequest.getStartTime(),
                                                                                              shiftRequest.getEndTime());
        if (!existingShifts.isEmpty()) {
            return new RosteredShiftResponse(null, HttpStatus.CONFLICT);
        }

        Shift shift = shiftRepository.save(shiftFactory.create(shiftRequest));
        return new RosteredShiftResponse(shift, HttpStatus.OK);
    }

    private List<Employee> getOtherStaffWithinRegion(Area area) {
        RegionalArea regionalArea = regionalAreaRepository.findByArea(area);
        List<RegionalArea> regionalAreas = regionalAreaRepository.findAllByRegionAndAreaIsNot(regionalArea.getRegion(), regionalArea.getArea());
        List<Area> otherAreas = regionalAreas.stream().map(RegionalArea::getArea).collect(Collectors.toList());
        return employeeRepository.findAllByAreaIsIn(otherAreas);
    }


    private List<Employee> filterSortEmployees(List<Shift> shifts, Role role, List<Qualification> roleQualifications, List<Employee> employees) {
        Map<Employee, List<Shift>> employeeToExistingShiftsMap = shifts.stream().collect(Collectors.groupingBy(Shift::getEmployee));

        Map<Employee, Integer> employeeToProficiencyLevelMap = newHashMap();
        for (Employee employee : employees) {
            for (EmployeeRole employeeRole : employee.getEmployeeRoles()) {
                if (employeeRole.getRole().equals(role)) {
                    employeeToProficiencyLevelMap.put(employee, employeeRole.getProficiency().bestMatchValue());
                }
            }
        }
        return employees.stream()
                        .filter(employee -> !employeeToExistingShiftsMap.containsKey(employee) && employee.getEmployeeQualifications()
                                                                                                          .stream()
                                                                                                          .filter(employeeQualification ->
                                                                                                                      roleQualifications.contains(
                                                                                                                          employeeQualification.getQualification()) &&
                                                                                                                      employeeQualification.getQualificationStatus()
                                                                                                                                           .equals(
                                                                                                                                               QualificationStatus.ACTIVE))
                                                                                                          .count() == roleQualifications.size())
                        .sorted((employee1, employee2) -> employeeToProficiencyLevelMap.get(employee2)
                                                                                       .compareTo(employeeToProficiencyLevelMap.get(employee1)))
                        .collect(Collectors.toList());
    }

}
