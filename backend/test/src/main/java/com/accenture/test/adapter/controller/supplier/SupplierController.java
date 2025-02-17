package com.accenture.test.adapter.controller.supplier;

import com.accenture.test.adapter.mapper.supplier.SupplierDtoMapper;
import com.accenture.test.adapter.input.supplier.SupplierRequestDto;
import com.accenture.test.adapter.output.supplier.SupplierPagResponseDto;
import com.accenture.test.adapter.output.supplier.SupplierResponseDto;
import com.accenture.test.application.usecase.supplier.SupplierUseCase;
import com.accenture.test.domain.supplier.Supplier;
import com.accenture.test.domain.supplier.SupplierPag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 *
 * The supplier controller.
 *
 * @author Marcus Nastasi
 * @version 1.0.1
 * @since 2024
 */
@RestController
@RequestMapping(value = "/api/fornecedor")
public class SupplierController {

    @Autowired
    private SupplierUseCase supplierUseCase;
    @Autowired
    private SupplierDtoMapper supplierDtoMapper;

    /**
     *
     * This function opens the api to handle getting all suppliers requests.
     *
     * @param page the number of the page.
     * @param size the size of the return.
     * @param name name filter.
     * @param cnpj_cpf cnpj or cpf filters.
     *
     * @return the supplier paginated response dto.
     */
    @GetMapping()
    @Cacheable("supplier")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
        summary = "Get all suppliers",
        description = "In this route you can consult data from all suppliers, " +
                "with pagination and filter by name and cnpj/cpf"
    )
    @ApiResponse(responseCode = "200", description = "Returning data from suppliers")
    public SupplierPagResponseDto getAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "nome", required = false) String name,
            @RequestParam(value = "cnpj_cpf", required = false) String cnpj_cpf
    ) {
        if (size < 1) size = 10;
        SupplierPag supplierPag = supplierUseCase.getAll(page, size, name, cnpj_cpf);
        return new SupplierPagResponseDto(
            supplierPag.getData().stream().map(supplierDtoMapper::mapToClean).toList(),
            supplierPag.getPage(),
            supplierPag.getTotal(),
            supplierPag.getRemainingPages() - 1
        );
    }

    /**
     *
     * This function opens the api to handle getting single suppliers requests.
     *
     * @param id the supplier id.
     *
     * @return the supplier found.
     */
    @GetMapping(value = "/{id}")
    @Cacheable("supplier")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
        summary = "Get single supplier",
        description = "In this route you can consult detailed data from a single supplier"
    )
    @ApiResponse(responseCode = "200", description = "Returning Single Supplier Data")
    public SupplierResponseDto getSingle(@PathVariable("id") UUID id) {
        return supplierDtoMapper.mapToResponse(supplierUseCase.get(id));
    }

    /**
     *
     * This function opens the api to handle saving suppliers requests.
     *
     * @param data the new supplier to save.
     *
     * @return the supplier saved.
     */
    @PostMapping(value = "/registrar")
    @CacheEvict(value = "supplier", allEntries = true)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register new supplier", description = "In this route you can register a new supplier")
    @ApiResponse(responseCode = "201", description = "Returning data from the created supplier")
    public ResponseEntity<SupplierResponseDto> register(@RequestBody @Valid SupplierRequestDto data) {
        Supplier supplier = supplierUseCase.register(supplierDtoMapper.mapFromRequest(data));
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierDtoMapper.mapToResponse(supplier));
    }

    /**
     *
     * This function opens the api to handle updating suppliers requests.
     *
     * @param id the supplier id.
     * @param data the data to update.
     *
     * @return the supplier updated.
     */
    @PatchMapping(value = "/atualizar/{id}")
    @CacheEvict(value = "supplier", allEntries = true)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update supplier data", description = "In this route you can update a supplier's data")
    @ApiResponse(responseCode = "200", description = "Returning updated supplier data")
    public ResponseEntity<SupplierResponseDto> update(
            @PathVariable("id") UUID id,
            @RequestBody @Valid SupplierRequestDto data) {
        Supplier supplier = supplierUseCase.update(id, supplierDtoMapper.mapFromRequest(data));
        return ResponseEntity.status(HttpStatus.OK).body(supplierDtoMapper.mapToResponse(supplier));
    }

    /**
     *
     * This function opens the api to handle deleting suppliers.
     *
     * @param id the supplier id to delete.
     *
     * @return the supplier deleted.
     */
    @DeleteMapping(value = "/deletar/{id}")
    @CacheEvict(value = "supplier", allEntries = true)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete a supplier", description = "In this route you can delete a supplier's data")
    @ApiResponse(responseCode = "200", description = "Returning deleted supplier data")
    public ResponseEntity<SupplierResponseDto> delete(@PathVariable("id") UUID id)  {
        Supplier supplier = supplierUseCase.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(supplierDtoMapper.mapToResponse(supplier));
    }
}
