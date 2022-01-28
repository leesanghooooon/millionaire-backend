package com.home.millionairebackend.service.impl;

import com.home.millionairebackend.mapper.FinlifeMapper;
import com.home.millionairebackend.mapper.ProdMapper;
import com.home.millionairebackend.model.FinlifeResultCd;
import com.home.millionairebackend.model.Product;
import com.home.millionairebackend.model.finlife.CompanySearch;
import com.home.millionairebackend.model.finlife.CompanySearchMeta;
import com.home.millionairebackend.model.finlife.CompanySearchOption;
import com.home.millionairebackend.model.finlife.CompanySearchResponse;
import com.home.millionairebackend.service.FinlifeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Transactional
public class FinlifeServiceImpl implements FinlifeService {

    @Autowired
    private FinlifeMapper mapper;

    public void insertCompanyInfo(CompanySearchResponse companySearchResponse) {
        log.info(companySearchResponse.toString());

        FinlifeResultCd codeInfo = FinlifeResultCd.findMessage(companySearchResponse.getResult().getErr_cd());
        if("000".equals(codeInfo.getCode())){
            String topFinGrpNo = companySearchResponse.getResult().getTop_fin_grp_no();

            List<CompanySearch> companySearchList = companySearchResponse.getResult().getBaseList();
            List<CompanySearchOption> companySearchOptionList = companySearchResponse.getResult().getOptionList();

            this.insertCompany(companySearchList, topFinGrpNo);
            this.insertCompanyOption(companySearchOptionList);
        }else{
            log.error(codeInfo.getMessage());
        }

    }

    public void insertCompany(List<CompanySearch> companySearchList, String topFinGrpNo){
        for(CompanySearch companySearch : companySearchList){
            Map<Object,String> param = new HashMap<>();
            param.put("dcls_month",     companySearch.getDcls_month());
            param.put("top_fin_grp_no", topFinGrpNo);
            param.put("fin_co_no",      companySearch.getFin_co_no());
            param.put("kor_co_nm",      companySearch.getKor_co_nm());
            param.put("dcls_chrg_man",  companySearch.getDcls_chrg_man());
            param.put("homp_url",       companySearch.getHomp_url());
            param.put("cal_tel",        companySearch.getCal_tel());
            mapper.insertCompany(param);
        }
    }

    public void insertCompanyOption(List<CompanySearchOption> companySearchOptionList){
        for(CompanySearchOption companySearchOption : companySearchOptionList){
            mapper.insertCompanyOption(companySearchOption);
        }
    }

}
