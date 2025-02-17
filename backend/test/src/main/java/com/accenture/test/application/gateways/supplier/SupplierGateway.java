package com.accenture.test.application.gateways.supplier;

import com.accenture.test.domain.supplier.Supplier;
import com.accenture.test.domain.supplier.SupplierPag;

import java.util.UUID;

/**
 *
 * The Supplier interface.
 *
 * @author Marcus Nastasi
 * @version 1.0.1
 * @since 2024
 */
public interface SupplierGateway {

    /**
     *
     * This function should allow to get all the supplier registres on database.
     *
     * @param page the pagination page.
     * @param size the size of search.
     * @param name the name filter.
     * @param cnpj_cpf the CNPJ or CPF.
     *
     * @return return a supplier list paginated.
     */
    SupplierPag getAll(int page, int size, String name, String cnpj_cpf);

    /**
     *
     * This function should allow to get a single supplier.
     *
     * @param id the supplier id.
     *
     * @return the supplier entity object.
     */
    Supplier get(UUID id);

    /**
     *
     * This function should allow to save data on database.
     *
     * @param supplier the supplier entity object to be saved.
     *
     * @return the registre saved.
     */
    Supplier save(Supplier supplier);

    /**
     *
     * This function should allow to delete a supplier registre on database.
     *
     * @param id the supplier id.
     *
     * @return the supplier deleted.
     */
    Supplier delete(UUID id);
}
