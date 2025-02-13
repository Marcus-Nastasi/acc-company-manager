package com.accenture.test.application.usecase.company;

import com.accenture.test.domain.cep.Cep;
import com.accenture.test.domain.company.Company;
import com.accenture.test.domain.company.CompanyPag;
import com.accenture.test.domain.supplier.Supplier;
import com.accenture.test.application.exception.AppException;
import com.accenture.test.application.usecase.cep.CepUseCase;
import com.accenture.test.application.usecase.supplier.SupplierUseCase;
import com.accenture.test.application.gateways.company.CompanyGateway;

import java.util.UUID;

/**
 *
 * The Company use case.
 *
 * @author Marcus Nastasi
 * @version 1.0.1
 * @since 2024
 */
public class CompanyUseCase {

    private final CompanyGateway companyGateway;
    private final SupplierUseCase supplierUseCase;
    private final CepUseCase cepUseCase;

    public CompanyUseCase(CompanyGateway companyGateway, SupplierUseCase supplierUseCase, CepUseCase cepUseCase) {
        this.companyGateway = companyGateway;
        this.supplierUseCase = supplierUseCase;
        this.cepUseCase = cepUseCase;
    }

    /**
     *
     * This function allows to get all the company registres on database.
     *
     * @param page the pagination page.
     * @param size the size of search.
     * @param name the name filter.
     * @param cnpj cnpj filter.
     * @param cep cep filter.
     * @return return a company list paginated.
     */
    public CompanyPag getAll(int page, int size, String name, String cnpj, String cep) {
        return companyGateway.getAll(page, size, name, cnpj, cep);
    }

    /**
     *
     * This function allows to get a single company.
     *
     * @param id the company id.
     * @return the company entity object.
     */
    public Company get(UUID id) {
        return companyGateway.get(id);
    }

    /**
     *
     * This function allows to save new data on database.
     *
     * @param data the company entity object to be saved.
     * @return the registre saved.
     */
    public Company register(Company data) {
        return companyGateway.save(data);
    }

    /**
     *
     * This function allows to update data on database.
     *
     * @param id the company id.
     * @param data the company entity object to update.
     * @return the registre updated.
     */
    public Company update(UUID id, Company data) {
        Company company = get(id);
        company.setCnpj(data.getCnpj());
        company.setName(data.getName());
        company.setCep(data.getCep());
        return companyGateway.save(company);
    }

    /**
     *
     * This function allows to delete a company registre on database.
     *
     * @param id the company id.
     * @return the company deleted.
     */
    public Company delete(UUID id) {
        return companyGateway.delete(id);
    }

    /**
     *
     * This function allows to associate a company and supplier.
     *
     * @param id_supplier supplier id.
     * @param id company id.
     * @return the company.
     */
    public Company associateSupplier(UUID id_supplier, UUID id) {
        Company company = get(id);
        Supplier supplier = supplierUseCase.get(id_supplier);
        if (supplier.isE_pf() && isPr(company.getCep()) && supplierUseCase.validatesUnderageSuppliers(supplier.getBirth())) {
            throw new AppException("It's not permitted to register an underage supplier in Paraná");
        }
        linkCompanySupplier(company, supplier);
        return company;
    }

    /**
     *
     * This function allows to disassociate a company and supplier.
     *
     * @param supplier_id supplier id.
     * @param company_id company id.
     * @return the company.
     */
    public Company disassociateSupplier(UUID supplier_id, UUID company_id) {
        Company company = get(company_id);
        Supplier supplier = supplierUseCase.get(supplier_id);
        unlinkCompanySupplier(company, supplier);
        return company;
    }

    /**
     *
     * This function allows to check if the CEP is from Paraná.
     *
     * @param cep the CEP string.
     * @return a boolean indicating if is from Paraná or not.
     */
    public boolean isPr(String cep) {
        Cep response = cepUseCase.getCep(cep);
        if (response == null) throw new AppException("Error getting CEP");
        return response.getUf().equalsIgnoreCase("PR");
    }

    /**
     *
     * This is an auxiliar function to link the company and supplier on database.
     *
     * @param company company entity.
     * @param supplier supplier entity.
     */
    public void linkCompanySupplier(Company company, Supplier supplier) {
        company.getSuppliers().add(supplier);
        supplier.getCompanies().add(company);
        companyGateway.save(company);
        supplierUseCase.save(supplier);
    }

    /**
     *
     * This is an auxiliar function to unlink the company and supplier on database.
     *
     * @param company company entity.
     * @param supplier supplier entity.
     */
    public void unlinkCompanySupplier(Company company, Supplier supplier) {
        supplier.getCompanies().removeIf(e -> e.getId().equals(company.getId()));
        company.getSuppliers().removeIf(f -> f.getId().equals(supplier.getId()));
        supplierUseCase.save(supplier);
        companyGateway.save(company);
    }
}
