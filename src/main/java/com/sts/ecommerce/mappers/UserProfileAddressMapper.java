package com.sts.ecommerce.mappers;

import com.sts.ecommerce.dtos.UserProfileAddressDto;
import com.sts.ecommerce.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author saif
 */
@Mapper(uses = {ProfileMapper.class, AddressMapper.class})
public interface UserProfileAddressMapper {
    UserProfileAddressMapper INSTANCE = Mappers.getMapper(UserProfileAddressMapper.class);

    UserProfileAddressDto userToUserProfileAddressDto(User user);
}