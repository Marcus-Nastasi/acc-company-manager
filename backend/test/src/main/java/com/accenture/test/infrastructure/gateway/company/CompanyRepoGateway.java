package com.accenture.test.infrastructure.gateway.company;

import com.accenture.test.application.gateways.company.CompanyGateway;
import com.accenture.test.domain.company.Company;
import com.accenture.test.domain.company.CompanyPag;
import com.accenture.test.infrastructure.entity.CompanyEntity;
import com.accenture.test.infrastructure.exception.InfraException;
import com.accenture.test.infrastructure.mapper.CompanyEntityMapper;
import com.accenture.test.infrastructure.persistence.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

/**
 *
 * The company service implementation.
 *
 * @author Marcus Nastasi
 * @version 1.0.1
 * @since 2024
 */
public class CompanyRepoGateway implements CompanyGateway {

    @Autowired
    private CompanyRepo companyRepo;
    @Autowired
    private CompanyEntityMapper companyEntityMapper;

    /**
     *
     * This function allows to get all companies paginated.
     *
     * @param page the pagination page.
     * @param size the size of search.
     * @param nome_fantasia the name filter.
     * @param cnpj cnpj filter.
     * @param cep cep filter.
     *
     * @return return a company list paginated.
     */
    @Override
    public CompanyPag getAll(int page, int size, String nome_fantasia, String cnpj, String cep) {
        Page<CompanyEntity> companyEntities = companyRepo.filter(nome_fantasia, cnpj, cep, PageRequest.of(page, size));
        return new CompanyPag(
            companyEntities.getContent().stream().map(companyEntityMapper::mapFromEntity).toList(),
            companyEntities.getNumber(),
            companyEntities.getTotalPages(),
            companyEntities.getTotalPages()
        );
    }

    /**
     *
     * This function allows to get one company by id.
     *
     * @param id the company id.
     *
     * @return the company entity object.
     */
    @Override
    public Company get(UUID id) {
        return companyEntityMapper.mapFromEntity(companyRepo.findById(id).orElseThrow(() -> new InfraException("Not able to get company")));
    }

    /**
     *
     * This function allows to save company data on database.
     *
     * @param data the company entity object to be saved.
     *
     * @return the company saved.
     */
    @Override
    public Company save(Company data) {
        return companyEntityMapper.mapFromEntity(companyRepo.save(companyEntityMapper.mapToEntity(data)));
    }

    /**
     *
     * This function allows to delete company data from database.
     *
     * @param id the company id.
     *
     * @return the company deleted.
     */
    @Override
    public Company delete(UUID id) {
        Company companySupplier = get(id);
        if (companySupplier == null) throw new InfraException("Not able to delete company");
        companyRepo.deleteById(id);
        return companySupplier;
    }
}
