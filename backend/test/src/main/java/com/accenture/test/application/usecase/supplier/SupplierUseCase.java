package com.accenture.test.application.usecase.supplier;

import com.accenture.test.domain.supplier.Supplier;
import com.accenture.test.domain.supplier.SupplierPag;
import com.accenture.test.application.gateways.supplier.SupplierGateway;
import com.accenture.test.application.exception.AppException;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

/**
 *
 * The Supplier use case.
 *
 * @author Marcus Nastasi
 * @version 1.0.1
 * @since 2024
 */
public class SupplierUseCase {

    private final SupplierGateway supplierGateway;

    public SupplierUseCase(SupplierGateway supplierGateway) {
        this.supplierGateway = supplierGateway;
    }

    /**
     *
     * This function allows to get all the supplier registres on database.
     *
     * @param page the pagination page.
     * @param size the size of search.
     * @param name the name filter.
     * @param cnpj_cpf the CNPJ or CPF.
     * @return return a supplier list paginated.
     */
    public SupplierPag getAll(int page, int size, String name, String cnpj_cpf) {
        return supplierGateway.getAll(page, size, name, cnpj_cpf);
    }

    /**
     *
     * This function allows to get a single supplier.
     *
     * @param id the supplier id.
     * @return the supplier entity object.
     */
    public Supplier get(UUID id) {
        return supplierGateway.get(id);
    }

    /**
     *
     * This function allows to save data on database.
     *
     * @param supplier the supplier entity object to be saved.
     * @return the registre saved.
     */
    public Supplier save(Supplier supplier) {
        return supplierGateway.save(supplier);
    }

    /**
     *
     * This function allows to registre data on database.
     *
     * @param data the supplier entity object to be saved.
     * @return the registre saved.
     */
    public Supplier register(Supplier data) {
        if (data.isE_pf()) if (data.getRg() == null || data.getBirth() == null) {
            throw new AppException("It is necessary to provide an ID and date of birth to register as an individual supplier");
        }
        return save(data);
    }

    /**
     *
     * This function allows to update data.
     *
     * @param id the supplier id to update.
     * @param data the supplier entity object.
     * @return the registre saved.
     */
    public Supplier update(UUID id, Supplier data) {
        Supplier supplier = get(id);
        supplier.setCnpj_cpf(data.getCnpj_cpf());
        supplier.setName(data.getName());
        supplier.setEmail(data.getEmail());
        supplier.setCep(data.getCep());
        supplier.setE_pf(data.isE_pf());
        if (supplier.isE_pf()) {
            if (data.getRg() == null || data.getBirth() == null) {
                throw new AppException("It is necessary to provide an ID and date of birth to register as an individual supplier");
            }
            supplier.setRg(data.getRg());
            supplier.setBirth(data.getBirth());
        }
        return save(supplier);
    }

    /**
     *
     * This function allows to delete a supplier registre on database.
     *
     * @param id the supplier id.
     * @return the supplier deleted.
     */
    public Supplier delete(UUID id) {
        return supplierGateway.delete(id);
    }

    /**
     *
     * This function allows to check a supplier's age.
     *
     * @param nascimento the birthday.
     * @return a boolean that indicates if the supplier is underage.
     */
    public boolean validatesUnderageSuppliers(LocalDate nascimento) {
        return Period.between(nascimento, LocalDate.now()).getYears() <= 18;
    }
}
