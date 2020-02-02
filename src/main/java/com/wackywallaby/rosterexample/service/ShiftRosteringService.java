package com.wackywallaby.rosterexample.service;

import com.wackywallaby.rosterexample.request.ShiftRequest;
import com.wackywallaby.rosterexample.response.EmployeeDetails;
import com.wackywallaby.rosterexample.response.RosteredShiftResponse;

import java.time.LocalDateTime;

public interface ShiftRosteringService {

    EmployeeDetails availableEmployeeDetails(String roleName, String areaName, LocalDateTime startTime, LocalDateTime endTime);

    RosteredShiftResponse create(ShiftRequest shiftRequest);

}
