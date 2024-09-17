package com.alicansadeler.myecommerce.services.Impl;

import com.alicansadeler.myecommerce.entity.User;
import com.alicansadeler.myecommerce.exceptions.ApiException;
import com.alicansadeler.myecommerce.repository.UserRepository;
import com.alicansadeler.myecommerce.services.service.UserService;
import com.alicansadeler.myecommerce.validations.Validate;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
    }

    private User save(User user) {
        Validate.validateUserForSave(user);
        Optional<User> user1 = userRepository.findUserByEmail(user.getEmail());
        if (user1.isEmpty()) {
            throw new ApiException("User already in use. Email: " + user.getEmail(), HttpStatus.BAD_REQUEST);
        }
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User delete(Long id) {
        Validate.validateId(id);
        User user = findById(id);
        userRepository.delete(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        Validate.validateId(id);
        return userRepository.findById(id)
                .orElseThrow(() -> new ApiException("ID not found, ID: " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public User update(Long id, User user) {
        User oldUser = findById(id);
        String encodedPass = passwordEncoder.encode(user.getPassword());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(encodedPass);
        oldUser.setLastName(user.getLastName());
        oldUser.setFirstName(user.getFirstName());

        if (user.getAuthorities() != null && !user.getAuthorities().isEmpty()) {
            oldUser.setRoles(user.getRoles());
        }

        if (user.getBirthOfDate() != null) {
            oldUser.setBirthOfDate(user.getBirthOfDate());
        }
        if (user.getAvatar() != null) {
            oldUser.setAvatar(user.getAvatar());
        }

        return userRepository.save(oldUser);
    }

}
