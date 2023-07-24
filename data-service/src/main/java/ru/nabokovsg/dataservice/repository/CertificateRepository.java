package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.dataservice.model.Certificate;

import javax.transaction.Transactional;

public interface CertificateRepository extends JpaRepository<Certificate, Long>{

    @Modifying
    @Transactional
    @Query("delete from Certificate c where c.employee.id = ?1")
    void deleteAllByEmployeeId(Long employeeId);
}