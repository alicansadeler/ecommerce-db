package com.alicansadeler.myecommerce.services.service;

import com.alicansadeler.myecommerce.entity.Products;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface ProductService {

    Products save(@Valid Products products);
    Products delete(@NotNull Long id);
    List<Products> findAll();
    Products findById(@NotNull Long id);
    Products update(@NotNull Long id, @Valid Products updateProducts);

}
