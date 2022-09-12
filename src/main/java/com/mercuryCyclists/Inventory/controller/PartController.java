package com.mercuryCyclists.Inventory.controller;

import com.mercuryCyclists.Inventory.entity.Part;
import com.mercuryCyclists.Inventory.service.PartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/part")
public class PartController {
    private PartService partService;
    private static final String GETSUPLLIERAPI = "http://localhost:8080/api/v1/supplier/{supplierId}";
    private static RestTemplate restTemplate = new RestTemplate();

    public PartController(PartService partService) {
        this.partService = partService;

    }

    /**
     * Find supplier with given part id
     * @param partId
     */
    @GetMapping("{partId}/supplier")
    public ResponseEntity<String> findSupplier(@PathVariable Long partId) {
        Part part = partService.findPartByPartId(partId);
        if (part == null) return new ResponseEntity<>("Invalid part ID", HttpStatus.FAILED_DEPENDENCY);
        Map<String, Long> param = new HashMap<>();
        param.put("supplierId", part.getSupplierId());
        String result = restTemplate.getForObject(GETSUPLLIERAPI, String.class, param);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
