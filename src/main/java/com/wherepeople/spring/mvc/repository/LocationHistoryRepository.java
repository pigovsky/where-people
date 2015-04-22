package com.wherepeople.spring.mvc.repository;

import com.wherepeople.spring.mvc.model.location.LocationWithTimestamp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by yuriy on 24.03.15.
 */
public interface LocationHistoryRepository extends JpaRepository<LocationWithTimestamp, Long> {

    List<LocationWithTimestamp> findByUsername(String username);

    //@Query("select location from location where location.dateTime > :time")
    //List<Location> findLocationsAfter(@Param("time") long time);

    List<LocationWithTimestamp> findByUsernameAndDateTimeGreaterThan(String username, long time);

}
