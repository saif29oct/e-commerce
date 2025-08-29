package com.sts.ecommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author saif
 */
@AllArgsConstructor
@Getter
public class AddressDto {
    private String street;
    private String city;
    private String zip;
}