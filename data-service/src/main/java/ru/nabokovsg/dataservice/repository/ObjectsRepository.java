package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.dataservice.model.Objects;


public interface ObjectsRepository extends JpaRepository<Objects, Long> {
}