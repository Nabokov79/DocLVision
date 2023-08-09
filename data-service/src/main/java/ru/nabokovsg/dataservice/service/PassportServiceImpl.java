package ru.nabokovsg.dataservice.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.passport.PassportDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mapper.PassportMapper;
import ru.nabokovsg.dataservice.model.*;
import ru.nabokovsg.dataservice.repository.PassportRepository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PassportServiceImpl implements PassportService {

    private final PassportRepository repository;
    private final PassportMapper mapper;
    private final EntityManager entityManager;

    @Override
    public List<Passport> save(List<Objects> objects) {
        Map<Long, Passport> passportsDb = getAll(objects).stream().collect(Collectors.toMap(p -> p.getObject().getId(), p -> p ));
        List<Passport> passports = new ArrayList<>();
        if (passportsDb.size() != objects.size()) {
            objects = objects.stream().filter(o -> passportsDb.get(o.getId()) == null).toList();
        } else {
            return new ArrayList<>(passportsDb.values());
        }
        for (Objects object : objects) {
            Passport passport = passportsDb.get(object.getId());
            if (passport == null) {
                passport = new Passport();
                passport.setObject(object);
                passports.add(passport);
            }
        }
        for (Passport passport : repository.saveAll(passports)) {
            passportsDb.put(passport.getObject().getId(), passport);
        }
        return new ArrayList<>(passportsDb.values());
    }

    @Override
    public PassportDto get(Long id) {
        return mapper.mapToPassportDto(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Passport object with id=%s not found", id))));
    }

    @Override
    public List<Passport> getAll(List<Objects> objects) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(QPassport.passport.object.in(objects));
        QPassport passport = QPassport.passport;
        JPAQueryFactory qf = new JPAQueryFactory(entityManager);
        JPAQuery<Passport> query = qf.from(passport)
                .select(passport)
                .where(booleanBuilder);
        return query.fetch();
    }
}