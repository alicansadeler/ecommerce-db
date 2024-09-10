package com.alicansadeler.myecommerce.services.Impl;

import com.alicansadeler.myecommerce.entity.User;
import com.alicansadeler.myecommerce.exceptions.ApiException;
import com.alicansadeler.myecommerce.repository.UserRepository;
import com.alicansadeler.myecommerce.services.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        User user = findById(id);
        userRepository.delete(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
   .orElseThrow(() -> new ApiException("User not found with id: " + id, HttpStatus.NOT_FOUND));
    }
}
