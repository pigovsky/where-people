package com.wherepeople.spring.mvc.service;

import com.wherepeople.spring.mvc.model.location.Location;

import java.util.List;

/**
 * Service providing means to add and read user locations
 */
public interface LocationService {
    Location createLocation(Location location) throws Exception;

    List<Location> getLocationsAfter(long time);
}
