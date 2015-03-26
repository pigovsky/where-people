package com.wherepeople.spring.mvc.repository;

import com.wherepeople.spring.mvc.model.login.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yuriy on 24.03.15.
 */
public interface AccessTokenRepository extends JpaRepository<AccessToken, Long> {
    AccessToken findOneByUsername(String username);
}
