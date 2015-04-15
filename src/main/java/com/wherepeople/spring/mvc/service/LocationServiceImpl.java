package com.wherepeople.spring.mvc.service;

import com.wherepeople.spring.mvc.model.location.Location;
import com.wherepeople.spring.mvc.model.login.AccessToken;
import com.wherepeople.spring.mvc.repository.AccessTokenRepository;
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
    LocationRepository locationRepository;

    @Override
    public Location createLocation(Location location) throws Exception {
        if (WebServiceUtil.isEmptyOrNull(location.getAccessToken())){
            throw new Exception("Access token is empty");
        }
        AccessToken accesstoken = accessTokenRepository.findOneByAccessToken(location.getAccessToken());
        if (accesstoken != null) {
            location.setDateTime(Calendar.getInstance().getTime().getTime());
            location.setUsername(accesstoken.getUsername());
            locationRepository.save(location);
            return location;
        } else {
            throw new Exception("Invalid access token");
        }
    }

    @Override
    public List<Location> getLocationsAfter(long time) {
        return locationRepository.findByDateTimeGreaterThan(time);
        //return locationRepository.findAll();
    }
}
