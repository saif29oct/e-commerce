package com.sts.ecommerce.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author saif
 */
@Getter
@AllArgsConstructor
public class CategoryDto {
    @JsonIgnore
    private Long id;
    private String name;
}
