package com.alicansadeler.myecommerce.validations;

import com.alicansadeler.myecommerce.entity.*;
import com.alicansadeler.myecommerce.exceptions.ApiException;
import org.springframework.http.HttpStatus;

import java.util.Set;
import java.util.regex.Pattern;

public class Validate {

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

    public static void validateAddressUpdate(Address oldAddress, Address updatedAddress) {
        if (updatedAddress.getUser() != null) {
            oldAddress.setUser(updatedAddress.getUser());
        }
        if (updatedAddress.getAddressText() != null) {
            oldAddress.setAddressText(updatedAddress.getAddressText());
        }
        if (updatedAddress.getCity() != null) {
            oldAddress.setCity(updatedAddress.getCity());
        }
        if (updatedAddress.getCountry() != null) {
            oldAddress.setCountry(updatedAddress.getCountry());
        }
        if (updatedAddress.getPostalCode() != null) {
            oldAddress.setPostalCode(updatedAddress.getPostalCode());
        }
        if (updatedAddress.getTitle() != null) {
            oldAddress.setTitle(updatedAddress.getTitle());
        }
    }

    public static void validateCategoryUpdate(Categories oldCategories, Categories updateCategories) {
        if (updateCategories.getCategoryName() != null) {
            oldCategories.setCategoryName(updateCategories.getCategoryName());
        }
        if (updateCategories.getDescription() != null) {
            oldCategories.setDescription(updateCategories.getDescription());
        }
        if (updateCategories.getProducts() != null) {
            oldCategories.setProducts(updateCategories.getProducts());
        }

    }

    public static void validateProductUpdate(Products oldProducts, Products updateProducts) {
        if (updateProducts.getName() != null) {
            oldProducts.setName(updateProducts.getName());
        }
        if (updateProducts.getDescription() != null) {
            oldProducts.setDescription(updateProducts.getDescription());
        }
        if (updateProducts.getStockQuantity() != null) {
            oldProducts.setStockQuantity(updateProducts.getStockQuantity());
        }
        if (updateProducts.getOrderCount() != null) {
            oldProducts.setOrderCount(updateProducts.getOrderCount());
        }
        if (updateProducts.getImagePath() != null) {
            oldProducts.setImagePath(updateProducts.getImagePath());
        }
        if (updateProducts.getCategories() != null) {
            oldProducts.setCategories(updateProducts.getCategories());
        }

        updateProductDetails(oldProducts, updateProducts);
    }

    private static void updateProductDetails(Products oldProduct, Products updateProduct) {
        if (updateProduct.getProductDetails() != null) {
            oldProduct.getProductDetails().clear();
            for (ProductDetails newDetail : updateProduct.getProductDetails()) {
                ProductDetails detailCopy = new ProductDetails();
                detailCopy.setSize(newDetail.getSize());
                detailCopy.setColor(newDetail.getColor());
                detailCopy.setStock(newDetail.getStock());
                detailCopy.setProducts(oldProduct);
                oldProduct.getProductDetails().add(detailCopy);
            }
        }
    }


    public static void validateDetailsUpdate(ProductDetails oldProductDetails, ProductDetails updateProductDetails) {
        if (updateProductDetails.getSize() != null) {
            oldProductDetails.setSize(updateProductDetails.getSize());
        }
        if (updateProductDetails.getColor() != null) {
            oldProductDetails.setColor(updateProductDetails.getColor());
        }
        if (updateProductDetails.getProducts() != null) {
            oldProductDetails.setProducts(updateProductDetails.getProducts());
        }
        if (updateProductDetails.getId() != null) {
            oldProductDetails.setId(updateProductDetails.getId());
        }
        if (updateProductDetails.getStock() != null) {
            oldProductDetails.setStock(updateProductDetails.getStock());
        }
    }
}
