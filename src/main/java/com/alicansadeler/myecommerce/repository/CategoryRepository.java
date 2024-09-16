package com.alicansadeler.myecommerce.repository;

import com.alicansadeler.myecommerce.dto.response.CategoryOfProduct;
import com.alicansadeler.myecommerce.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Categories, Long> {
    @Query("SELECT new com.alicansadeler.myecommerce.dto.response.CategoryOfProduct(ca.categoryName, pr.name, pr.stockQuantity, pr.imagePath, pr.description) " +
            "FROM Categories ca LEFT JOIN ca.products pr")
    List<CategoryOfProduct> findCategoryOfProduct();

}
