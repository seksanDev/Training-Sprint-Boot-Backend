package com.demo.backend.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.backend.business.ProductBusiness;
import com.demo.backend.exception.BaseException;

@RestController
@RequestMapping("/product")
public class ProductApi {
    private final ProductBusiness business;

    public ProductApi(ProductBusiness business) {
        this.business = business;
    }

    // get id product ผ่าน url
    @GetMapping("/{id}")
    public ResponseEntity<String> getProductById(@PathVariable String id) throws BaseException {
        String response = business.getProductById(id);
        return ResponseEntity.ok(response);
    }
}
