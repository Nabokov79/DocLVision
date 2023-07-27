package ru.nabokovsg.dataservice.service;

import ru.nabokovsg.dataservice.dto.certificate.CertificateDto;
import ru.nabokovsg.dataservice.dto.certificate.NewCertificateDto;
import ru.nabokovsg.dataservice.dto.certificate.UpdateCertificateDto;

import java.time.LocalDate;
import java.util.List;

public interface CertificateService {

    List<CertificateDto> save(List<NewCertificateDto> certificatesDto);

    List<CertificateDto> update(List<UpdateCertificateDto> certificatesDto);

    List<CertificateDto> getAll(Long employeeId, LocalDate date);

    void delete(Long id);
}