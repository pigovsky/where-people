package com.wherepeople.spring.mvc.model.location;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by yuriy on 24.03.15.
 */
@Entity(name = "location")
public class LocationWithTimestamp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    private Double longitude;

    @Basic
    private Double latitude;

    @Basic
    private String username;

    @Basic
    private Long dateTime;

    public LocationWithTimestamp(){}

    public LocationWithTimestamp(Location location){
        setLongitude(location.getLongitude());
        setLatitude(location.getLatitude());
        setUsername(location.getUsername());
        setDateTime(Calendar.getInstance().getTime().getTime());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getDateTime() {
        return dateTime;
    }

    public void setDateTime(Long dateTime) {
        this.dateTime = dateTime;
    }
}
