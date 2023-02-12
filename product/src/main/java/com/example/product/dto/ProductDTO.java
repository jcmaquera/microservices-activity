package com.example.product.dto;

import lombok.Data;

@Data
public class ProductDTO {

    private Long productId;
    private String name;
    private Double price;
    private Integer quantity;
    private String description;
    private String imageName;
    private Long userId;
}
