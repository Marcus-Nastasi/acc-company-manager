package com.accenture.test.infrastructure.mapper;

import com.accenture.test.domain.company.Company;
import com.accenture.test.domain.supplier.Supplier;
import com.accenture.test.infrastructure.entity.CompanyEntity;
import com.accenture.test.infrastructure.entity.SupplierEntity;

import java.util.ArrayList;

/**
 *
 * The supplier entity mapper.
 *
 * @author Marcus Nastasi
 * @version 1.0.1
 * @since 2024
 */
public class SupplierEntityMapper {

    public Supplier mapFromEntity(SupplierEntity supplier) {
        return new Supplier(
            supplier.getId(),
            supplier.getCnpj_cpf(),
            supplier.getRg(),
            supplier.getBirth(),
            supplier.getName(),
            supplier.getEmail(),
            supplier.getCep(),
            supplier.isE_pf(),
            new ArrayList<>(supplier.getCompanies().stream().map(this::mapCompanyWithoutSuppliers).toList())
        );
    }

    public SupplierEntity mapFromSupplierToEntity(Supplier supplier) {
        return new SupplierEntity(
            supplier.getId(),
            supplier.getCnpj_cpf(),
            supplier.getRg(),
            supplier.getBirth(),
            supplier.getName(),
            supplier.getEmail(),
            supplier.getCep(),
            supplier.isE_pf(),
            new ArrayList<>(supplier.getCompanies().stream().map(this::mapCompanyEntityWithoutSuppliers).toList())
        );
    }

    private CompanyEntity mapCompanyEntityWithoutSuppliers(Company company) {
        return new CompanyEntity(
            company.getId(),
            company.getCnpj(),
            company.getName(),
            company.getCep(),
            null
        );
    }

    private Company mapCompanyWithoutSuppliers(CompanyEntity company) {
        return new Company(
            company.getId(),
            company.getCnpj(),
            company.getName(),
            company.getCep(),
            null
        );
    }
}
