package com.wherepeople.spring.mvc.repository;

import com.wherepeople.spring.mvc.model.location.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by yuriy on 24.03.15.
 */
public interface LocationRepository extends JpaRepository<Location, Long> {

    List<Location> findByUsername(String username);

}
