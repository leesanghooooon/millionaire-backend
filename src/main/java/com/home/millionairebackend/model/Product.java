package com.home.millionairebackend.model;

import lombok.Data;

@Data
public class Product {
    private String finPrdtUid;
    private String userId;
    private String finCoNo;
    private String finPrdtCd;
    private String finPrdtNm;
    private String savMoney;
    private String maxLimit;
    private String intrRateType;
    private String saveTrm;
    private String rsrvType;
    private String intrRate;
    private String intrRate2;
}
