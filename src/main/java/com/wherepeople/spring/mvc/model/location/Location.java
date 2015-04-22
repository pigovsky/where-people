package com.wherepeople.spring.mvc.model.location;

import javax.persistence.*;

/**
 * Created by yuriy on 22.04.15.
 */
@Entity(name = "last_location")
public class Location {
    @Id
    private String username;

    @Basic
    private Double longitude;

    @Basic
    private Double latitude;


    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
