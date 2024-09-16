package com.alicansadeler.myecommerce.controller;


import com.alicansadeler.myecommerce.entity.ProductDetails;
import com.alicansadeler.myecommerce.entity.Products;
import com.alicansadeler.myecommerce.services.service.ProductDetailsService;
import com.alicansadeler.myecommerce.services.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop/products")
public class ProductDetailsController {
    // TODO post yaparken hangi kategoriye ait olduğunu gir

    private final ProductDetailsService productDetailsService;
    private final ProductService productService;
    @Autowired
    public ProductDetailsController(ProductDetailsService productDetailsService, ProductService productService) {
        this.productDetailsService = productDetailsService;
        this.productService = productService;
    }


    @GetMapping("/details/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDetails getAllDetails(@PathVariable Long id) {
        return productDetailsService.findById(id);
    }

    @GetMapping("/details")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDetails> getDetail() {
        return productDetailsService.findAll();
    }

    @PostMapping("/{productId}/details")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDetails saveDetail(@PathVariable Long productId, @RequestBody ProductDetails productDetails) {
        Products products = productService.findById(productId);
        productDetails.setProducts(products);
        return productDetailsService.save(productDetails);
    }

    @PutMapping("/details/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDetails updateDetail(@PathVariable Long id, @RequestBody ProductDetails productDetails) {
        return productDetailsService.update(id,productDetails);
    }

}
