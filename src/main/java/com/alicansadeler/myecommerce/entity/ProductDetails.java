package com.alicansadeler.myecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "product_details", schema = "public")
public class ProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
// TODO detay tablosu 2'ye ayrılmalı. Tablo1: color, Tablo2: size&stock(hashmap olabilir ?)
    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "product_id")
    private Products products;

    @Column(name = "size")
    @Size(max = 100, message = "Product size cannot be more than 100 characters")
    private String size;


    @Column(name = "color")
    private String color;


    @Column(name = "stock")
    @Min(value= 0, message = "Stock cannot be less than 0")
    private Long stock;
}
