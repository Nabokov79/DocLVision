package ru.nabokovsg.dataservice.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.reportData.DocumentInformationDto;
import ru.nabokovsg.dataservice.dto.reportData.DocumentSearchParameters;
import ru.nabokovsg.dataservice.exceptions.BadRequestException;
import ru.nabokovsg.dataservice.mapper.DocumentInformationMapper;
import ru.nabokovsg.dataservice.model.Application;
import ru.nabokovsg.dataservice.model.DocumentInformation;
import ru.nabokovsg.dataservice.model.QDocumentInformation;
import ru.nabokovsg.dataservice.model.Statuses;
import ru.nabokovsg.dataservice.repository.DocumentInformationRepository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

@Service
@RequiredArgsConstructor
public class DocumentInformationServiceImpl implements DocumentInformationService {

    private final DocumentInformationRepository repository;
    private final DocumentInformationMapper mapper;
    private final EntityManager entityManager;

    @Override
    public List<DocumentInformationDto> getAll(DocumentSearchParameters parameters) {
        QDocumentInformation document = QDocumentInformation.documentInformation;
        JPAQueryFactory qf = new JPAQueryFactory(entityManager);
        JPAQuery<DocumentInformation> query = qf.from(document)
                                       .select(document)
                                       .where(getPredicate(parameters));
        return mapper.mapToDocumentInformationDto(query.fetch());
    }

    @Override
    public void create(List<Application> applications) {
        List<DocumentInformation> documents = new ArrayList<>();
        Integer number = getMaxNumber();
        for (Application application : applications) {
            DocumentInformation document = mapper.mapToDocumentInformation(application);
            document.setStatus(Statuses.WAITING);
            if (number == null) {
                number = 0;
            }
            document.setDocumentNumber(++number);
            documents.add(document);
        }
        repository.saveAll(documents);
    }

    private Integer getMaxNumber() {
        LocalDate now = LocalDate.now();
        QDocumentInformation document = QDocumentInformation.documentInformation;
        return new JPAQueryFactory(entityManager).from(document)
                            .select(document.documentNumber.max())
                            .where(QDocumentInformation.documentInformation.date.after(now.with(firstDayOfYear()))
                                  .and(QDocumentInformation.documentInformation.date.before(now.with(lastDayOfYear()))))
                            .fetchOne();
    }

    private BooleanBuilder getPredicate(DocumentSearchParameters parameters) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (parameters.getObjectId() != null) {
            booleanBuilder.and(QDocumentInformation.documentInformation.object.id.eq(parameters.getObjectId()));
        }
        if (parameters.getEmployeeId() != null) {
            booleanBuilder.and(QDocumentInformation.documentInformation.employee.id.eq(parameters.getEmployeeId()));
        }
        if (parameters.getAddressId() != null) {
            booleanBuilder.and(QDocumentInformation.documentInformation.address.id.eq(parameters.getAddressId()));
        }
        if (parameters.getDocumentNumber() != null) {
            booleanBuilder.and(QDocumentInformation.documentInformation.documentNumber.eq(parameters.getDocumentNumber()));
        }
        if (parameters.getStartDate() != null) {
            booleanBuilder.and(QDocumentInformation.documentInformation.endDate.before(parameters.getStartDate().minusDays(1)));
        }
        if (parameters.getEndDate() != null) {
            booleanBuilder.and(QDocumentInformation.documentInformation.endDate.after(parameters.getEndDate().plusDays(1)));
        }
        if (parameters.getStatus() != null) {
            Statuses status = Statuses.from(parameters.getStatus())
                    .orElseThrow(() -> new BadRequestException("Unknown type object: " + parameters.getStatus()));
            booleanBuilder.and(QDocumentInformation.documentInformation.status.eq(status));
        }
        return booleanBuilder;
    }
}