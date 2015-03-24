package com.wherepeople.spring.mvc.config;

import com.wherepeople.spring.mvc.service.OpenIdUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.openid.OpenIDAuthenticationToken;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private OpenIdUserDetailsService openIdUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.openidLogin().authenticationUserDetailsService(openIdUserDetailsService).attributeExchange("https://www.google.com/.*").attribute("email").and().
                attribute("firstName").and().attribute("lastName");
    }
}
