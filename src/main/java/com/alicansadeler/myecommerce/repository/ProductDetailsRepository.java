package com.alicansadeler.myecommerce.repository;

import com.alicansadeler.myecommerce.entity.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Long> {
}
