package com.wackywallaby.rosterexample.repository;

import com.wackywallaby.rosterexample.model.Area;
import com.wackywallaby.rosterexample.model.Employee;
import com.wackywallaby.rosterexample.model.Role;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, BigInteger> {

    Employee findById(Long id);

    List<Employee> findAllByArea(Area area);

    List<Employee> findAllByRoleAndArea(Role role, Area area);

    List<Employee> findAllByAreaIsIn(List<Area> area);

}
