package com.alicansadeler.myecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "addresses", schema = "public")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    @NotNull(message = "Title cannot be null")
    @NotBlank(message = "Title cannot be blank")
    @Size(max = 100, message = "Title cannot be more than 100 characters")
    private String title;

    @Column(name = "address_text")
    @NotNull(message = "Address text cannot be null")
    @NotBlank(message = "Address text cannot be blank")
    @Size(max = 255, message = "Address text cannot be more than 255 characters")
    private String addressText;

    @Column(name = "country")
    @NotNull(message = "Country cannot be null")
    @NotBlank(message = "Country cannot be blank")
    @Size(max = 100, message = "Country cannot be more than 100 characters")
    private String country;

    @Column(name = "city")
    @NotNull(message = "City cannot be null")
    @NotBlank(message = "City cannot be blank")
    @Size(max = 100, message = "City cannot be more than 100 characters")
    private String city;

    @Column(name = "postal_code")
    @NotNull(message = "Postal code cannot be null")
    @Size(max = 50, message = "Postal code cannot be more than 50 characters")
    private String postalCode;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
}
