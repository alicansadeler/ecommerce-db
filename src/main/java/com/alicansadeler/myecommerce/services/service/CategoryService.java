package com.alicansadeler.myecommerce.services.service;

import com.alicansadeler.myecommerce.dto.response.CategoryProductDTO;
import com.alicansadeler.myecommerce.entity.Address;
import com.alicansadeler.myecommerce.entity.Categories;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface CategoryService {
    Categories save(@Valid Categories categories);
    Categories delete(@NotNull Long id);
    List<Categories> findAll();
    CategoryProductDTO findById(@NotNull Long id);
    Categories update(@NotNull Long id, @Valid Categories updateCategories);
    Categories find(Long id);

}
