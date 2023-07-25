package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.dataservice.model.License;

public interface LicenseRepository extends JpaRepository<License, Long> {
}