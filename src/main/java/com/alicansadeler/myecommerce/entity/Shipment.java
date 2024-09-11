package com.alicansadeler.myecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "shipment", schema = "public")
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "order_id", unique = true)
    private Order order;

    @Column(name = "shipment_type")
    @NotNull(message = "Shipment type cannot be null")
    @NotBlank(message = "Shipment type cannot be blank")
    @Size(max = 50, message = "Shipment type cannot be more than 50 characters")
    private String shipmentType;

    @Column(name = "discount")
    @Pattern(regexp = "^\\d{1,2}%$", message = "Discount must be in the format of '0%' to '99%'")
    @Size(max = 3, message = "Discount cannot be more than 3 characters")
    private String discount = "%0";

    @Column(name = "delivery_cost")
    @NotNull(message = "Delivery cost cannot be null")
    private BigDecimal deliveryCost;

    @Column(name = "product_price")
    @NotNull(message = "Product price cannot be null")
    private BigDecimal productPrice;


    @Column(name = "total_price")
    @NotNull(message = "Total price cannot be null")
    private BigDecimal totalPrice;

}
