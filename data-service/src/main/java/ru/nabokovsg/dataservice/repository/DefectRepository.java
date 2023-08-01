package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.dataservice.model.Defect;

public interface DefectRepository extends JpaRepository<Defect, Long> {
}