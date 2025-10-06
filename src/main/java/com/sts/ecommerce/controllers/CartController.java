package com.sts.ecommerce.controllers;

import ch.qos.logback.classic.Logger;
import com.sts.ecommerce.dtos.CartDto;
import com.sts.ecommerce.entities.Cart;
import com.sts.ecommerce.mappers.CartMapper;
import com.sts.ecommerce.repsitories.CartRepository;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
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

    private final Logger LOGGER = (Logger) LoggerFactory.getLogger(CartController.class);

    @PostMapping("/create")
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