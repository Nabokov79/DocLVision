package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.dataservice.model.Repair;

public interface RepairRepository extends JpaRepository<Repair, Long> {
}
