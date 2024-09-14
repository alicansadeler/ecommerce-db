package com.alicansadeler.myecommerce.services.Impl;

import com.alicansadeler.myecommerce.entity.Products;
import com.alicansadeler.myecommerce.exceptions.ApiException;
import com.alicansadeler.myecommerce.repository.ProductRepository;
import com.alicansadeler.myecommerce.services.service.ProductService;
import com.alicansadeler.myecommerce.validations.Validate;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Products> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Products findById(Long id) {
        Validate.validateId(id);
        Optional<Products> products = productRepository.findById(id);
        if (products.isEmpty()) {
            throw new ApiException("Product not found, ID: " + id, HttpStatus.NOT_FOUND);
        }
        return products.get();
    }

    @Override
    @Transactional
    public Products save(Products products) {
        return productRepository.save(products);
    }

    @Override
    @Transactional
    public Products delete(Long id) {
        Products products = findById(id);
        productRepository.delete(products);
        return products;
    }

    @Override
    @Transactional
    public Products update(Long id, Products updateProducts) {
        Products oldProduct = findById(id);
        Validate.validateProductUpdate(oldProduct, updateProducts);
       return productRepository.save(oldProduct);
    }
}
