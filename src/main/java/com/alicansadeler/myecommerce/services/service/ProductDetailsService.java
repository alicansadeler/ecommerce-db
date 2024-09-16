package com.alicansadeler.myecommerce.services.service;

import com.alicansadeler.myecommerce.entity.ProductDetails;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface ProductDetailsService {
    ProductDetails save(@Valid ProductDetails productDetails);
    void delete(@NotNull Long id);
    List<ProductDetails> findAll();
    ProductDetails findById(@NotNull Long id);
    ProductDetails update(@NotNull Long id, @Valid ProductDetails productDetails);
}
