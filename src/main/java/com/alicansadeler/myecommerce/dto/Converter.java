package com.alicansadeler.myecommerce.dto;

import com.alicansadeler.myecommerce.dto.response.CategoryProductDTO;
import com.alicansadeler.myecommerce.dto.response.UserResponse;
import com.alicansadeler.myecommerce.entity.Categories;
import com.alicansadeler.myecommerce.entity.User;

import java.util.ArrayList;
import java.util.List;

public class Converter {

    public static List<UserResponse> userResponseList(List<User> users) {
        List<UserResponse> userRes = new ArrayList<>();
        for (User user : users) {
            assert false;
            userRes.add(userResponse(user));
        }
        return userRes;
    }

    public static UserResponse userResponse(User user) {
        return new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAuthorities().toString());
    }

    public static CategoryProductDTO categoryProductDTO(Long id, Categories categories) {
        return new CategoryProductDTO(id,
                categories.getCategoryName(), categories.getDescription(), categories.getProducts()
        );
    }
}
