package com.mercuryCyclists.Inventory.service;

import com.mercuryCyclists.Inventory.entity.Part;
import com.mercuryCyclists.Inventory.repository.PartRepository;
import org.springframework.stereotype.Service;

@Service
public class PartService {
    private PartRepository partRepository;

    public PartService(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    /**
     * Find part by part id
     * @param partId
     * @return
     */
    public Part findPartByPartId(Long partId) {
        return partRepository.findById(partId).orElse(null);
    }
}
