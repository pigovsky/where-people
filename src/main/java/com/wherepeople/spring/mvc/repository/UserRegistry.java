package com.wherepeople.spring.mvc.repository;

import com.wherepeople.spring.mvc.model.person.GaeUser;

public interface UserRegistry {
    GaeUser findUser(String userId);
    void registerUser(GaeUser newUser);
    void removeUser(String userId);
}