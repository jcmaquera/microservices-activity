package com.example.product.controller;

import com.example.product.dto.ResponseDTO;
import com.example.product.model.Product;
import com.example.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/addProducts")
    public String addProducts(@RequestParam Long userId, @RequestBody Product product){
        return productService.addProducts(userId, product);
    }

    @PutMapping("/updateProducts/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product){
        product.setProductId(id);
        return productService.updateProduct(id, product);
    }
    @DeleteMapping("/deleteProducts/{id}")
    public void deleteProducts(@PathVariable Long id){
        productService.deleteById(id);
    }
    @GetMapping("/getProductById/{id}")
    public ResponseEntity<ResponseDTO> getUserById(@PathVariable("id") Long productId){
        ResponseDTO responseDTO = productService.getProductsById(productId);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/getAllProducts")
    public @ResponseBody List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

}
