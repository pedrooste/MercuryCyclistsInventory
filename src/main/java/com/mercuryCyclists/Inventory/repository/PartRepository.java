package com.mercuryCyclists.Inventory.repository;

import com.mercuryCyclists.Inventory.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, Long> {
}
