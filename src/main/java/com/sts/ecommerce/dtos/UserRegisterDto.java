package com.sts.ecommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author saif
 */
@Getter
@AllArgsConstructor
@ToString
public class UserRegisterDto {
    private String name;
    private String email;
    private String password;
}
