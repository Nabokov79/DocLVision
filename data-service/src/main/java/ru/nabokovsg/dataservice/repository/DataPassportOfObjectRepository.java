package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.dataservice.model.DataPassportOfObject;

public interface DataPassportOfObjectRepository extends JpaRepository<DataPassportOfObject, Long> {
}