package com.wherepeople.spring.mvc.model.login;

import javax.persistence.*;

/**
 * Created by yuriy on 24.03.15.
 */
@Entity(name = "access_token")
public class AccessToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    private String accessToken;

    @Basic
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
