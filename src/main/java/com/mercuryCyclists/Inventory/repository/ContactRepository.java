package com.mercuryCyclists.Inventory.repository;

import com.mercuryCyclists.Inventory.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for contact
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
