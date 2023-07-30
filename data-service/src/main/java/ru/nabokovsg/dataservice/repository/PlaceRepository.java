package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.dataservice.model.Place;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}