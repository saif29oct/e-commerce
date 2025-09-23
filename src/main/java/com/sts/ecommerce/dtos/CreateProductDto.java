package com.sts.ecommerce.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author saif
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CreateProductDto {
    @NotBlank
    private String name;
    private String description;
    private Double price;
}
