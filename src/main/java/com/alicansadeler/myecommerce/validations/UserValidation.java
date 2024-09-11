package com.alicansadeler.myecommerce.validations;

import com.alicansadeler.myecommerce.entity.User;
import com.alicansadeler.myecommerce.exceptions.ApiException;
import org.springframework.http.HttpStatus;

import java.util.regex.Pattern;

public class UserValidation {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static void validateUserForSave(User user) {
        validateEmail(user.getEmail());
        validatePassword(user.getPassword());
        validateUsername(user.getUsername());
    }

    public static void validateUserForUpdate(User user) {
        if (user.getEmail() != null) {
            validateEmail(user.getEmail());
        }
        if (user.getPassword() != null) {
            validatePassword(user.getPassword());
        }
        if (user.getUsername() != null) {
            validateUsername(user.getUsername());
        }
    }

    public static void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new ApiException("Email cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new ApiException("Invalid email format", HttpStatus.BAD_REQUEST);
        }
    }

    public static void validatePassword(String password) {
        if (password == null || password.length() < 8) {
            throw new ApiException("Password must be at least 8 characters long", HttpStatus.BAD_REQUEST);
        }
    }

    public static void validateUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new ApiException("Username cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if (username.length() < 3 || username.length() > 50) {
            throw new ApiException("Username must be between 3 and 50 characters", HttpStatus.BAD_REQUEST);
        }
    }

    public static void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new ApiException("Invalid user ID", HttpStatus.BAD_REQUEST);
        }
    }
}
