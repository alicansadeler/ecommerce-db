package com.alicansadeler.myecommerce.controller;

import com.alicansadeler.myecommerce.dto.Converter;
import com.alicansadeler.myecommerce.dto.response.ProductResponse;
import com.alicansadeler.myecommerce.entity.Categories;
import com.alicansadeler.myecommerce.entity.Products;
import com.alicansadeler.myecommerce.services.service.CategoryService;
import com.alicansadeler.myecommerce.services.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProduct() {
        return Converter.productResponseList(productService.findAll());
    }

    @GetMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProduct(@PathVariable Long id) {
        return Converter.productResponse(productService.findById(id));
    }

    @PostMapping("/{categoryId}/products")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse saveProduct(@PathVariable Long categoryId, @RequestBody Products products) {
        Categories categories = categoryService.find(categoryId);
        products.setCategories(categories);
        return Converter.productResponse(productService.save(products));
    }

    @PutMapping("/products/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Products updateProduct(@PathVariable Long id, @RequestBody Products products) {
        return productService.update(id, products);
    }

    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }
}
