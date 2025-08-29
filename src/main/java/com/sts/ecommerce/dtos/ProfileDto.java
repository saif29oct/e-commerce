package com.sts.ecommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author saif
 */
@AllArgsConstructor
@Getter
public class ProfileDto {
    private String bio;
    private String phoneNumber;
    private String dateOfBirth;
    private Integer loyaltyPoints;
}