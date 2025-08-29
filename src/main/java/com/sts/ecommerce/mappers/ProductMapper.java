package com.sts.ecommerce.mappers;

import com.sts.ecommerce.dtos.ProductDto;
import com.sts.ecommerce.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author saif
 */
@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "categories", target = "categories")
    ProductDto productToProductDto(Product product);
}