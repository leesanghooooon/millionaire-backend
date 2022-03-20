package com.home.millionairebackend.mapper;

import com.home.millionairebackend.model.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProdMapper {
    void insertProduct(Product product);

    List<Product> selectProductList(Product product);
}
