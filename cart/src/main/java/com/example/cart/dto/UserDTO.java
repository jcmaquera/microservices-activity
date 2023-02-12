package com.example.cart.dto;

import lombok.Data;

@Data
public class UserDTO {

    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
}
