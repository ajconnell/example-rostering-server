package com.wackywallaby.rosterexample.model;

import com.wackywallaby.rosterexample.convertor.JpaLocalDateTimeConvertor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "leave")
public class Leave extends RosterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "employee_id")
    private Long employeeId;

    @NotNull
    @Column(name = "start_date")
    @Convert(converter = JpaLocalDateTimeConvertor.class)
    private LocalDateTime startDate;

    @NotNull
    @Column(name = "end_date")
    @Convert(converter = JpaLocalDateTimeConvertor.class)
    private LocalDateTime endDate;

    @NotNull
    @Column(name = "type")
    private LeaveType leaveType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

}
