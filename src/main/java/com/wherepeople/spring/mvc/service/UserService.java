package com.wherepeople.spring.mvc.service;

import com.wherepeople.spring.mvc.model.location.Location;
import com.wherepeople.spring.mvc.model.login.AccessToken;
import com.wherepeople.spring.mvc.model.person.Person;

import java.util.List;

/**
 * Handles requests of registering and
 * logining users
 */
public interface UserService {
    List<Person> getAllUsers();

    Person createUser(Person person) throws Exception;

    AccessToken login(Person person) throws Exception;
}
