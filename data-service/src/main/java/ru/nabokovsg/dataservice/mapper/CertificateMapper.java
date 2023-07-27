package ru.nabokovsg.dataservice.mapper;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.certificate.CertificateDto;
import ru.nabokovsg.dataservice.dto.certificate.NewCertificateDto;
import ru.nabokovsg.dataservice.dto.certificate.UpdateCertificateDto;
import ru.nabokovsg.dataservice.model.Certificate;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CertificateMapper {

    List<CertificateDto> mapToCertificatesDto(List<Certificate> certificates);

    Certificate mapToNewCertificate(NewCertificateDto newCertificateDto);

    Certificate mapToUpdateCertificate(UpdateCertificateDto updateCertificateDto);
}