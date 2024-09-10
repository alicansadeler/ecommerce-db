package com.alicansadeler.myecommerce.services.service;

import com.alicansadeler.myecommerce.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService  {
    UserDetails loadUserByUsername(String email);
    User save(User user);
    void delete(Long id);

    List<User> findAll();
    User findById(Long id);
}
