package com.home.millionairebackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.home.millionairebackend.constant.MillionaireCommonConstant;
import com.home.millionairebackend.model.FinlifeResultCd;
import com.home.millionairebackend.model.finlife.CompanySearchResponse;
import com.home.millionairebackend.model.finlife.SavingProductsSearchResponse;
import com.home.millionairebackend.service.FinlifeService;
import com.home.millionairebackend.utill.HttpClientUtil;
import com.home.millionairebackend.utill.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class SchedulerController {

    @Autowired
    HttpClientUtil httpClientUtil;

    @Autowired
    JsonUtil jsonUtil;

    @Autowired
    FinlifeService finlifeService;

//    @Scheduled(cron = "0 * 9 * * ?")
//    @Scheduled(fixedDelay = 200000)
    public void cronJobSchInsertCompany() throws JsonProcessingException {
        String url = "http://localhost:8081/api/finlife/getCompanySearch";

        String[] topFinGrpNoArr = {"020000", "030200", "030300", "050000", "060000"};

        log.info("=================== cronJobSchInsertCompany [START]");
        for(String topFinGrpNo : topFinGrpNoArr){

            Map<Object,String> param = new HashMap<>();
            param.put("topFinGrpNo", topFinGrpNo);
            param.put("pageNo","0");

            Map<String,Object> basRsltMap = httpClientUtil.doPostByFrom(1, url, MillionaireCommonConstant.HTTP_CONT_TYP_FROM, param);
            if(MapUtils.isNotEmpty(basRsltMap)) {

                int statusCode = (Integer) basRsltMap.get("statusCode");
                if (200 == statusCode) {
                    CompanySearchResponse basResponse = jsonUtil.jsonStringTobean((String) basRsltMap.get("rsltJson"), CompanySearchResponse.class);

                    FinlifeResultCd codeInfo = FinlifeResultCd.findMessage(basResponse.getResult().getErr_cd());
                    if("000".equals(codeInfo.getCode())){
                        String maxPageNo = basResponse.getResult().getMax_page_no();
                        String nowPageNo= basResponse.getResult().getNow_page_no();
                        int maxCnt = Integer.parseInt(maxPageNo);
                        int nowCnt = Integer.parseInt(nowPageNo);

                        while (true){
                            if(maxCnt >= nowCnt){
                                nowCnt++;
                                nowPageNo = Integer.toString(nowCnt);
                                param.put("pageNo",nowPageNo);

                                Map<String,Object> rsltMap = httpClientUtil.doPostByFrom(1, url, MillionaireCommonConstant.HTTP_CONT_TYP_FROM, param);
                                statusCode = (Integer) rsltMap.get("statusCode");
                                if (200 == statusCode) {
                                    CompanySearchResponse companySearchResponse = jsonUtil.jsonStringTobean((String) rsltMap.get("rsltJson"), CompanySearchResponse.class);
                                    companySearchResponse.getResult().setTop_fin_grp_no(topFinGrpNo);
                                    finlifeService.insertCompanyInfo(companySearchResponse);
                                }else{
                                    log.error(codeInfo.getMessage());
                                }
                            }
                            if(maxCnt == nowCnt){
                                break;
                            }
                        }
                    }else{
                        log.error(codeInfo.getMessage());
                    }
                }

            }

        }
        log.info("=================== cronJobSchInsertCompany [END]");

    }

//    @Scheduled(fixedDelay = 200000)
    public void cronJobSchInsertSaveProducts() throws JsonProcessingException {
        String url = "http://localhost:8081/api/finlife/getSavingProductsSearch";

        String[] topFinGrpNoArr = {"020000", "030200", "030300", "050000", "060000"};

        log.info("=================== cronJobSchInsertSaveProducts [START]");
        for(String topFinGrpNo : topFinGrpNoArr){

            Map<Object,String> param = new HashMap<>();
            param.put("topFinGrpNo", topFinGrpNo);
            param.put("pageNo","0");

            Map<String,Object> basRsltMap = httpClientUtil.doPostByFrom(1, url, MillionaireCommonConstant.HTTP_CONT_TYP_FROM, param);
            if(MapUtils.isNotEmpty(basRsltMap)) {

                int statusCode = (Integer) basRsltMap.get("statusCode");
                if (200 == statusCode) {
                    SavingProductsSearchResponse basResponse = jsonUtil.jsonStringTobean((String) basRsltMap.get("rsltJson"), SavingProductsSearchResponse.class);

                    FinlifeResultCd codeInfo = FinlifeResultCd.findMessage(basResponse.getResult().getErr_cd());
                    if("000".equals(codeInfo.getCode())){
                        String maxPageNo = basResponse.getResult().getMax_page_no();
                        String nowPageNo= basResponse.getResult().getNow_page_no();
                        int maxCnt = Integer.parseInt(maxPageNo);
                        int nowCnt = Integer.parseInt(nowPageNo);

                        while (true){
                            if(maxCnt >= nowCnt){
                                nowCnt++;
                                nowPageNo = Integer.toString(nowCnt);
                                param.put("pageNo",nowPageNo);

                                Map<String,Object> rsltMap = httpClientUtil.doPostByFrom(1, url, MillionaireCommonConstant.HTTP_CONT_TYP_FROM, param);
                                statusCode = (Integer) rsltMap.get("statusCode");
                                if (200 == statusCode) {
                                    SavingProductsSearchResponse savingProductsSearchResponse = jsonUtil.jsonStringTobean((String) rsltMap.get("rsltJson"), SavingProductsSearchResponse.class);
                                    savingProductsSearchResponse.getResult().setTop_fin_grp_no(topFinGrpNo);
                                    finlifeService.insertSavingProductsInfo(savingProductsSearchResponse);
                                }else{
                                    log.error(codeInfo.getMessage());
                                }
                            }
                            if(maxCnt == nowCnt){
                                break;
                            }
                        }

                    }else{
                        log.error(codeInfo.getMessage());
                    }

                }

            }

        }
        log.info("=================== cronJobSchInsertSaveProducts [END]");

    }

}
