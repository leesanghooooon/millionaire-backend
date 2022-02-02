package com.home.millionairebackend.service.impl;

import com.home.millionairebackend.mapper.FinlifeMapper;
import com.home.millionairebackend.mapper.ProdMapper;
import com.home.millionairebackend.model.FinlifeResultCd;
import com.home.millionairebackend.model.Product;
import com.home.millionairebackend.model.finlife.*;
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

    public void insertSavingProductsInfo(SavingProductsSearchResponse savingProductsSearchResponse) {
        log.info(savingProductsSearchResponse.toString());

        FinlifeResultCd codeInfo = FinlifeResultCd.findMessage(savingProductsSearchResponse.getResult().getErr_cd());
        if("000".equals(codeInfo.getCode())){
            String topFinGrpNo = savingProductsSearchResponse.getResult().getTop_fin_grp_no();

            List<SavingProductsSearch> savingProductsSearchList = savingProductsSearchResponse.getResult().getBaseList();
            List<SavingProductsSearchOption> savingProductsSearchOptionList = savingProductsSearchResponse.getResult().getOptionList();

            this.insertSavingProduct(savingProductsSearchList, topFinGrpNo);
            this.insertSavingProductOption(savingProductsSearchOptionList);
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

    public void insertSavingProduct(List<SavingProductsSearch> savingProductsSearchList, String topFinGrpNo){
        for(SavingProductsSearch savingProductsSearch : savingProductsSearchList){
            Map<Object,String> param = new HashMap<>();
            param.put("dcls_month",     savingProductsSearch.getDcls_month());
            param.put("top_fin_grp_no", topFinGrpNo);
            param.put("fin_co_no",      savingProductsSearch.getFin_co_no());
            param.put("fin_prdt_cd",    savingProductsSearch.getFin_prdt_cd());
            param.put("fin_prdt_nm",    savingProductsSearch.getFin_prdt_nm());
            param.put("join_way",       savingProductsSearch.getJoin_way());
            param.put("mtrt_int",       savingProductsSearch.getMtrt_int());
            param.put("spcl_cnd",       savingProductsSearch.getSpcl_cnd());
            param.put("join_member",    savingProductsSearch.getJoin_member());
            param.put("etc_note",       savingProductsSearch.getEtc_note());
            param.put("max_limit",      savingProductsSearch.getMax_limit());
            param.put("dcls_strt_day",  savingProductsSearch.getDcls_strt_day());
            param.put("dcls_end_day",   savingProductsSearch.getDcls_end_day());
            param.put("fin_co_subm_day",savingProductsSearch.getFin_co_subm_day());
            mapper.insertSavingProduct(param);
        }
    }

    public void insertSavingProductOption(List<SavingProductsSearchOption> savingProductsSearchOptionList){
        for(SavingProductsSearchOption savingProductsSearchOption : savingProductsSearchOptionList){
            mapper.insertSavingProductOption(savingProductsSearchOption);
        }
    }

}
