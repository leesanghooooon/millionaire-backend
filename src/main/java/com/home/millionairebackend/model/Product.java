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

    private String korCoNm;
    private String intrRateTypeNm;
    private String rsrvTypeNm;
    private String joinWay;
    private String mtrtInt;
    private String spclCnd;
    private String joinMember;
    private String etcNote;
    private String strDtm;
}
