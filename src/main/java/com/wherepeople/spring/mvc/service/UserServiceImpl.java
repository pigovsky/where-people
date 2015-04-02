package com.wherepeople.spring.mvc.service;

import com.wherepeople.spring.mvc.model.login.AccessToken;
import com.wherepeople.spring.mvc.model.person.Person;
import com.wherepeople.spring.mvc.repository.AccessTokenRepository;
import com.wherepeople.spring.mvc.repository.PersonRepository;
import com.wherepeople.spring.mvc.util.WebServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.Calendar;
import java.util.List;

/**
 * Created by yuriy on 02.04.15.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AccessTokenRepository accessTokenRepository;

    @Override
    public List<Person> getAllUsers() {
        return personRepository.findAll();
    }

    @Override
    public Person createUser(Person person) throws Exception {
        if (personRepository.findOneByUsername(person.getUsername()) != null){
            throw new Exception(String.format("Username %s is already in use", person.getUsername()));
        }
        return personRepository.save(person);
    }

    @Override
    public AccessToken login(Person person) throws Exception {
        if (WebServiceUtil.isEmptyOrNull(person.getUsername()) || WebServiceUtil.isEmptyOrNull(person.getPassword())){
            throw new Exception("Username or password is empty");
        }
        if (personRepository.findOneByUsernameAndPassword(person.getUsername(), person.getPassword()) != null) {
            AccessToken accessToken = new AccessToken();
            accessToken.setUsername(person.getUsername());
            byte[] md5s = MessageDigest.getInstance("MD5").digest((person.getUsername() + person.getPassword() + Calendar.getInstance().getTime().getTime()).getBytes());
            accessToken.setAccessToken(bytesToString(md5s));
            accessTokenRepository.save(accessToken);
            return accessToken;
        } else {
            throw new Exception("Incorrect login/password");
        }
    }

    private static String bytesToString(byte[] arrary){
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : arrary){
            stringBuilder.append(String.format("%02X",b));
        }
        return stringBuilder.toString();
    }
}
