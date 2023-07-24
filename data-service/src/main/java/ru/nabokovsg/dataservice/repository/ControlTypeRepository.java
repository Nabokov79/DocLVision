package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.dataservice.model.ControlType;

public interface ControlTypeRepository extends JpaRepository<ControlType, Long> {

    boolean existsByNameTypeControl(String nameTypeControl);
}