package com.blueteam.authentication.repo;

import com.blueteam.authentication.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.randomValue = ?1")
    User findByRandomValue(String randomValue);


}
