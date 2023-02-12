package com.example.product.service;

import com.example.product.dto.ProductDTO;
import com.example.product.dto.ResponseDTO;
import com.example.product.dto.UserDTO;
import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RestTemplate restTemplate;

    public Product updateProduct(Long productId, Product product) {
        product.setProductId(productId);
        return productRepository.save(product);
    }

    public void deleteById(Long productId) {
        productRepository.deleteById(productId);
    }


    public ResponseDTO getProductsById(Long productId) {
        ResponseDTO responseDTO = new ResponseDTO();
        Optional<Product> product = productRepository.findById(productId);
        ProductDTO productDTO = mapToUser(product.get());

        ResponseEntity<UserDTO> responseEntity = restTemplate
                .getForEntity("http://localhost:8080/api/getUser/"+ product.get().getUserId(),
                        UserDTO.class);

        UserDTO userDTO = responseEntity.getBody();

        System.out.println(responseEntity.getStatusCode());


        responseDTO.setProductDto(productDTO);
        responseDTO.setUserDto(userDTO);
        return responseDTO;
    }
    private ProductDTO mapToUser(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setImageName(product.getImageName());
        return productDTO;
    }
    public String getUserRole(Long userId){
        UserDTO user = restTemplate.getForObject("http://localhost:8080/api/getUser/"+userId, UserDTO.class);
        if(user == null){
            return null;
        }
        return user.getRole();
    }
    public String addProducts(Long userId, Product product) {
        if(getUserRole(userId) == null){
            return HttpStatus.NOT_FOUND.toString();
        }
        if(getUserRole(userId).equals("buyer")){
            return HttpStatus.UNAUTHORIZED.toString();
        }
        product.setUserId(userId);
        productRepository.save(product);
        return HttpStatus.OK.toString();
    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


}
