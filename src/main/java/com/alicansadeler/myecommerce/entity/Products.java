package com.alicansadeler.myecommerce.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "products", schema = "public")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
// CATEGORY
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "category_id")
    private Categories categories;
// PRODUCTdetails
    // TODO detail tablosu parçalanmalı ** sunumdan sonra
    @JsonManagedReference
    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ProductDetails> productDetails = new ArrayList<>();
// ORDER
    @ManyToMany(mappedBy = "products")
    private Set<Order> orders = new HashSet<>();


    @Column(name = "name")
    @NotNull(message = "Product name cannot be null")
    @NotBlank(message = "Product name cannot be blank")
    @Size(max = 100, message = "Product name cannot be more than 100 characters")
    private String name;

    @Column(name = "description")
    @Size(max = 255, message = "Description text cannot be more than 100 characters")
    private String description;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "created_at", updatable = false)
    @NotNull
    private LocalDateTime createdAt;

    @Column(name = "price")
    private BigDecimal price;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @Column(name = "stock_quantity")
    @Min(value = 0, message = "Stock quantity cannot be negative")
    private Long stockQuantity;

    @Column(name = "order_count")
    @Min(value = 0, message = "Order count cannot be negative")
    private Integer orderCount = 0;

    public void addProductDetail(ProductDetails detail) {
        productDetails.add(detail);
        detail.setProducts(this);
    }

    public void removeProductDetail(ProductDetails detail) {
        productDetails.remove(detail);
        detail.setProducts(null);
    }
}
