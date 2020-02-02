package com.wackywallaby.rosterexample.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee extends RosterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    @OneToOne
    private Area area;

    @NotNull
    @JoinColumn(name = "profile_picture_id", referencedColumnName = "id")
    @OneToOne
    private ProfilePicture picture;

    @NotNull
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @OneToOne
    private Role role;

    @NotEmpty
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "employeeId", cascade = CascadeType.ALL)
    private Set<EmployeeQualification> employeeQualifications;

    @NotEmpty
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "employeeId", cascade = CascadeType.ALL)
    private Set<EmployeeRole> employeeRoles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public ProfilePicture getPicture() {
        return picture;
    }

    public void setPicture(ProfilePicture picture) {
        this.picture = picture;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<EmployeeQualification> getEmployeeQualifications() {
        return employeeQualifications;
    }

    public void setEmployeeQualifications(Set<EmployeeQualification> employeeQualifications) {
        this.employeeQualifications = employeeQualifications;
    }

    public Set<EmployeeRole> getEmployeeRoles() {
        return employeeRoles;
    }

    public void setEmployeeRoles(Set<EmployeeRole> employeeRoles) {
        this.employeeRoles = employeeRoles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return id.equals(employee.id) && name.equals(employee.name) && area.equals(employee.area) && picture.equals(employee.picture) &&
               role.equals(employee.role) && employeeQualifications.equals(employee.employeeQualifications) &&
               employeeRoles.equals(employee.employeeRoles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, area, picture, role, employeeQualifications, employeeRoles);
    }
}
