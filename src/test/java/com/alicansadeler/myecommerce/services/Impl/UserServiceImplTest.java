package com.alicansadeler.myecommerce.services.Impl;

import com.alicansadeler.myecommerce.entity.Role;
import com.alicansadeler.myecommerce.entity.User;
import com.alicansadeler.myecommerce.repository.RoleRepository;
import com.alicansadeler.myecommerce.repository.UserRepository;
import com.alicansadeler.myecommerce.services.service.AuthenticationService;
import com.alicansadeler.myecommerce.services.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
@ActiveProfiles("test")
@Transactional
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
// sahte örnekleri oluştur
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RoleRepository roleRepository;

    private AuthenticationService authenticationService;

    private UserService userService;

    private User testUser;

    @BeforeEach
    void setUp() {
        // inject et
        userService = new UserServiceImpl(userRepository, passwordEncoder);
        authenticationService = new AuthenticationServiceImpl(userRepository, roleRepository, passwordEncoder);
        testUser = new User();
        testUser.setFirstName("Ali Can");
        testUser.setLastName("Sadeler");
        testUser.setEmail("alicansadeler@gmail.com");
        testUser.setPassword("4244test.");
        testUser.setRoles(new HashSet<>(Set.of(new Role(1, "ADMIN"))));
        testUser.setId(1L);
    }

    @Test
    void loadUserByUsername() {
        //belirli bir girdiyle çağrıldığında hangi çıktıyı döndürmesi gerektiğini belirtir.
        when(userRepository.findUserByEmail("alicansadeler@gmail.com")).thenReturn(Optional.ofNullable(testUser));
        userService.loadUserByUsername("alicansadeler@gmail.com");
        verify(userRepository).findUserByEmail("alicansadeler@gmail.com");
    }

    @Test
    void delete() {
        //belirli bir girdiyle çağrıldığında hangi çıktıyı döndürmesi gerektiğini belirtir.
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        userService.delete(1L);
        verify(userRepository).delete(testUser);
    }


    @Test
    void findAll() {
        userService.findAll();
        // service repoyla konuşabiliyor mu ?
        verify(userRepository).findAll();
    }

    @Test
    void findById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        userService.findById(testUser.getId());
        verify(userRepository).findById(testUser.getId());
    }

}