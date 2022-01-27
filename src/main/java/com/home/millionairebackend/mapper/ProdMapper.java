package com.home.millionairebackend.mapper;

import com.home.millionairebackend.model.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProdMapper {
    void insertProduct(Product product);
}
