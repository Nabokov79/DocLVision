package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nabokovsg.dataservice.model.Documentation;

import java.util.List;
import java.util.Set;

public interface DocumentationRepository extends JpaRepository<Documentation, Long> {

    @Query("select d from Documentation d where d.title in :titles")
    Set<Documentation> findAllByTitle(@Param("titles") List<String> titles);
}