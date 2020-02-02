package com.wackywallaby.rosterexample.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "employee_qualifications")
public class EmployeeQualification extends RosterEntity {

    @Id
    @NotNull
    @Column(name = "employee_id")
    private Long employeeId;

    @Id
    @NotNull
    @JoinColumn(name = "qualification_id", referencedColumnName = "id")
    @ManyToOne
    private Qualification qualification;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "status")
    private QualificationStatus qualificationStatus;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public QualificationStatus getQualificationStatus() {
        return qualificationStatus;
    }

    public void setQualificationStatus(QualificationStatus qualificationStatus) {
        this.qualificationStatus = qualificationStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EmployeeQualification that = (EmployeeQualification) o;
        return employeeId.equals(that.employeeId) && qualification.getId().equals(that.qualification.getId()) &&
               qualificationStatus == that.qualificationStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, qualification.getId(), qualificationStatus);
    }
}
