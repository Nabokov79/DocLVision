package ru.nabokovsg.dataservice.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.certificate.CertificateDto;
import ru.nabokovsg.dataservice.dto.certificate.NewCertificateDto;
import ru.nabokovsg.dataservice.dto.certificate.UpdateCertificateDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mapper.CertificateMapper;
import ru.nabokovsg.dataservice.model.*;
import ru.nabokovsg.dataservice.repository.CertificateRepository;
import ru.nabokovsg.dataservice.repository.ControlTypeRepository;
import ru.nabokovsg.dataservice.repository.EmployeeRepository;
import ru.nabokovsg.dataservice.repository.OrganizationRepository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository repository;
    private final OrganizationRepository organizationRepository;
    private final ControlTypeRepository controlTypeRepository;
    private final EmployeeRepository employeeRepository;
    private final CertificateMapper mapper;
    private final EntityManager entityManager;

    @Override
    public List<CertificateDto> save(List<NewCertificateDto> newCertificates) {
        if (newCertificates.isEmpty()) {
            throw new NotFoundException("new certificates not found for save");
        }
        List<Certificate> certificatesDb = setCertificatesValue(newCertificates, null);
        return mapper.mapToCertificatesDto(repository.saveAll(certificatesDb));
    }

    @Override
    public List<CertificateDto> update(List<UpdateCertificateDto> updateCertificates) {
        if (updateCertificates.isEmpty()) {
            throw new NotFoundException("certificates not found for update");
        }
        List<Certificate> certificatesDb = setCertificatesValue(null, updateCertificates);
        return mapper.mapToCertificatesDto(repository.saveAll(certificatesDb));
    }

    @Override
    public List<CertificateDto> getAll(Long employeeId, LocalDate date) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (employeeId != null) {
            booleanBuilder.and(QCertificate.certificate.employee.id.eq(employeeId));
        }
        if (date != null) {
            booleanBuilder.and(QCertificate.certificate.end.before(date));
        }
        QCertificate certificates = QCertificate.certificate;
        JPAQueryFactory qf = new JPAQueryFactory(entityManager);
        JPAQuery<Certificate> query = qf.from(certificates)
                .select(certificates)
                .where(booleanBuilder);
        return mapper.mapToCertificatesDto(query.fetch());
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("certificate with id = %s not found for delete", id));
    }

    public List<Certificate> setCertificatesValue(List<NewCertificateDto> newCertificates,
                                                  List<UpdateCertificateDto> updateCertificates) {
        List<Certificate> certificates = new ArrayList<>();
        Map<Long, ControlType> typeControls = controlTypeRepository.findAll().stream()
                                             .collect(Collectors.toMap(ControlType::getId, controlType -> controlType));
        Map<Long, Organization> organizations = organizationRepository.findAll().stream()
                                          .collect(Collectors.toMap(Organization::getId, organization -> organization));
        Map<Long, Employee> users = employeeRepository.findAll().stream().collect(Collectors.toMap(Employee::getId, user -> user));
        if (newCertificates != null) {
            for (NewCertificateDto certificateDto : newCertificates) {
                Certificate certificate = mapper.mapToCertificate(certificateDto);
                certificate.setControlType(typeControls.get(certificateDto.getControlTypeId()));
                certificate.setOrganization(organizations.get(certificateDto.getOrganizationId()));
                certificate.setEmployee(users.get(certificateDto.getUserId()));
                certificates.add(certificate);
            }
        }
        if (updateCertificates != null) {
            for (UpdateCertificateDto certificateDto : updateCertificates) {
                Certificate certificate = mapper.mapToUpdateCertificate(certificateDto);
                certificate.setControlType(typeControls.get(certificateDto.getControlTypeId()));
                certificate.setOrganization(organizations.get(certificateDto.getOrganizationId()));
                certificate.setEmployee(users.get(certificateDto.getUserId()));
                certificates.add(certificate);
            }
        }
        return certificates;
    }
}