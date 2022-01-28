package com.home.millionairebackend.mapper;

import com.home.millionairebackend.model.Product;
import com.home.millionairebackend.model.finlife.CompanySearch;
import com.home.millionairebackend.model.finlife.CompanySearchOption;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FinlifeMapper {
    void insertCompany(Map<Object,String> param);
    void insertCompanyOption(CompanySearchOption companySearchOption);
}
