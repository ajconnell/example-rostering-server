package com.wackywallaby.rosterexample.repository;

import com.wackywallaby.rosterexample.model.Shift;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public interface ShiftRepository extends PagingAndSortingRepository<Shift, BigInteger> {

    @Query("SELECT shift FROM Shift shift WHERE shift.employeeId IN (:employeeIds) " +
           "AND ((shift.startTime <= :startTime AND shift.endTime >= :startTime) " +
           "OR (shift.startTime >= :startTime AND shift.startTime <= :endTime)" + "OR (shift.endTime >= :startTime AND shift.endTime <= :endTime))")
    List<Shift> findShiftsForEmployeeIdsWithinTimePeriod(@Param("employeeIds") List<Long> employeeIds, @Param("startTime") LocalDateTime startTime,
                                                         @Param("endTime") LocalDateTime endTime);

}
