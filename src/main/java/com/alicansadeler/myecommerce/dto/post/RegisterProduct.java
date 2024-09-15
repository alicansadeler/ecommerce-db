package com.alicansadeler.myecommerce.dto.post;

public record RegisterProduct(Long id, String name, String description, String imagePath, Long stockQuantity,Long categoryId) {
}
