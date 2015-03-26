package com.wherepeople.spring.mvc.model.person;

import com.wherepeople.spring.mvc.model.AppRole;

import java.io.Serializable;
import java.util.Set;

public class GaeUser implements Serializable {
    private String userId;
    private String email;
    private String nickname;
    private String forename;
    private String surname;
    private Set<AppRole> authorities;
    private boolean enabled;

    public GaeUser(String userId, String nickname, String email, String forename, String surname, Set<AppRole> roles, Boolean enabled) {
        this(userId, nickname, email);
        this.forename = forename;
        this.surname = surname;
        this.authorities = roles;
        this.enabled = enabled;
    }

    public GaeUser(String userId, String nickname, String email) {
        this.userId = userId;
        this.nickname = nickname;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getForename() {
        return forename;
    }

    public String getSurname() {
        return surname;
    }

    public Set<AppRole> getAuthorities() {
        return authorities;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAuthorities(Set<AppRole> authorities) {
        this.authorities = authorities;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}