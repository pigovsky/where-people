package com.wherepeople.spring.mvc.model.person;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Class holding person name
 */
@Entity(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 3, max = 50, message = "Name must be from 3 to 50 symbols")
    @Basic
    private String name;

    @Size(min=3, max = 10, message = "Username must consist of from 3 to 10 characters")
    @Pattern(regexp = "[A-Za-z0-9]+", message = "Username shall be alphanumeric without spaces")
    @Basic
    private String username;

    @Basic
    @Size(min = 6, message = "Password has to consist of at least six characters")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
