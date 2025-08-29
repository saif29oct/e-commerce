package com.sts.ecommerce.mappers;

import com.sts.ecommerce.dtos.UserDto;
import com.sts.ecommerce.dtos.UserRegisterDto;
import com.sts.ecommerce.dtos.UpdateUserDto;
import com.sts.ecommerce.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author saif
 */
@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);

    User userRegisterDtoToUser(UserRegisterDto userRegisterDto);
    
    User updateUserDtoToUser(UpdateUserDto updateUserDto);
    
    void updateUserFromUpdateUserDto(UpdateUserDto updateUserDto, @MappingTarget User user);
}