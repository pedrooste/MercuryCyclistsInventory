package com.mercuryCyclists.Inventory.repository;

import com.mercuryCyclists.Inventory.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for supplier
 */
@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
