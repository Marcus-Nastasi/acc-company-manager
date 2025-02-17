package com.accenture.test.adapter.mapper.company;

import com.accenture.test.adapter.input.company.CompanyRequestDto;
import com.accenture.test.adapter.mapper.supplier.SupplierDtoMapper;
import com.accenture.test.adapter.output.company.CompanyCleanDto;
import com.accenture.test.adapter.output.company.CompanyResponseDto;
import com.accenture.test.domain.company.Company;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * The company dto mapper class.
 *
 * @author Marcus Nastasi
 * @version 1.0.1
 * @since 2024
 */
public class CompanyDtoMapper {

    @Autowired
    private SupplierDtoMapper supplierDtoMapper;

    /**
     *
     * This function allows to map from company request dto to company domain object.
     *
     * @param companyRequestDto the company request dto.
     *
     * @return the company domain object.
     */
    public Company mapFromRequest(CompanyRequestDto companyRequestDto) {
        return new Company(
                companyRequestDto.id(),
                companyRequestDto.cnpj(),
                companyRequestDto.nome_fantasia(),
                companyRequestDto.cep(),
                companyRequestDto.fornecedores()
        );
    }

    /**
     *
     * This function allows to map from company domain object to company response dto.
     *
     * @param company company domain object.
     *
     * @return company response dto.
     */
    public CompanyResponseDto mapToResponse(Company company) {
        return new CompanyResponseDto(
                company.getId(),
                company.getCnpj(),
                company.getName(),
                company.getCep(),
                company.getSuppliers().stream().map(supplierDtoMapper::mapToClean).toList()
        );
    }

    /**
     *
     * This function allows to map from company domain object to company response dto
     * without supplier.
     *
     * @param company company domain object.
     *
     * @return the company response dto without supplier.
     */
    public CompanyCleanDto mapToClean(Company company) {
        return new CompanyCleanDto(
                company.getId(),
                company.getCnpj(),
                company.getName(),
                company.getCep()
        );
    }
}
