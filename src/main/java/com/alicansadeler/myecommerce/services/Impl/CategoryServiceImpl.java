package com.alicansadeler.myecommerce.services.Impl;

import com.alicansadeler.myecommerce.dto.Converter;
import com.alicansadeler.myecommerce.dto.response.CategoryOfProduct;
import com.alicansadeler.myecommerce.dto.response.CategoryProductDTO;
import com.alicansadeler.myecommerce.entity.Categories;
import com.alicansadeler.myecommerce.exceptions.ApiException;
import com.alicansadeler.myecommerce.repository.CategoryRepository;
import com.alicansadeler.myecommerce.services.service.CategoryService;
import com.alicansadeler.myecommerce.validations.Validate;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Categories> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryProductDTO findById(Long id) {
        Validate.validateId(id);
        Optional<Categories> categories = categoryRepository.findById(id);
        if (categories.isEmpty()) {
            throw new ApiException("Category not found. ID: " + id, HttpStatus.NOT_FOUND);
        }
      return  Converter.categoryProductDTO(categories.orElseThrow().getId(), categories.get());
    }

    public Categories find(Long id) {
        Validate.validateId(id);
        Optional<Categories> categories = categoryRepository.findById(id);
        if (categories.isEmpty()) {
            throw new ApiException("Category not found. ID: " + id, HttpStatus.NOT_FOUND);
        }
        return categories.get();
    }

    @Override
    public List<CategoryOfProduct> findCategoryOfProduct() {
        return categoryRepository.findCategoryOfProduct();
    }

    @Override
    @Transactional
    public Categories save(Categories categories) {
        return categoryRepository.save(categories);
    }

    @Override
    @Transactional
    public Categories  delete(Long id) {
         Categories categories = find(id);
         categoryRepository.delete(categories);
        return categories;
    }

    @Override
    @Transactional
    public Categories update(Long id, Categories updateCategories) {
        Categories oldCategory = find(id);
        Validate.validateCategoryUpdate(oldCategory, updateCategories);
        return oldCategory;
    }
}
