package com.home.millionairebackend.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class SampleController {

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
}
