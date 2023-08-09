package ru.nabokovsg.dataservice.service;

import ru.nabokovsg.dataservice.dto.passport.PassportDto;
import ru.nabokovsg.dataservice.model.Objects;
import ru.nabokovsg.dataservice.model.Passport;
import java.util.List;

public interface PassportService {

    List<Passport> save(List<Objects> objects);

    List<Passport> getAll(List<Objects> objects);

    PassportDto get(Long id);
}