package com.wackywallaby.rosterexample.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "roster_allowances")
public class RosterAllowance extends RosterEntity {

    @Id
    @NotNull
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @NotNull
    @Column(name = "allowance")
    private BigDecimal allowance;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public BigDecimal getAllowance() {
        return allowance;
    }

    public void setAllowance(BigDecimal allowance) {
        this.allowance = allowance;
    }

}
