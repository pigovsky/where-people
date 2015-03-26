package com.wherepeople.spring.mvc;

import com.wherepeople.spring.mvc.model.ApiConstants;
import com.wherepeople.spring.mvc.model.location.Location;
import com.wherepeople.spring.mvc.model.login.AccessToken;
import com.wherepeople.spring.mvc.model.person.Person;
import com.wherepeople.spring.mvc.model.person.RegistrationResult;
import com.wherepeople.spring.mvc.repository.AccessTokenRepository;
import com.wherepeople.spring.mvc.repository.LocationRepository;
import com.wherepeople.spring.mvc.repository.PersonRepository;
import com.wherepeople.spring.mvc.util.WebServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sun.misc.Hashing;

import java.security.MessageDigest;
import java.util.Calendar;

@Controller
@RequestMapping("/")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AccessTokenRepository accessTokenRepository;

    @Autowired
    private LocationRepository locationRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printPeople(ModelMap model) {
		model.addAttribute("person", new Person());
        model.addAttribute("people", personRepository.findAll());
		return "people";
	}

    @RequestMapping(value = "/api/register", method = RequestMethod.POST, consumes = ApiConstants.APPLICATION_JSON,
        produces = ApiConstants.APPLICATION_JSON)
    public @ResponseBody String addPerson(@RequestBody String personJson){
        RegistrationResult registrationResult = new RegistrationResult();

        try {
            Person person = WebServiceUtil.GSON.fromJson(personJson, Person.class);
            if (WebServiceUtil.isEmptyOrNull(person.getUsername()) || WebServiceUtil.isEmptyOrNull(person.getPassword())){
                throw new Exception("Username or password is empty");
            }
            if (personRepository.findOneByUsername(person.getUsername()) != null){
                throw new Exception(String.format("Username %s is already in use", person.getUsername()));
            }
            personRepository.save(person);
            registrationResult.setSuccess(true);
            registrationResult.setMessage(String.format("User %s was successfully created", person.getUsername()));
        } catch (Exception e){
            registrationResult.setSuccess(false);
            registrationResult.setMessage(e.getMessage());
        }
        return WebServiceUtil.GSON.toJson(registrationResult);
    }

    @RequestMapping(value = "/api/login", method = RequestMethod.POST, consumes = ApiConstants.APPLICATION_JSON,
            produces = ApiConstants.APPLICATION_JSON)
    public @ResponseBody String login(@RequestBody String personJson){
        try {
            Person person = WebServiceUtil.GSON.fromJson(personJson, Person.class);
            if (WebServiceUtil.isEmptyOrNull(person.getUsername()) || WebServiceUtil.isEmptyOrNull(person.getPassword())){
                throw new Exception("Username or password is empty");
            }
            if (personRepository.findOneByUsernameAndPassword(person.getUsername(), person.getPassword()) != null) {
                AccessToken accessToken = new AccessToken();
                accessToken.setUsername(person.getUsername());
                byte[] md5s = MessageDigest.getInstance("MD5").digest((person.getUsername() + person.getPassword() + Calendar.getInstance().getTime().getTime()).getBytes());
                accessToken.setAccessToken(bytesToString(md5s));
                accessTokenRepository.save(accessToken);
                return WebServiceUtil.GSON.toJson(accessToken);
            } else {
                throw new Exception("Incorrect login/password");
            }
        } catch (Exception e){
            e.printStackTrace();
            return "{error: \""+e.getMessage()+"\"}";
        }
    }

    @RequestMapping(value = "/api/location", method = RequestMethod.POST, consumes = ApiConstants.APPLICATION_JSON,
            produces = ApiConstants.APPLICATION_JSON)
    public @ResponseBody String location(@RequestBody String locationJson){
        try {
            Location location = WebServiceUtil.GSON.fromJson(locationJson, Location.class);
            if (WebServiceUtil.isEmptyOrNull(location.getAccessToken())){
                throw new Exception("Access token is empty");
            }
            if (accessTokenRepository.findOneByAccesstoken(location.getAccessToken()) != null) {
                AccessToken accessToken = new AccessToken();
                accessToken.setUsername(location.getUsername());
                byte[] md5s = MessageDigest.getInstance("MD5").digest((location.getUsername() + location.getPassword() + Calendar.getInstance().getTime().getTime()).getBytes());
                accessToken.setAccessToken(bytesToString(md5s));
                accessTokenRepository.save(accessToken);
                return WebServiceUtil.GSON.toJson(accessToken);
            } else {
                throw new Exception("Incorrect login/password");
            }
        } catch (Exception e){
            e.printStackTrace();
            return "{error: \""+e.getMessage()+"\"}";
        }
    }

    String bytesToString(byte[] arrary){
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : arrary){
            stringBuilder.append(String.format("%02X",b));
        }
        return stringBuilder.toString();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("person") Person person, BindingResult result){
        personRepository.save(person);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{userId}")
    public String deletePerson(@PathVariable("userId") Long userId){
        personRepository.delete(personRepository.findOne(userId));
        return "redirect:/";
    }
}