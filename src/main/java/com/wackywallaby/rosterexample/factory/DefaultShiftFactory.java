package com.wackywallaby.rosterexample.factory;

import com.wackywallaby.rosterexample.model.Shift;
import com.wackywallaby.rosterexample.request.ShiftRequest;
import org.springframework.stereotype.Component;

@Component
public class DefaultShiftFactory implements ShiftFactory {

    public Shift create(ShiftRequest shiftRequest) {
        Shift shift = new Shift();

        shift.setAreaId(shiftRequest.getAreaId());
        shift.setRoleId(shiftRequest.getRoleId());
        shift.setEmployeeId(shiftRequest.getEmployeeId());

        shift.setStartTime(shiftRequest.getStartTime());
        shift.setEndTime(shiftRequest.getEndTime());

        shift.setNotified(shiftRequest.isNotified());
        shift.setConfirmed(shiftRequest.isConfirmed());
        shift.setBreakDetail(shiftRequest.getBreakDetail());

        return shift;
    }

}
