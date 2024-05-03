package com.demo.backend.business;

import org.springframework.stereotype.Service;

import com.demo.backend.exception.BaseException;
import com.demo.backend.exception.ProductException;
import java.util.Objects;
@Service
public class ProductBusiness {
    public String getProductById(String id) throws BaseException {
        //TODO: get data from Database
        if (Objects.equals("1234",id)) {
            throw ProductException.notFound();
        }
        return id;
    }
}
