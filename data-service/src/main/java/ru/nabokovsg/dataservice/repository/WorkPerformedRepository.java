package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.dataservice.model.WorkPerformed;

public interface WorkPerformedRepository extends JpaRepository<WorkPerformed, Long> {
}