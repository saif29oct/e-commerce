package com.sts.ecommerce.controllers;

import com.sts.ecommerce.dtos.CartDto;
import com.sts.ecommerce.entities.Cart;
import com.sts.ecommerce.mappers.CartMapper;
import com.sts.ecommerce.repsitories.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

@RestController
@AllArgsConstructor
@RequestMapping("/carts")
public class CartController {

    private CartRepository cartRepository;
    private CartMapper cartMapper;

    @PostMapping
    public ResponseEntity<CartDto> createCart() {
        Cart cart = new Cart();
        cart.setDateCreated(LocalDate.now());

        Cart savedCart = cartRepository.save(cart);

        CartDto cartDto = cartMapper.toDto(savedCart);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCart.getId())
                .toUri();
        
        return ResponseEntity.created(location).body(cartDto);
    }
}