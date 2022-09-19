package com.mercuryCyclists.Inventory.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class RestfulService {
    private static final String SUPPLIERAPI = "http://localhost:8080/api/v1/supplier/{supplierId}";
    private RestTemplate restTemplate;

    public RestfulService() {
        this.restTemplate = new RestTemplate();
    }

    /**
     * Get supplier with given supplier Id
     *
     * @param supplierId
     * @return Response entity
     */
    public ResponseEntity<String> getSupplier(Long supplierId) {
        Map<String, Long> param = new HashMap<>();
        param.put("supplierId", supplierId);
        return restTemplate.getForEntity(SUPPLIERAPI, String.class, param);
    }
}
