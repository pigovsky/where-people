package com.wherepeople.spring.mvc;

import com.wherepeople.spring.mvc.model.ApiConstants;
import com.wherepeople.spring.mvc.model.location.Location;
import com.wherepeople.spring.mvc.model.person.RequestResult;
import com.wherepeople.spring.mvc.service.LocationService;
import com.wherepeople.spring.mvc.util.WebServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles request of creating and listing locations
 */
@Controller
@RequestMapping("/api")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @RequestMapping("/locations")
    public @ResponseBody
    String locations(){
        return WebServiceUtil.GSON.toJson(locationService.getAllLocations());
    }

    @RequestMapping(value = "/location", method = RequestMethod.POST, consumes = ApiConstants.APPLICATION_JSON,
            produces = ApiConstants.APPLICATION_JSON)
    public @ResponseBody String location(@RequestBody String locationJson){
        try {
            Location location = WebServiceUtil.GSON.fromJson(locationJson, Location.class);
            return WebServiceUtil.GSON.toJson(locationService.createLocation(location));
        } catch (Exception e){
            e.printStackTrace();
            return new RequestResult(e.getMessage()).toString();
        }
    }
}
