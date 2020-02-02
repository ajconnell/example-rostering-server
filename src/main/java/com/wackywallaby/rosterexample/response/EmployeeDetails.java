package com.wackywallaby.rosterexample.response;

import com.wackywallaby.rosterexample.model.Employee;

import java.io.Serializable;
import java.util.List;

public class EmployeeDetails implements Serializable {

    private List<Employee> bestMatches;
    private List<Employee> otherStaff;

    public EmployeeDetails(List<Employee> bestMatches, List<Employee> otherStaff) {
        this.bestMatches = bestMatches;
        this.otherStaff = otherStaff;
    }

    public List<Employee> getBestMatches() {
        return bestMatches;
    }

    public void setBestMatches(List<Employee> bestMatches) {
        this.bestMatches = bestMatches;
    }

    public List<Employee> getOtherStaff() {
        return otherStaff;
    }

    public void setOtherStaff(List<Employee> otherStaff) {
        this.otherStaff = otherStaff;
    }

}
