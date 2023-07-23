package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.dataservice.model.Organization;
import java.util.Set;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    boolean existsByOrganization(String organization);

    @Query("select o.organization, o.shortNameOrganization, o.address from Organization o")
    Set<Organization> findAllOrganization();
}