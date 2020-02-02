package com.wackywallaby.rosterexample.controller;

import com.wackywallaby.rosterexample.request.ShiftRequest;
import com.wackywallaby.rosterexample.response.RosteredShiftResponse;
import com.wackywallaby.rosterexample.service.ShiftRosteringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shifts")
public class ShiftsController {

    @Autowired
    private ShiftRosteringService shiftRosteringService;

    @PostMapping("/create")
    public ResponseEntity<RosteredShiftResponse> getQualifiedStaffForShift(@RequestBody ShiftRequest shiftRequest) {
        if (shiftRequest.getEndTime().isBefore(shiftRequest.getStartTime())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        RosteredShiftResponse rosteredShiftResponse = shiftRosteringService.create(shiftRequest);
        return new ResponseEntity<>(rosteredShiftResponse, rosteredShiftResponse.getHttpStatus());
    }

}
