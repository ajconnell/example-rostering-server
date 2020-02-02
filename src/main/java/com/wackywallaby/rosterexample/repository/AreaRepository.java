package com.wackywallaby.rosterexample.repository;

import com.wackywallaby.rosterexample.model.Area;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public interface AreaRepository extends PagingAndSortingRepository<Area, BigInteger> {

    Area findByName(String name);

}
