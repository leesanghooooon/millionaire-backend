package com.home.millionairebackend.service.impl;

import com.home.millionairebackend.mapper.ProdMapper;
import com.home.millionairebackend.model.Product;
import com.home.millionairebackend.service.ProdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class ProdServiceImpl implements ProdService {

    @Autowired
    private ProdMapper mapper;

//    public ProdServiceImpl(ProdMapper prodMapper) {
//        this.mapper = prodMapper;
//    }

    public void insertProduct(Product product) {
        log.info(product.toString());
        mapper.insertProduct(product);
    }

}
