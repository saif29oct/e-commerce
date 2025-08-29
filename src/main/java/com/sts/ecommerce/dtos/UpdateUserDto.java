package com.sts.ecommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * DTO for updating user information
 * @author saif
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {
    private String name;
    private String email;
}