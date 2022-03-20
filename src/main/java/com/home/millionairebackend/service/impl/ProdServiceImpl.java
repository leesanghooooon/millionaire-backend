package com.home.millionairebackend.service.impl;

import com.home.millionairebackend.mapper.ProdMapper;
import com.home.millionairebackend.model.Product;
import com.home.millionairebackend.service.ProdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
public class ProdServiceImpl implements ProdService {

    @Autowired
    private ProdMapper mapper;

    public void insertProduct(Product product) {
        log.info(product.toString());
        mapper.insertProduct(product);
    }

    public List<Product> selectProductList(Product product){
        log.info(product.toString());
        List<Product> list = mapper.selectProductList(product);
        return list;
    }

}
