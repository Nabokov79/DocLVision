package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.dataservice.model.Branch;
import ru.nabokovsg.dataservice.model.Department;

import java.util.Set;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Set<Department> findByBranch(Branch branch);
}