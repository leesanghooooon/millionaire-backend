package com.home.millionairebackend.service;

import com.home.millionairebackend.model.Product;

import java.util.List;

public interface ProdService {
    void insertProduct(Product product);

    List<Product> selectProductList(Product product);
}
