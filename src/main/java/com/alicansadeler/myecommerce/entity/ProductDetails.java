package com.alicansadeler.myecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "product_details", schema = "public")
public class ProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "product_id", unique = true)
    private Products products;

    @Column(name = "size")
    @Size(max = 10, message = "Product size cannot be more than 10 characters")
    private String size;


    @Column(name = "color")
    @Size(max = 50, message = "Product color cannot be more than 50 characters")
    private String color;


    @Column(name = "stock")
    @Min(value= 0, message = "Stock cannot be less than 0")
    private Long stock;
}
