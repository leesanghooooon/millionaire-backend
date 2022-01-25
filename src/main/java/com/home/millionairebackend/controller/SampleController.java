package com.home.millionairebackend.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SampleController {

    ResponseEntity<?> entity = null;

    @ApiOperation(value = "TEST API", notes = "TEST API입니다.")
    @GetMapping
    public ResponseEntity<?> apiTest() {
        entity = new ResponseEntity<>("SUCESS", HttpStatus.OK);

        return entity;
    }
}
