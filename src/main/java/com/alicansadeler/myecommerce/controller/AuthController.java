package com.alicansadeler.myecommerce.controller;

import com.alicansadeler.myecommerce.dto.Converter;
import com.alicansadeler.myecommerce.dto.post.RegisterUser;
import com.alicansadeler.myecommerce.dto.response.UserResponse;
import com.alicansadeler.myecommerce.entity.User;
import com.alicansadeler.myecommerce.services.service.AuthenticationService;
import com.alicansadeler.myecommerce.services.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationService authenticationService;
    private final UserService userService;


    @Autowired
    public AuthController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping("/register/user")
    @Transactional
    public User registerUser(@RequestBody RegisterUser registerUser){
        return authenticationService
                .registerUser(registerUser.firstName(), registerUser.lastName() ,registerUser.email(), registerUser.password());
    }

    @PostMapping("/register/admin")
    @Transactional
    public User registerAdmin(@RequestBody RegisterUser registerUser){
        return authenticationService
                .registerAdmin(registerUser.firstName(), registerUser.lastName() ,registerUser.email(), registerUser.password());
    }


    @GetMapping("/users")
    public List<UserResponse> getUsers() {
        return Converter.userResponseList(userService.findAll());
    }

    @GetMapping("/users/{id}")
    public UserResponse getUsers(@PathVariable Long id) {
      return Converter.userResponse(userService.findById(id));
    }

}
