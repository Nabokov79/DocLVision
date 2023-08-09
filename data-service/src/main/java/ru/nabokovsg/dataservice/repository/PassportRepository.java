package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.dataservice.model.Passport;

public interface PassportRepository extends JpaRepository<Passport, Long> {
}