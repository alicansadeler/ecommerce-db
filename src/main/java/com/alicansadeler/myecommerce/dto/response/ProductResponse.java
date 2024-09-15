package com.alicansadeler.myecommerce.dto.response;

import java.util.List;

public record ProductResponse(Long id, String name, String description, String imagePath, Long stockQuantity,String categoryName, List<ProductDetailResponse> productDetailResponse) {
}
