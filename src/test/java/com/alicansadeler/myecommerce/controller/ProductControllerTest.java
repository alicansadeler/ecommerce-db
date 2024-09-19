package com.alicansadeler.myecommerce.controller;

import com.alicansadeler.myecommerce.config.SecurityConfig;
import com.alicansadeler.myecommerce.dto.response.ProductDetailResponse;
import com.alicansadeler.myecommerce.dto.response.ProductResponse;
import com.alicansadeler.myecommerce.entity.Categories;
import com.alicansadeler.myecommerce.entity.ProductDetails;
import com.alicansadeler.myecommerce.entity.Products;
import com.alicansadeler.myecommerce.services.service.CategoryService;
import com.alicansadeler.myecommerce.services.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
@ActiveProfiles("test")
//@Import(SecurityConfig.class)
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private ProductService productService;

    private ProductDetails productDetails;
    private Products products;
    private ProductDetailResponse productDetailResponse;
    private ProductResponse productResponse;
    private Categories categories;

    @BeforeEach
    void setUp() {
        categories = new Categories();
        categories.setCategoryName("Electronics");
        categories.setId(1L);

        products = new Products();
        products.setId(1L);
        products.setName("Televizyon");
        products.setDescription("Test 123");
        products.setImagePath("www.google.com");
        products.setStockQuantity(20L);
        products.setCategories(categories);
        products.setPrice(BigDecimal.valueOf(25));

        productDetails = new ProductDetails();
        productDetails.setSize("xl");
        productDetails.setColor("purple");
        productDetails.setStock(20L);

        List<ProductDetailResponse> productDetailResponseList = new ArrayList<>();
        productDetailResponse = new ProductDetailResponse(productDetails.getSize(), productDetails.getColor(), productDetails.getStock());
        productDetailResponseList.add(productDetailResponse);

        productResponse = new ProductResponse(
                products.getId(),
                products.getName(),
                products.getDescription(),
                products.getImagePath(),
                products.getStockQuantity(),
                categories.getCategoryName(),
                productDetailResponseList,
                products.getPrice()
        );
    }

    public static String jsonToString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    @DisplayName("ADMIN ile ürün postu oluyor")
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void saveProductForAdmin() throws Exception {
        when(categoryService.find(1L)).thenReturn(categories);
        when(productService.save(any(Products.class))).thenReturn(products);
// SecurityMockMvcRequestPostProcessors : test sırasında bir CSRF token oluşturur ve isteğe ekler.
        mockMvc.perform(post("/shop/1/products")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(products))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Televizyon"));
    }

    @DisplayName("User ile ürün post olmuyor")
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void saveProductForUser() throws Exception{
        when(categoryService.find(1L)).thenReturn(categories);
        when(productService.save(any(Products.class))).thenReturn(products);
        mockMvc.perform(post("/shop/1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(products))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @DisplayName("GET /shop/1/products ile ürün bilgisi al")
    @WithMockUser(roles = "ANONYMOUS")
    @Test
    void getProductById() throws Exception {
        when(productService.findById(1L)).thenReturn(products);

        mockMvc.perform(get("/shop/products/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(products.getId()))
                .andExpect(jsonPath("$.name").value(products.getName()));

    }
}