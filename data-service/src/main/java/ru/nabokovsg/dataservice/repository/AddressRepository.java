package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.nabokovsg.dataservice.model.Address;

import java.util.Set;

public interface AddressRepository extends JpaRepository<Address, Long>, QuerydslPredicateExecutor<Address> {

    Set<Address> findByCityId(Long cityId);
}