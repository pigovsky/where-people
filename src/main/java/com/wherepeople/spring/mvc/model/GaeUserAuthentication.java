package com.wherepeople.spring.mvc.model;

import com.wherepeople.spring.mvc.model.person.GaeUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by yuriy on 26.03.15.
 */
public class GaeUserAuthentication implements Authentication {
    private final GaeUser user;
    private boolean authenticated;

    public GaeUserAuthentication(GaeUser user, Object details) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        this.authenticated = b;
    }

    @Override
    public String getName() {
        return user.getUserId();
    }
}
