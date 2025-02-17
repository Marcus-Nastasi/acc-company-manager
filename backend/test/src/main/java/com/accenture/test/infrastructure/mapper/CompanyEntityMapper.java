package com.accenture.test.infrastructure.mapper;

import com.accenture.test.domain.company.Company;
import com.accenture.test.domain.supplier.Supplier;
import com.accenture.test.infrastructure.entity.CompanyEntity;
import com.accenture.test.infrastructure.entity.SupplierEntity;

import java.util.ArrayList;

/**
 *
 * The company entity mapper.
 *
 * @author Marcus Nastasi
 * @version 1.0.1
 * @since 2024
 */
public class CompanyEntityMapper {

    /**
     *
     * Map from company JPA entity to company domain object.
     *
     * @param companyEntity the entity.
     *
     * @return the domain object.
     */
    public Company mapFromEntity(CompanyEntity companyEntity) {
        return new Company(
            companyEntity.getId(),
            companyEntity.getCnpj(),
            companyEntity.getName(),
            companyEntity.getCep(),
            new ArrayList<>(companyEntity.getSuppliers().stream().map(this::mapSupplierWithoutCompanies).toList())
        );
    }

    /**
     *
     * Map from company domain object to company JPA entity.
     *
     * @param company the domain object.
     *
     * @return the entity object.
     */
    public CompanyEntity mapToEntity(Company company) {
        return new CompanyEntity(
            company.getId(),
            company.getCnpj(),
            company.getName(),
            company.getCep(),
            new ArrayList<>(company.getSuppliers().stream().map(this::mapSupplierEntityWithoutCompanies).toList()));
    }

    /**
     *
     * Mapping supplier to supplier entity without companies.
     *
     * @param supplier the supplier domain object.
     *
     * @return the supplier entity without companies.
     */
    private SupplierEntity mapSupplierEntityWithoutCompanies(Supplier supplier) {
        return new SupplierEntity(
            supplier.getId(),
            supplier.getCnpj_cpf(),
            supplier.getRg(),
            supplier.getBirth(),
            supplier.getName(),
            supplier.getEmail(),
            supplier.getCep(),
            supplier.isE_pf(),
            null
        );
    }

    /**
     *
     * Mapping supplier entity to supplier domain without companies.
     *
     * @param supplier the supplier entity object.
     *
     * @return the supplier domain object.
     */
    private Supplier mapSupplierWithoutCompanies(SupplierEntity supplier) {
        return new Supplier(
            supplier.getId(),
            supplier.getCnpj_cpf(),
            supplier.getRg(),
            supplier.getBirth(),
            supplier.getName(),
            supplier.getEmail(),
            supplier.getCep(),
            supplier.isE_pf(),
            null
        );
    }
}
