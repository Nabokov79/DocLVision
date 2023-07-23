package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.dataservice.model.Building;
import ru.nabokovsg.dataservice.model.Department;

import java.util.Set;

public interface BuildingRepository extends JpaRepository<Building, Long> {

    Set<Building> findAllByDepartment(Department department);
}