package com.alicansadeler.myecommerce.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record ProductResponse(Long id, String name, String description, String imagePath, Long stockQuantity, String categoryName, List<ProductDetailResponse> productDetailResponse, BigDecimal price) {
}
