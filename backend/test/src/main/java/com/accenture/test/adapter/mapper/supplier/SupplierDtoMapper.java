package com.accenture.test.adapter.mapper.supplier;

import com.accenture.test.adapter.input.supplier.SupplierRequestDto;
import com.accenture.test.adapter.mapper.company.CompanyDtoMapper;
import com.accenture.test.adapter.output.supplier.SupplierCleanDto;
import com.accenture.test.adapter.output.supplier.SupplierResponseDto;
import com.accenture.test.domain.supplier.Supplier;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * The supplier dto mapper.
 *
 * @author Marcus Nastasi
 * @version 1.0.1
 * @since 2024
 */
public class SupplierDtoMapper {

    @Autowired
    private CompanyDtoMapper companyDtoMapper;

    /**
     *
     * This function allows to map from supplier domain object to supplier response dto.
     *
     * @param supplier the domain object.
     *
     * @return the supplier response dto object.
     */
    public SupplierResponseDto mapToResponse(Supplier supplier) {
        return new SupplierResponseDto(
                supplier.getId(),
                supplier.getCnpj_cpf(),
                supplier.getRg(),
                supplier.getBirth(),
                supplier.getName(),
                supplier.getEmail(),
                supplier.getCep(),
                supplier.isE_pf(),
                supplier.getCompanies().stream().map(companyDtoMapper::mapToClean).toList()
        );
    }

    /**
     *
     * This function allows to map form supplier request dto to supplier domain object.
     *
     * @param supplierRequestDto the supplier request dto.
     *
     *  @return the supplier domain object.
     */
    public Supplier mapFromRequest(SupplierRequestDto supplierRequestDto) {
        return new Supplier(
                null,
                supplierRequestDto.cnpj_cpf(),
                supplierRequestDto.rg(),
                supplierRequestDto.nascimento(),
                supplierRequestDto.nome(),
                supplierRequestDto.email(),
                supplierRequestDto.cep(),
                supplierRequestDto.e_pf(),
                null
        );
    }

    /**
     *
     * This function allows to map from supplier domain object to supplier without companies dto.
     *
     * @param supplier the supplier domain object.
     *
     * @return the supplier without companies dto.
     */
    public SupplierCleanDto mapToClean(Supplier supplier) {
        return new SupplierCleanDto(
                supplier.getId(),
                supplier.getCnpj_cpf(),
                supplier.getRg(),
                supplier.getBirth(),
                supplier.getName(),
                supplier.getEmail(),
                supplier.getCep(),
                supplier.isE_pf()
        );
    }
}
