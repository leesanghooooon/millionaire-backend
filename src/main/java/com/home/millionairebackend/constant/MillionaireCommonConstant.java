package com.home.millionairebackend.constant;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class MillionaireCommonConstant {

    public static final String RSLT_CD_SUCCESS = "SUCCESS";
    public static final String RSLT_CD_FAIL = "FAIL";

    public static final String FINLIFE_AUTH = "d04565f2e495fac7a0acd02e06a3f69a";

    //###################################################
    //### Http 통신 content-type
    //###################################################
    public static final String HTTP_CONT_TYP_JSON   = "application/json;";
    public static final String HTTP_CONT_TYP_XML    = "application/xml;";
    public static final String HTTP_CONT_TYP_FROM    = "application/x-www-form-urlencoded;";

    //###################################################
    //### finlife Open API
    //###################################################
    public static final String FINLIFE_API_COMPANY_JSON = "http://finlife.fss.or.kr/finlifeapi/companySearch.json";
    public static final String FINLIFE_API_COMPANY_XML = "http://finlife.fss.or.kr/finlifeapi/companySearch.xml";
    public static final String FINLIFE_API_SAVINGPRODUCTS_JSON = "http://finlife.fss.or.kr/finlifeapi/savingProductsSearch.json";
    public static final String FINLIFE_API_SAVINGPRODUCTS_XML = "http://finlife.fss.or.kr/finlifeapi/savingProductsSearch.xml";

}
