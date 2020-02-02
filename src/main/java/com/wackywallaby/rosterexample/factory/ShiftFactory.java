package com.wackywallaby.rosterexample.factory;

import com.wackywallaby.rosterexample.model.Shift;
import com.wackywallaby.rosterexample.request.ShiftRequest;

public interface ShiftFactory {

    Shift create(ShiftRequest shiftRequest);

}
