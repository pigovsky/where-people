package com.wherepeople.spring.mvc.repository;

import com.wherepeople.spring.mvc.model.person.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by yuriy on 24.03.15.
 */
public interface UserAccountRepositoryService  extends JpaRepository<UserAccount, Long> {
    UserAccount findOneByOpenIdIdentifier(String openIdIdentifier);
}
