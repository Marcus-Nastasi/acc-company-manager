package com.accenture.test.application.gateways.company;

import com.accenture.test.domain.company.Company;
import com.accenture.test.domain.company.CompanyPag;

import java.util.UUID;

/**
 *
 * The Company interface.
 *
 * @author Marcus Nastasi
 * @version 1.0.1
 * @since 2024
 */
public interface CompanyGateway {

    /**
     *
     * This function should allow to get all the company registres on database.
     *
     * @param page the pagination page.
     * @param size the size of search.
     * @param name the name filter.
     * @param cnpj cnpj filter.
     * @param cep cep filter.
     * @return return a company list paginated.
     */
    CompanyPag getAll(int page, int size, String name, String cnpj, String cep);

    /**
     *
     * This function should allow to get a single company.
     *
     * @param id the company id.
     * @return the company entity object.
     */
    Company get(UUID id);

    /**
     *
     * This function should allow to save data on database.
     *
     * @param data the company entity object to be saved.
     * @return the registre saved.
     */
    Company save(Company data);

    /**
     *
     * This function should allow to delete a company registre on database.
     *
     * @param id the company id.
     * @return the company deleted.
     */
    Company delete(UUID id);
}
