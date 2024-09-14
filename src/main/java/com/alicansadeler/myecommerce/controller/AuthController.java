package com.alicansadeler.myecommerce.controller;


import com.alicansadeler.myecommerce.dto.post.RegisterUser;
import com.alicansadeler.myecommerce.entity.User;
import com.alicansadeler.myecommerce.services.service.AuthenticationService;
import com.alicansadeler.myecommerce.services.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationService authenticationService;



    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @PostMapping("/register/user")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public User registerUser(@RequestBody RegisterUser registerUser){
        return authenticationService
                .registerUser(registerUser.firstName(), registerUser.lastName() ,registerUser.email(), registerUser.password());
    }

    @PostMapping("/register/admin")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public User registerAdmin(@RequestBody RegisterUser registerUser){
        return authenticationService
                .registerAdmin(registerUser.firstName(), registerUser.lastName() ,registerUser.email(), registerUser.password());
    }

}
