package com.wherepeople.spring.mvc.service;

import com.wherepeople.spring.mvc.model.location.Location;
import com.wherepeople.spring.mvc.model.location.LocationWithTimestamp;
import com.wherepeople.spring.mvc.model.login.AccessToken;
import com.wherepeople.spring.mvc.repository.AccessTokenRepository;
import com.wherepeople.spring.mvc.repository.LocationHistoryRepository;
import com.wherepeople.spring.mvc.repository.LocationRepository;
import com.wherepeople.spring.mvc.util.WebServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * Created by yuriy on 02.04.15.
 */
@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    AccessTokenRepository accessTokenRepository;

    @Autowired
    LocationHistoryRepository locationHistoryRepository;

    @Autowired
    LocationRepository locationRepository;

    @Override
    public LocationWithTimestamp createLocation(Location location, AccessToken accessToken) throws Exception {
        if (WebServiceUtil.isEmptyOrNull(accessToken.getAccessToken())){
            throw new Exception("Access token is empty");
        }
        AccessToken accessTokenWithUsername = accessTokenRepository.findOneByAccessToken(accessToken.getAccessToken());
        if (accessTokenWithUsername != null) {

            location.setUsername(accessTokenWithUsername.getUsername());
            locationRepository.save(location);
            LocationWithTimestamp locationWithTimestamp = new LocationWithTimestamp(location);
            locationHistoryRepository.save(locationWithTimestamp);
            return locationWithTimestamp;
        } else {
            throw new Exception("Invalid access token");
        }
    }

    @Override
    public List<LocationWithTimestamp> getUserLocationsAfter(String username, long time) {
        return locationHistoryRepository.findByUsernameAndDateTimeGreaterThan(username, time);
        //return locationRepository.findAll();
    }

    @Override
    public List<Location> getLastLocationsOfAllUsers() {
        return locationRepository.findAll();
    }
}
