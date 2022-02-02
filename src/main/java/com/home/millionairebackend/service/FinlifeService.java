package com.home.millionairebackend.service;

import com.home.millionairebackend.model.Product;
import com.home.millionairebackend.model.finlife.CompanySearchResponse;
import com.home.millionairebackend.model.finlife.SavingProductsSearchResponse;

public interface FinlifeService {
    void insertCompanyInfo(CompanySearchResponse companySearchResponse);
    void insertSavingProductsInfo(SavingProductsSearchResponse savingProductsSearchResponse);
}
