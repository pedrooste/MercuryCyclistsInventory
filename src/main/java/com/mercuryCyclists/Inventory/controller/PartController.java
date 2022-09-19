package com.mercuryCyclists.Inventory.controller;

import com.mercuryCyclists.Inventory.entity.Part;
import com.mercuryCyclists.Inventory.service.PartService;
import com.mercuryCyclists.Inventory.service.RestfulService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/part")
public class PartController {
    private PartService partService;
    private RestfulService restfulService;

    public PartController(PartService partService, RestfulService restfulService) {
        this.partService = partService;
        this.restfulService = restfulService;
    }

    /**
     * Find supplier with given part id
     *
     * @param partId
     */
    @GetMapping("{partId}/supplier")
    public ResponseEntity<String> findSupplier(@PathVariable Long partId) {
        Part part = partService.findPartByPartId(partId);
        if (part == null) return new ResponseEntity<>("Invalid part ID", HttpStatus.FAILED_DEPENDENCY);
        return restfulService.getSupplier(part.getSupplierId());
    }
}
