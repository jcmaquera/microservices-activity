package com.example.product.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String name;
    private Double price;
    private Integer quantity;
    private String description;
    private String imageName;
    private Long userId;

}
