package com.alicansadeler.myecommerce.repository;

import com.alicansadeler.myecommerce.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Categories, Long> {
}
