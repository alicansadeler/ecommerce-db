package com.alicansadeler.myecommerce.controller;

import com.alicansadeler.myecommerce.dto.response.CategoryProductDTO;
import com.alicansadeler.myecommerce.entity.Categories;
import com.alicansadeler.myecommerce.services.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Categories> getAllCategory() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryProductDTO getCategory(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Categories saveCategory(@RequestBody Categories categories) {
        return categoryService.save(categories);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Categories updateCategory(@PathVariable Long id, @RequestBody Categories categories) {
        return categoryService.update(id, categories);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Categories deleteCategory(@PathVariable Long id) {
        return categoryService.delete(id);
    }
}
