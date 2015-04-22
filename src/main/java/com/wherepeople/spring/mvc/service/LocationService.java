package com.wherepeople.spring.mvc.service;

import com.wherepeople.spring.mvc.model.location.Location;
import com.wherepeople.spring.mvc.model.location.LocationWithTimestamp;
import com.wherepeople.spring.mvc.model.login.AccessToken;

import java.util.List;

/**
 * Service providing means to add and read user locations
 */
public interface LocationService {
    LocationWithTimestamp createLocation(Location location, AccessToken accessToken) throws Exception;

    List<LocationWithTimestamp> getUserLocationsAfter(String username, long time);

    List<Location> getLastLocationsOfAllUsers();
}
