package com.alicansadeler.myecommerce.dto.response;

import com.alicansadeler.myecommerce.entity.Products;

import java.util.List;
import java.util.Set;

public record CategoryProductDTO(Long id, String categoryName, String description, Set<Products> products) {
}
