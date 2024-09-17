package com.alicansadeler.myecommerce.controller;

import com.alicansadeler.myecommerce.config.SecurityConfig;
import com.alicansadeler.myecommerce.entity.Role;
import com.alicansadeler.myecommerce.entity.User;
import com.alicansadeler.myecommerce.services.service.AddressService;
import com.alicansadeler.myecommerce.services.service.AuthenticationService;
import com.alicansadeler.myecommerce.services.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@Import(SecurityConfig.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private AddressService addressService;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private AuthenticationService authenticationService;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setFirstName("Ali Can");
        testUser.setLastName("Sadeler");
        testUser.setEmail("alicansadeler@gmail.com");
        testUser.setPassword("4244test.");
        testUser.setRoles(new HashSet<>(Set.of(new Role(1, "ADMIN"))));
        testUser.setId(1L);
    }





}