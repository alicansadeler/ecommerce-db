package com.alicansadeler.myecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
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
    @OneToOne(mappedBy = "products", cascade = CascadeType.ALL)
    private ProductDetails productDetails;
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
}
