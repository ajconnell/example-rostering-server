package com.wackywallaby.rosterexample.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "role_qualifications")
@IdClass(RoleQualification.class)
public class RoleQualification extends RosterEntity {

    @Id
    @NotNull
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @ManyToOne
    private Role role;

    @Id
    @NotNull
    @JoinColumn(name = "qualification_id", referencedColumnName = "id")
    @ManyToOne
    private Qualification qualification;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoleQualification that = (RoleQualification) o;
        return role.equals(that.role) && qualification.equals(that.qualification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, qualification);
    }
}
