package com.accenture.test.infrastructure.gateway.supplier;

import com.accenture.test.application.gateways.supplier.SupplierGateway;
import com.accenture.test.domain.supplier.Supplier;
import com.accenture.test.domain.supplier.SupplierPag;
import com.accenture.test.infrastructure.entity.SupplierEntity;
import com.accenture.test.infrastructure.exception.InfraException;
import com.accenture.test.infrastructure.mapper.SupplierEntityMapper;
import com.accenture.test.infrastructure.persistence.SupplierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

/**
 *
 * The supplier service implementation.
 *
 * @author Marcus Nastasi
 * @version 1.0.1
 * @since 2024
 */
public class SupplierRepoGateway implements SupplierGateway {

    @Autowired
    private SupplierRepo supplierRepo;
    @Autowired
    private SupplierEntityMapper supplierEntityMapper;

    /**
     *
     * This function allows to get all the suppliers paginated and filtered by name and cnpj or cpf.
     *
     * @param page the pagination page.
     * @param size the size of search.
     * @param nome the name filter.
     * @param cnpj_cpf the CNPJ or CPF.
     *
     * @return a supplier paginated object.
     */
    @Override
    public SupplierPag getAll(int page, int size, String nome, String cnpj_cpf) {
        Page<SupplierEntity> entityPage = supplierRepo.filter(nome, cnpj_cpf, PageRequest.of(page, size));
        return new SupplierPag(
            entityPage.getContent().stream().map(supplierEntityMapper::mapFromEntity).toList(),
            entityPage.getNumber(),
            entityPage.getTotalPages(),
            entityPage.getTotalPages()
        );
    }

    /**
     *
     * This function allows to get one supplier by its id.
     *
     * @param id the supplier id.
     *
     * @return the supplier object.
     */
    @Override
    public Supplier get(UUID id) {
        return supplierEntityMapper.mapFromEntity(supplierRepo.findById(id).orElseThrow(() -> new InfraException("Not able to get supplier")));
    }

    /**
     *
     * This function allows to save a supplier.
     *
     * @param supplier the supplier entity object to be saved.
     *
     * @return the supplier saved.
     */
    @Override
    public Supplier save(Supplier supplier) {
        return supplierEntityMapper.mapFromEntity(supplierRepo.save(supplierEntityMapper.mapFromSupplierToEntity(supplier)));
    }

    /**
     *
     * This function allows to delete a supplier.
     *
     * @param id the supplier id.
     *
     * @return the supplier deleted.
     */
    @Override
    public Supplier delete(UUID id) {
        Supplier supplierCompanies = get(id);
        if (supplierCompanies == null) throw new InfraException("Supplier not found");
        supplierRepo.deleteById(id);
        return supplierCompanies;
    }
}
