package com.alicansadeler.myecommerce.dto;

import com.alicansadeler.myecommerce.dto.response.CategoryProductDTO;
import com.alicansadeler.myecommerce.dto.response.ProductDetailResponse;
import com.alicansadeler.myecommerce.dto.response.ProductResponse;
import com.alicansadeler.myecommerce.dto.response.UserResponse;
import com.alicansadeler.myecommerce.entity.Categories;
import com.alicansadeler.myecommerce.entity.ProductDetails;
import com.alicansadeler.myecommerce.entity.Products;
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


    public static ProductResponse productResponse(Products products) {
        List<ProductDetailResponse> detailResponses = new ArrayList<>();

        for (ProductDetails detail : products.getProductDetails()) {
            ProductDetailResponse detailResponse = new ProductDetailResponse(
                    detail.getSize(),
                    detail.getColor(),
                    detail.getStock()
            );
            detailResponses.add(detailResponse);
        }

        return new ProductResponse(products.getId(), products.getName(), products.getImagePath(), products.getDescription(), products.getStockQuantity(), products.getCategories().getCategoryName() ,detailResponses);

    }

    public static List<ProductResponse> productResponseList(List<Products> products) {
        List<ProductResponse> productResponses = new ArrayList<>();

        for (Products product : products) {
            ProductResponse productResponse = productResponse(product);
            productResponses.add(productResponse);
        }

        return productResponses;
    }
}
