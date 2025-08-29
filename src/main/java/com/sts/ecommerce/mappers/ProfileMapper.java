package com.sts.ecommerce.mappers;

import com.sts.ecommerce.dtos.ProfileDto;
import com.sts.ecommerce.entities.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author saif
 */
@Mapper
public interface ProfileMapper {
    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    ProfileDto profileToProfileDto(Profile profile);

    Profile profileDtoToProfile(ProfileDto profileDto);
}