package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.dataservice.model.Element;

public interface ElementRepository extends JpaRepository<Element, Long> {
}