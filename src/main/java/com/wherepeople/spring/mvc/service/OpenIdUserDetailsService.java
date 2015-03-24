package com.wherepeople.spring.mvc.service;

import com.wherepeople.spring.mvc.model.person.UserAccount;
import com.wherepeople.spring.mvc.repository.UserAccountRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAuthenticationToken;

/**
 * Created by yuriy on 24.03.15.
 */
public class OpenIdUserDetailsService implements AuthenticationUserDetailsService<OpenIDAuthenticationToken> {

    @Autowired
    private UserAccountRepositoryService userAccountRepositoryService;

    public UserDetails loadUserByUsername(String openIdIdentifier) {
        UserAccount userAccount = userAccountRepositoryService.findOneByOpenIdIdentifier(openIdIdentifier);
        if (userAccount == null) {
            throw new UsernameNotFoundException(openIdIdentifier);
        } else {
            if (!userAccount.isEnabled()) {
                throw new DisabledException("User is disabled");
            }
        }
        return userAccount;
    }

    @Override
    public UserDetails loadUserDetails(OpenIDAuthenticationToken openIDAuthenticationToken) throws UsernameNotFoundException {
        return loadUserByUsername(openIDAuthenticationToken.getName());
    }
}