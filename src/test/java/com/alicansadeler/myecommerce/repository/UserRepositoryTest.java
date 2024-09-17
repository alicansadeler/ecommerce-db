package com.alicansadeler.myecommerce.repository;

import com.alicansadeler.myecommerce.entity.Role;
import com.alicansadeler.myecommerce.entity.User;
import com.alicansadeler.myecommerce.exceptions.ApiException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class UserRepositoryTest {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Autowired
    UserRepositoryTest(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @BeforeEach
    void setUp() {
        Role role = new Role();
        role.setAuthority("ADMIN");
        role = roleRepository.save(role);

        User user = new User();
        user.setFirstName("Ali Can");
        user.setLastName("Sadeler");
        user.setEmail("alicansadeler@gmail.com");
        user.setPassword("4244test.");
        user.setRoles(new HashSet<>(Set.of(role)));

        userRepository.save(user);
    }


    @DisplayName("Kullanıcının email adresi bulunuyor mu")
    @Test
    void findUserByEmail() {

        User foundUser = userRepository.findUserByEmail("alicansadeler@gmail.com").orElse(null);

        assertNotNull(foundUser, "Kullanıcı bulunamadı");
        assertEquals("Ali Can", foundUser.getFirstName(), "İsim uyumsuz");
        assertEquals("Sadeler", foundUser.getLastName(), "Soyisim uyumsuz");
        assertEquals("alicansadeler@gmail.com", foundUser.getEmail(), "Email uyumsuz");
    }
    @DisplayName("Kullanıcı bilgilerini güncelleyebiliyor mu")
    @Test
    void updateUser() {
        User user = userRepository.findUserByEmail("alicansadeler@gmail.com").orElseThrow();
        user.setLastName("Test 123");
        userRepository.save(user);

        User updatedUser = userRepository.findUserByEmail("alicansadeler@gmail.com").orElseThrow();
        assertEquals("Test 123", updatedUser.getLastName(), "Soyisim güncellenmedi");
    }

//    @DisplayName("Aynı kullanıcı kaydolamıyor")
//    @Test
//    void saveUser() {
//        Role role = new Role();
//        role.setAuthority("ADMIN");
//        role = roleRepository.save(role);
//
//        User user = new User();
//        user.setFirstName("Ali Can");
//        user.setLastName("Sadeler");
//        user.setEmail("alicansadeler@gmail.com");
//        user.setPassword("4244test.");
//        user.setRoles(new HashSet<>(Set.of(role)));
//
//        userRepository.save(user);
//        assertThrows(ApiException.class, () -> userRepository.save(user));
//    }
}