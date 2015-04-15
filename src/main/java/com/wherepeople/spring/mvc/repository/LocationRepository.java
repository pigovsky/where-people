package com.wherepeople.spring.mvc.repository;

import com.wherepeople.spring.mvc.model.location.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by yuriy on 24.03.15.
 */
public interface LocationRepository extends JpaRepository<Location, Long> {

    List<Location> findByUsername(String username);

    //@Query("select location from location where location.dateTime > :time")
    //List<Location> findLocationsAfter(@Param("time") long time);

    List<Location> findByDateTimeGreaterThan(long time);

}
