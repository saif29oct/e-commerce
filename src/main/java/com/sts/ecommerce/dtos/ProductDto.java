package com.sts.ecommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author saif
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private List<CategoryDto> categories;
}
