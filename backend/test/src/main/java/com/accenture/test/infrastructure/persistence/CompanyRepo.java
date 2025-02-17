package com.accenture.test.infrastructure.persistence;

import com.accenture.test.infrastructure.entity.CompanyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 *
 * The company JPA repository.
 *
 * @author Marcus Nastasi
 * @version 1.0.1
 * @since 2024
 */
@Repository
public interface CompanyRepo extends JpaRepository<CompanyEntity, UUID> {

    /**
     *
     * This query allows to retrieve company entity objects paginated and filtered.
     *
     * @param name the name filter.
     * @param cnpj cnjp filter.
     * @param cep the postal code filter.
     * @param pageable the pageable object.
     *
     * @return A page of company entities.
     */
    @Query(nativeQuery = true, value = "SELECT c.* FROM company c " +
            "WHERE(:name IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND (:cnpj IS NULL OR c.cnpj LIKE CONCAT('%', :cnpj, '%')) " +
            "AND (:cep IS NULL OR c.cep LIKE CONCAT('%', :cep, '%'));")
    Page<CompanyEntity> filter(
            @Param("name") String name,
            @Param("cnpj") String cnpj,
            @Param("cep") String cep,
            Pageable pageable
    );
}
