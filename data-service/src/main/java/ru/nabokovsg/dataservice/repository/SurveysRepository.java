package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.dataservice.model.Survey;

public interface SurveysRepository extends JpaRepository<Survey, Long> {
}