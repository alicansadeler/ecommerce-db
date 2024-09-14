package com.alicansadeler.myecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "categories", schema = "public")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name")
    @NotNull(message = "Category name cannot be null")
    @NotBlank(message = "Category name cannot be blank")
    @Size(max = 100, message = "Category name cannot be more than 100 characters")
    private String categoryName;

    @Column(name = "description")
    @Size(max = 255, message = "Description  cannot be more than 255 characters")
    private String description;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "categories")
    private Set<Products> products = new HashSet<>();

}
