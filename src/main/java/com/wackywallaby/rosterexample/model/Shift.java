package com.wackywallaby.rosterexample.model;

import com.wackywallaby.rosterexample.convertor.JpaLocalDateTimeConvertor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "shifts")
public class Shift extends RosterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "area_id")
    private Long areaId;

    @JoinColumn(name = "area_id", insertable = false, updatable = false, referencedColumnName = "id")
    @ManyToOne
    private Area area;

    @NotNull
    @Column(name = "role_id")
    private Long roleId;

    @JoinColumn(name = "role_id", insertable = false, updatable = false, referencedColumnName = "id")
    @ManyToOne
    private Role role;

    @NotNull
    @Column(name = "employee_id")
    private Long employeeId;

    @JoinColumn(name = "employee_id", insertable = false, updatable = false, referencedColumnName = "id")
    @ManyToOne
    private Employee employee;

    @NotNull
    @Column(name = "start_time")
    @Convert(converter = JpaLocalDateTimeConvertor.class)
    private LocalDateTime startTime;

    @NotNull
    @Column(name = "end_time")
    @Convert(converter = JpaLocalDateTimeConvertor.class)
    private LocalDateTime endTime;

    @NotNull
    @Column(name = "notified")
    private boolean notified;

    @NotNull
    @Column(name = "confirmed")
    private boolean confirmed;

    @Column(name = "break_detail")
    private String breakDetail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public boolean isNotified() {
        return notified;
    }

    public void setNotified(boolean notified) {
        this.notified = notified;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getBreakDetail() {
        return breakDetail;
    }

    public void setBreakDetail(String breakDetail) {
        this.breakDetail = breakDetail;
    }
}
