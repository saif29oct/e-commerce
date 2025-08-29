package com.sts.ecommerce.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

/**
 * @author saif
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileAddressDto {
    private Long id;
    private String name;
    private String email;
    @JsonIgnore
    private String password;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ProfileDto profile;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<AddressDto> addresses;
}