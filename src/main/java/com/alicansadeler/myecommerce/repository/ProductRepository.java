package com.alicansadeler.myecommerce.repository;

import com.alicansadeler.myecommerce.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Products, Long> {
}
