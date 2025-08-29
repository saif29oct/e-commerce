package com.sts.ecommerce.mappers;

import com.sts.ecommerce.dtos.AddressDto;
import com.sts.ecommerce.entities.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author saif
 */
@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressDto addressToAddressDto(Address address);

    Address addressDtoToAddress(AddressDto addressDto);
}