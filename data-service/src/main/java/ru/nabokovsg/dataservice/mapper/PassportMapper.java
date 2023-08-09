package ru.nabokovsg.dataservice.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.passport.PassportDto;
import ru.nabokovsg.dataservice.model.Passport;

@Mapper(componentModel = "spring")
public interface PassportMapper {

    PassportDto mapToPassportDto(Passport passport);
}