package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nabokovsg.dataservice.model.DefectParameter;

import java.util.List;
import java.util.Set;

public interface DefectParameterRepository extends JpaRepository<DefectParameter, Long> {

    @Query("select p from DefectParameter p where p.parametersName in :parametersNames")
    Set<DefectParameter> findDefectParameterByParametersName(@Param("parametersNames") List<String> parametersNames);
}