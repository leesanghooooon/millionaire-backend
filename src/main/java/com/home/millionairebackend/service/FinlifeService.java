package com.home.millionairebackend.service;

import com.home.millionairebackend.model.Product;
import com.home.millionairebackend.model.finlife.CompanySearchResponse;

public interface FinlifeService {
    void insertCompanyInfo(CompanySearchResponse companySearchResponse);
}
