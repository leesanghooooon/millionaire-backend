package com.home.millionairebackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.home.millionairebackend.constant.MillionaireCommonConstant;
import com.home.millionairebackend.model.FinlifeResultCd;
import com.home.millionairebackend.model.finlife.CompanySearchResponse;
import com.home.millionairebackend.model.finlife.SavingProductsSearchResponse;
import com.home.millionairebackend.utill.HttpClientUtil;
import com.home.millionairebackend.utill.JsonUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/finlife")
@EnableAutoConfiguration
public class FinlifeController {

    @Autowired
    HttpClientUtil httpClientUtil;

    @Autowired
    JsonUtil jsonUtil;

    ResponseEntity<?> entity = null;

    @ApiOperation(value = "금융회사 API 상세", notes = "금융회사 API 상세")
    @PostMapping("/getCompanySearch")
    public ResponseEntity<?> getCompanySearch(String topFinGrpNo, String pageNo) throws JsonProcessingException {

        String url = MillionaireCommonConstant.FINLIFE_API_COMPANY_JSON;

        Map<Object,String> param = new HashMap<>();
        param.put("auth", MillionaireCommonConstant.FINLIFE_AUTH);
        param.put("topFinGrpNo",topFinGrpNo);
        param.put("pageNo",pageNo);

        Map<String,Object> rsltMap = httpClientUtil.doPostByFrom(3, url, MillionaireCommonConstant.HTTP_CONT_TYP_FROM, param);
        if(MapUtils.isNotEmpty(rsltMap)){

            int statusCode = (Integer) rsltMap.get("statusCode");
            if(200 == statusCode){

                CompanySearchResponse response = jsonUtil.jsonStringTobean((String) rsltMap.get("rsltJson"), CompanySearchResponse.class);

                FinlifeResultCd codeInfo = FinlifeResultCd.findMessage(response.getResult().getErr_cd());
                if("000".equals(codeInfo.getCode())){
                    entity = new ResponseEntity<>(response, HttpStatus.OK);
                }else{
                    entity = new ResponseEntity<>(codeInfo.getMessage(), HttpStatus.OK);
                }

            }else{
                entity = new ResponseEntity<>(statusCode, HttpStatus.OK);
            }

        }else{
            entity = new ResponseEntity<>("rsltMap isEmpty", HttpStatus.OK);
        }

        return entity;
    }

    @ApiOperation(value = "적금 API 상세", notes = "적금 API 상세")
    @PostMapping
    public ResponseEntity<?> getSavingProductsSearch(String topFinGrpNo,String pageNo) throws JsonProcessingException {

        String url = MillionaireCommonConstant.FINLIFE_API_SAVINGPRODUCTS_JSON;

        topFinGrpNo = "020000";
        pageNo = "1";

        Map<Object,String> param = new HashMap<>();
        param.put("auth", MillionaireCommonConstant.FINLIFE_AUTH);
        param.put("topFinGrpNo",topFinGrpNo);
        param.put("pageNo",pageNo);

        Map<String,Object> rsltMap = httpClientUtil.doPostByFrom(3, url, MillionaireCommonConstant.HTTP_CONT_TYP_FROM, param);
        if(MapUtils.isNotEmpty(rsltMap)){

            int statusCode = (Integer) rsltMap.get("statusCode");
            if(200 == statusCode){

                SavingProductsSearchResponse response = jsonUtil.jsonStringTobean((String) rsltMap.get("rsltJson"), SavingProductsSearchResponse.class);

                FinlifeResultCd codeInfo = FinlifeResultCd.findMessage(response.getResult().getErr_cd());
                if("000".equals(codeInfo.getCode())){
                    entity = new ResponseEntity<>(response, HttpStatus.OK);
                }else{
                    entity = new ResponseEntity<>(codeInfo.getMessage(), HttpStatus.OK);
                }

            }else{
                entity = new ResponseEntity<>(statusCode, HttpStatus.OK);
            }

        }else{
            entity = new ResponseEntity<>("rsltMap isEmpty", HttpStatus.OK);
        }

        return entity;
    }
}
