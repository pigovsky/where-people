package com.wherepeople.spring.mvc.repository;

import com.wherepeople.spring.mvc.model.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository of persons
 */
public interface PersonRepository extends JpaRepository<Person, Long>{
    Person findOneByUsername(String username);

    Person findOneByUsernameAndPassword(String username, String password);
}
