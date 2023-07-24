package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.dataservice.model.Address;

import java.util.Set;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Set<Address> findByCityId(Long cityId);
}