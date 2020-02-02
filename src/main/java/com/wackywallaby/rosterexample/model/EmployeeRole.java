package com.wackywallaby.rosterexample.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "employee_roles")
public class EmployeeRole extends RosterEntity {

    @Id
    @NotNull
    @Column(name = "employee_id")
    private Long employeeId;

    @Id
    @NotNull
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @ManyToOne
    private Role role;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "proficiency")
    private Proficiency proficiency;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Proficiency getProficiency() {
        return proficiency;
    }

    public void setProficiency(Proficiency proficiency) {
        this.proficiency = proficiency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EmployeeRole that = (EmployeeRole) o;
        return employeeId.equals(that.employeeId) && role.getId().equals(that.role.getId()) && proficiency == that.proficiency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, role.getId(), proficiency);
    }
}
