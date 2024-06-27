package com.kucharski.shop.repository;

import com.kucharski.shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userId);
    boolean existsByName(String userName);
    User findByName(String userName);

}
