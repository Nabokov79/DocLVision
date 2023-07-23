package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.dataservice.model.City;

public interface CityRepository extends JpaRepository<City, Long> {

    boolean existsByCity(String city);
}