package com.alicansadeler.myecommerce.services.Impl;

import com.alicansadeler.myecommerce.entity.Products;
import com.alicansadeler.myecommerce.exceptions.ApiException;
import com.alicansadeler.myecommerce.repository.ProductRepository;
import com.alicansadeler.myecommerce.services.service.ProductService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    private Products testProducts;

    private Products testProducts2;

    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl(productRepository);

        testProducts = new Products();
        testProducts.setId(1L);
        testProducts.setName("Televizyon");
        testProducts.setDescription("Test 123");
        testProducts.setImagePath("www.google.com");
        testProducts.setStockQuantity(20L);
    }

    @Test
    void findAll() {
        productService.findAll();
        verify(productRepository).findAll();
    }

    @Test
    void save() {

        productService.save(testProducts);
        verify(productRepository).save(testProducts);
    }

    @Test
    void findById() {
        // db'ye kaydolmuş gibi sonucu döndür
        given(productRepository.findById(testProducts.getId())).willReturn(Optional.of(testProducts));

        productService.findById(testProducts.getId());

        verify(productRepository).findById(testProducts.getId());
    }

}