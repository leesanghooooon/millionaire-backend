package com.home.millionairebackend.controller;

import com.home.millionairebackend.model.Product;
import com.home.millionairebackend.service.ProdService;
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

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/prod")
@EnableAutoConfiguration
public class ProdController {

    @Autowired
    ProdService prodService;

    ResponseEntity<?> entity = null;

//    {
//        "finCoNo": "0010001",
//            "finPrdtCd": "WR0001A",
//            "finPrdtNm": "스무살 우리 적금(정액·자유)",
//            "finPrdtUid": "",
//            "intrRate": "2.2",
//            "intrRate2": "2.7",
//            "intrRateType": "S",
//            "maxLimit": "200000",
//            "rsrvType": "F",
//            "savMoney": "200000",
//            "saveTrm": "24",
//            "userId": "admin"
//    }
    @ApiOperation(value = "Product Data Insert", notes = "상품등록 API")
    @PostMapping
    public ResponseEntity<?> insertProduct(@RequestBody Product product) {
        product.setFinPrdtUid(UUID.randomUUID().toString());
        log.info(product.toString());
        prodService.insertProduct(product);
        entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        return entity;
    }

}
