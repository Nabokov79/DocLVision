package ru.nabokovsg.dataservice.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.application.ApplicationDto;
import ru.nabokovsg.dataservice.dto.application.ApplicationSearchParameters;
import ru.nabokovsg.dataservice.dto.application.NewApplicationDto;
import ru.nabokovsg.dataservice.dto.application.UpdateApplicationDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mapper.ApplicationMapper;
import ru.nabokovsg.dataservice.mapper.ObjectsIdsMapper;
import ru.nabokovsg.dataservice.model.*;
import ru.nabokovsg.dataservice.repository.ApplicationRepository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository repository;
    private final ApplicationMapper mapper;
    private final EntityManager entityManager;
    private final DocumentInformationService reportDataService;
    private final BuilderFactoryService factoryService;
    private final ObjectsIdsMapper objectsIdsMapper;

    @Override
    public List<ApplicationDto> save(List<NewApplicationDto> applicationsDto) {
        Builder builder = factoryService.getBuilder(applicationsDto.stream()
                                                                   .map(objectsIdsMapper::mapFromNewApplicationDto)
                                                                   .toList()
                                                 , BuilderType.APPLICATIONS);
        List<Application> applications = new ArrayList<>();
        for (NewApplicationDto applicationDto : applicationsDto) {
            applications.add(set(mapper.mapToNewApplication(applicationDto)
                               , builder
                               , objectsIdsMapper.mapFromNewApplicationDto(applicationDto)));
        }
        applications = repository.saveAll(applications);
        reportDataService.create(applications.stream().sorted(Comparator.comparing(Application::getDate)).toList());
        return mapper.mapToApplicationsDto(applications);
    }

    @Override
    public List<ApplicationDto> update(List<UpdateApplicationDto> applicationsDto) {
        validateIds(applicationsDto.stream().map(UpdateApplicationDto::getId).toList());
        Builder builder = factoryService.getBuilder(applicationsDto.stream()
                                                                   .map(objectsIdsMapper::mapFromUpdateApplicationDto)
                                                                   .toList()
                                                 , BuilderType.APPLICATIONS);
        List<Application> applications = new ArrayList<>();
        for (UpdateApplicationDto applicationDto : applicationsDto) {
            applications.add(set(mapper.mapToUpdateApplication(applicationDto)
                               , builder
                               , objectsIdsMapper.mapFromUpdateApplicationDto(applicationDto)));
        }
        applications = repository.saveAll(applications);
        reportDataService.create(applications.stream().sorted(Comparator.comparing(Application::getDate)).toList());
        return mapper.mapToApplicationsDto(applications);
    }

    @Override
    public ApplicationDto get(Long id) {
        return mapper.mapToApplicationDto((repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("application with id=%s not found",id)))));
    }

    @Override
    public List<ApplicationDto> getAll(ApplicationSearchParameters parameters) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(parameters.getAddressId() != null) {
            booleanBuilder.and(QApplication.application.address.id.eq(parameters.getAddressId()));
        }
        if(parameters.getStartDate() != null) {
            booleanBuilder.and(QApplication.application.date.after(parameters.getStartDate()));
        }
        if(parameters.getEndDate() != null) {
            booleanBuilder.and(QApplication.application.date.before(parameters.getEndDate()));
        }
        if(parameters.getObjectId() != null) {
            booleanBuilder.and(QApplication.application.object.id.eq(parameters.getObjectId()));
        }
        QApplication application = QApplication.application;
        JPAQueryFactory qf = new JPAQueryFactory(entityManager);
        JPAQuery<Application> query = qf.from(application)
                .select(application)
                .where(booleanBuilder);
        return mapper.mapToApplicationsDto(query.fetch());
    }

    private void validateIds(List<Long> ids) {
        Map<Long, Application> applications = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(Application::getId, m -> m));
        if (applications.size() != ids.size() || applications.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(applications.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("application with ids= %s not found", ids));
        }
    }

    private Application set(Application application, Builder builder, ObjectsIds ids) {
        application.setAddress(builder.getAddresses().get(ids.getAddressId()));
        application.setObject(builder.getObjects().get(ids.getObjectId()));
        application.setWorkPerformed(builder.getWorksPerformed().get(ids.getWorkPerformedId()));
        application.setEmployees(builder.getEmployees().values()
                                                       .stream()
                                                       .filter(e -> ids.getEmployeesIds().contains(e.getId()))
                                                       .toList());
        return application;
    }
}