package com.home.millionairebackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.home.millionairebackend.constant.MillionaireCommonConstant;
import com.home.millionairebackend.model.Product;
import com.home.millionairebackend.utill.HttpClientUtil;
import com.home.millionairebackend.utill.JsonUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/finlife")
@EnableAutoConfiguration
public class FinlifeAPIController {

    @Autowired
    HttpClientUtil httpClientUtil;

    @Autowired
    JsonUtil jsonUtil;

    ResponseEntity<?> entity = null;

    @ApiOperation(value = "Product Data Insert", notes = "상품등록 API")
    @PostMapping
    public ResponseEntity<?> getSavingProductsSearch() throws JsonProcessingException {

        Map<Object,String> param = new HashMap<>();
        param.put("auth", MillionaireCommonConstant.FINLIFE_AUTH);
        param.put("topFinGrpNo","020000");
        param.put("pageNo","1");

        String jsonData = jsonUtil.beanToJsonString(param);
        log.info(jsonData);

        String url = "http://finlife.fss.or.kr/finlifeapi/savingProductsSearch.json";
//        Map<String,Object> rsltMap = httpClientUtil.doPostByJson(1, url, MillionaireCommonConstant.HTTP_CONT_TYP_JSON, jsonData);
        Map<String,Object> rsltMap = httpClientUtil.doPostByJson(3, url, MillionaireCommonConstant.HTTP_CONT_TYP_JSON, jsonData);
        log.info("rsltMap :: {}", rsltMap);

        entity = new ResponseEntity<>(jsonData, HttpStatus.OK);
        return entity;
    }
}
