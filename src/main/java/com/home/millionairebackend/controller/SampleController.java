package com.home.millionairebackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.millionairebackend.constant.MillionaireCommonConstant;
import com.home.millionairebackend.utill.JsonUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class SampleController {

    @Autowired
    JsonUtil jsonUtil;

    ResponseEntity<?> entity = null;

    @ApiOperation(value = "TEST API", notes = "TEST API입니다.")
    @GetMapping
    public ResponseEntity<?> apiTest() {

        StandardPBEStringEncryptor spe = new StandardPBEStringEncryptor();
        spe.setAlgorithm("PBEWithMD5AndDES");
        spe.setPassword("millionaire");

        log.info("millionaire === {}",spe.encrypt("millionaire"));

        entity = new ResponseEntity<>("SUCESS", HttpStatus.OK);
        return entity;
    }

    @ApiOperation(value = "Product Data Insert", notes = "상품등록 API")
    @PostMapping("/httpCallTest")
    public void httpCallTest() throws IOException {

        HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
        HttpPost httpPost = new HttpPost("http://finlife.fss.or.kr/finlifeapi/savingProductsSearch.json"); //POST 메소드 URL 새성

        httpPost.setHeader("Content-Type", MillionaireCommonConstant.HTTP_CONT_TYP_FROM);

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);

        nameValuePairs.add(new BasicNameValuePair("auth", MillionaireCommonConstant.FINLIFE_AUTH));
        nameValuePairs.add(new BasicNameValuePair("topFinGrpNo", "020000"));
        nameValuePairs.add(new BasicNameValuePair("pageNo", "1"));

        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

        HttpResponse response = client.execute(httpPost);

        log.info("response : {}", response);

        //Response 출력
        if (response.getStatusLine().getStatusCode() == 200) {
            ResponseHandler<String> handler = new BasicResponseHandler();
            String body = handler.handleResponse(response);
            System.out.println("[RESPONSE] requestHttpJson() " + body);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode node = objectMapper.readTree(body);

            log.info("node : {}",node.toString());
        } else {
            System.out.println("response is error : " + response.getStatusLine().getStatusCode());
        }

    }
}
