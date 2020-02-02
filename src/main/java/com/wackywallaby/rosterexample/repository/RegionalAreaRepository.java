package com.wackywallaby.rosterexample.repository;

import com.wackywallaby.rosterexample.model.Area;
import com.wackywallaby.rosterexample.model.Region;
import com.wackywallaby.rosterexample.model.RegionalArea;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public interface RegionalAreaRepository extends PagingAndSortingRepository<RegionalArea, BigInteger> {

    RegionalArea findByArea(Area area);

    List<RegionalArea> findAllByRegionAndAreaIsNot(Region region, Area excludedArea);

}
