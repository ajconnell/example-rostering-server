package com.wackywallaby.rosterexample.controller;

import com.wackywallaby.rosterexample.response.EmployeeDetails;
import com.wackywallaby.rosterexample.service.ShiftRosteringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    @Autowired
    private ShiftRosteringService shiftRosteringService;

    @GetMapping("/available")
    public ResponseEntity<EmployeeDetails> getQualifiedStaffForShift(@RequestParam("role") String roleName, @RequestParam("area") String areaName,
                                                                     @RequestParam("start_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                         LocalDateTime startTime,
                                                                     @RequestParam("end_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                         LocalDateTime endTime) {
        if (endTime.isBefore(startTime)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(shiftRosteringService.availableEmployeeDetails(roleName, areaName, startTime, endTime), HttpStatus.OK);
    }

}
