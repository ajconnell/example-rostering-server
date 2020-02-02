package com.wackywallaby.rosterexample.repository;

import com.wackywallaby.rosterexample.model.Role;
import com.wackywallaby.rosterexample.model.RoleQualification;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public interface RoleQualificationsRepository extends PagingAndSortingRepository<RoleQualification, BigInteger> {

    List<RoleQualification> findAllByRole(Role role);

}
