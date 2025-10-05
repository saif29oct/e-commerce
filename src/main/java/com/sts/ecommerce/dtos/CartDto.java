package com.sts.ecommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartDto {
    private UUID id;
    private List<CartItemDto> items;
    private BigDecimal totalPrice;
    private LocalDate dateCreated;
}