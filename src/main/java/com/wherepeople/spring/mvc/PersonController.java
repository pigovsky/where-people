package com.wherepeople.spring.mvc;

import com.wherepeople.spring.mvc.model.ApiConstants;
import com.wherepeople.spring.mvc.model.person.Person;
import com.wherepeople.spring.mvc.model.person.RegistrationResult;
import com.wherepeople.spring.mvc.repository.PersonRepository;
import com.wherepeople.spring.mvc.util.WebServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printPeople(ModelMap model) {
		model.addAttribute("person", new Person());
        model.addAttribute("people", personRepository.findAll());
		return "people";
	}

    @RequestMapping(value = "/api/person/add", method = RequestMethod.POST, consumes = ApiConstants.APPLICATION_JSON,
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