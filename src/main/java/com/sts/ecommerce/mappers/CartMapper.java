package com.sts.ecommerce.mappers;

import com.sts.ecommerce.dtos.CartDto;
import com.sts.ecommerce.entities.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface CartMapper {
    public CartDto toDto(Cart cart);
}