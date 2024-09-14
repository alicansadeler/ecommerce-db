package com.alicansadeler.myecommerce.controller;

import com.alicansadeler.myecommerce.dto.Converter;
import com.alicansadeler.myecommerce.dto.response.UserResponse;
import com.alicansadeler.myecommerce.entity.User;
import com.alicansadeler.myecommerce.services.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getUsers() {
        return Converter.userResponseList(userService.findAll());
    }

    @GetMapping("users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUsers(@PathVariable Long id) {
        return Converter.userResponse(userService.findById(id));
    }

    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public User updateUser(@PathVariable Long id,@Valid @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User deleteUser(@PathVariable Long id) {
        return userService.delete(id);
    }

    //[TODO] ORDER SERVİCE KATMANI EKLENDİĞİNDE KULLANICILARIN SİPARİŞLERİNİ GÖRECEK BİR ENDPOİNT EKLE
}
