package com.alicansadeler.myecommerce.services.Impl;

import com.alicansadeler.myecommerce.entity.Role;
import com.alicansadeler.myecommerce.entity.User;
import com.alicansadeler.myecommerce.exceptions.ApiException;
import com.alicansadeler.myecommerce.repository.RoleRepository;
import com.alicansadeler.myecommerce.repository.UserRepository;
import com.alicansadeler.myecommerce.services.service.AuthenticationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private User registerCommon(String firstName, String lastName, String email, String password, String roleAuthority) {
        if (userRepository.findUserByEmail(email).isPresent()) {
            throw new ApiException("Email already in use", HttpStatus.BAD_REQUEST);
        }

        String encodedPassword = passwordEncoder.encode(password);
        Role role = roleRepository.findByAuthority(roleAuthority)
                .orElseThrow(() -> new ApiException("Role not found: " + roleAuthority, HttpStatus.NOT_FOUND));

        Set<Role> roles = new HashSet<>();
        roles.add(role);

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setAuthorities(roles);

        return userRepository.save(user);
    }

    @Override
    public User registerUser(String firstName, String lastName, String email, String password) {
        return registerCommon(firstName, lastName, email, password, "USER");
    }

    @Override
    public User registerAdmin(String firstName, String lastName, String email, String password) {
        return registerCommon(firstName, lastName, email, password, "ADMIN");
    }
}
