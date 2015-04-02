package com.wherepeople.spring.mvc;

import com.wherepeople.spring.mvc.model.ApiConstants;
import com.wherepeople.spring.mvc.model.person.Person;
import com.wherepeople.spring.mvc.model.person.RequestResult;
import com.wherepeople.spring.mvc.service.UserService;
import com.wherepeople.spring.mvc.util.WebServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class PersonController {
    public static final String PEOPLE = "people";
    @Autowired
    private UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printPeople(ModelMap model) {
		model.addAttribute("person", new Person());
        model.addAttribute("people", userService.getAllUsers());
		return "people";
	}

    @RequestMapping(value = "/api/register", method = RequestMethod.POST, consumes = ApiConstants.APPLICATION_JSON,
        produces = ApiConstants.APPLICATION_JSON)
    public @ResponseBody String addPerson(@RequestBody String personJson){
        RequestResult registrationResult = new RequestResult();

        try {
            Person person = WebServiceUtil.GSON.fromJson(personJson, Person.class);
            if (WebServiceUtil.isEmptyOrNull(person.getUsername()) || WebServiceUtil.isEmptyOrNull(person.getPassword())){
                throw new Exception("Username or password is empty");
            }
            userService.createUser(person);
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
            return WebServiceUtil.GSON.toJson(userService.login(person));
        } catch (Exception e){
            e.printStackTrace();
            return new RequestResult(e.getMessage()).toString();
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPerson(@Valid @ModelAttribute("person") Person person, BindingResult result){
        if(result.hasErrors()){
            return PEOPLE;
        }
        try {
            userService.createUser(person);
        } catch (Exception e) {
            result.reject(e.getMessage());
            return PEOPLE;
        }
        return "redirect:/";
    }
}