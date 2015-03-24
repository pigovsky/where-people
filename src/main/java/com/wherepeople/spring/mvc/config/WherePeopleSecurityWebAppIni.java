package com.wherepeople.spring.mvc.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Created by yuriy on 24.03.15.
 */
public class WherePeopleSecurityWebAppIni extends AbstractSecurityWebApplicationInitializer {

    public WherePeopleSecurityWebAppIni(){
        super(SecurityConfig.class);
    }
}
