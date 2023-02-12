package com.example.cart.controller;

import com.example.cart.model.Cart;
import com.example.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartController {
    @Autowired
    private CartService cartService;
    @PatchMapping("/cart/{userId}/{productId}")
    public ResponseEntity<Cart> addToCart(@PathVariable Long userId, @PathVariable Long productId){
        Cart saveCart = cartService.addToCart(userId, productId);
        return new ResponseEntity<>(saveCart, HttpStatus.CREATED);
    }
    @DeleteMapping("/cartRemove/{userId}")
    public ResponseEntity<Cart> removedProductsCart(@PathVariable Long userId, @RequestBody Cart cartDetail){
        Cart saveCart = cartService.removedProductsCart(userId, cartDetail);
        return new ResponseEntity<>(saveCart, HttpStatus.CREATED);
    }

    @GetMapping("/carts/{userId}")
    public ResponseEntity <String> getProductById(@PathVariable("userId") Long userId){
        return ResponseEntity.ok(cartService.listCart(userId));
    }

    @PutMapping("/update/{id}")
    public Cart updateCart(@PathVariable Long id, @RequestBody Cart cart){
        cart.setCartId(id);
        return cartService.updateCart(id, cart);
    }



}
