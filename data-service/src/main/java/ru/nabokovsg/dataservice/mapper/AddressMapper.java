package ru.nabokovsg.dataservice.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.address.AddressDto;
import ru.nabokovsg.dataservice.dto.address.NewAddressDto;
import ru.nabokovsg.dataservice.dto.address.UpdateAddressDto;
import ru.nabokovsg.dataservice.model.Address;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address mapToNewAddress(NewAddressDto addressDto);

    AddressDto mapToAddressDto(Address address);

    Address mapToUpdateAddress(UpdateAddressDto addressDto);

    List<AddressDto> mapToAddressDto(List<Address> addresses);
}