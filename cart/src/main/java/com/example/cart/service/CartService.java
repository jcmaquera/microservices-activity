package com.example.cart.service;

import com.example.cart.dto.ProductDTO;
import com.example.cart.dto.ResponseDTO;
import com.example.cart.model.Cart;
import com.example.cart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private RestTemplate restTemplate;

    public Cart addToCart(Long userId, Long productId) {
        if (getProductById(productId) != null){
            Cart cart = new Cart();
            cart.setUserId(userId);
            cart.setProductId(productId);
            cart.setQuantity(+1);
            cartRepository.save(cart);
        }
        return null;

    }
    public Cart removedProductsCart(Long userId, Cart cartDetail) {
        if (getProductById(cartDetail.getProductId()) != null) {
            Cart cart = cartRepository.findById(cartDetail.getCartId()).orElse(null);


            cart.setUserId(cartDetail.getUserId());
            cart.setProductId(cartDetail.getProductId());
            cart.setQuantity(cartDetail.getQuantity());
            cartRepository.delete(cart);
        }
        return null;
    }

    public Boolean findCartId (Long cartId){
        cartRepository.findById(cartId);
        return true;
    }

    public Cart updateCart(Long id, Cart cart) {
        cart.setCartId(id);
        return cartRepository.save(cart);
    }
    public String listCart(Long userId){
        if(cartRepository.findByUserId((userId)) != null){
            String output="";
            for(int i = 0; i < getProductsDetails().size() ;i++){
                for(int j = 0; j < cartRepository.findByUserIdAndProductId(userId, getProductsDetails().get(i).getProductId()).size(); j++ )
                {
                    output+=getProductsDetails().get(i).getName()+" \n";
                    output+=getProductsDetails().get(i).getProductId()+" \n";
                    output+=getProductsDetails().get(i).getDescription()+" \n";
                    output+=getProductsDetails().get(i).getPrice()+" \n";
                }
            }
            return output;
        }

        return getProductsDetails().get(0).getName().toString();
    }


    public ResponseDTO getProductById(Long productId) {
        ResponseDTO responseDto = restTemplate.getForObject("http://localhost:8081/api/getProductById/" + productId,
                ResponseDTO.class);
        return responseDto;
    }
    public List<ProductDTO> getProductsDetails(){ProductDTO[] productDto = restTemplate.getForObject("http://localhost:8081/api/getAllProducts",
            ProductDTO[].class );
        return Arrays.asList(productDto);
    }
}
