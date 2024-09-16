package com.alicansadeler.myecommerce.services.Impl;

import com.alicansadeler.myecommerce.entity.ProductDetails;
import com.alicansadeler.myecommerce.exceptions.ApiException;
import com.alicansadeler.myecommerce.repository.ProductDetailsRepository;
import com.alicansadeler.myecommerce.services.service.ProductDetailsService;
import com.alicansadeler.myecommerce.validations.Validate;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductDetailsServiceImpl implements ProductDetailsService {

    private final ProductDetailsRepository productDetailsRepository;

    @Autowired
    public ProductDetailsServiceImpl(ProductDetailsRepository productDetailsRepository) {
        this.productDetailsRepository = productDetailsRepository;
    }


    @Override
    public List<ProductDetails> findAll() {
        return productDetailsRepository.findAll();
    }

    @Override
    public ProductDetails findById(Long id) {
        Optional<ProductDetails> productDetails = productDetailsRepository.findById(id);
        if (productDetails.isEmpty()) {
            throw new ApiException("Product details not found, ID: " + id, HttpStatus.NOT_FOUND);
        }
        return productDetails.get();
    }

    @Override
    @Transactional
    public ProductDetails save(ProductDetails productDetails) {
        return productDetailsRepository.save(productDetails);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        ProductDetails productDetails = findById(id);
        productDetailsRepository.delete(productDetails);
    }

    @Override
    @Transactional
    public ProductDetails update(Long id, ProductDetails productDetails) {
        ProductDetails oldProductDetails = findById(id);
        Validate.validateDetailsUpdate(oldProductDetails, productDetails);
        return productDetails;
    }
}
