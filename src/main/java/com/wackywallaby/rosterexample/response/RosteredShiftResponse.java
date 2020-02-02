package com.wackywallaby.rosterexample.response;

import com.wackywallaby.rosterexample.model.Shift;
import org.springframework.http.HttpStatus;

public class RosteredShiftResponse {

    private Shift shift;
    private HttpStatus httpStatus;

    public RosteredShiftResponse(Shift shift, HttpStatus httpStatus) {
        this.shift = shift;
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Shift getShift() {
        return shift;
    }

}
