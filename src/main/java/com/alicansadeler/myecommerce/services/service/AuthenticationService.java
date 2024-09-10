package com.alicansadeler.myecommerce.services.service;

import com.alicansadeler.myecommerce.entity.User;

public interface AuthenticationService {
    User registerUser(String firstName, String lastName, String email, String password);
    User registerAdmin(String firstName,String lastName, String email, String password);
}
