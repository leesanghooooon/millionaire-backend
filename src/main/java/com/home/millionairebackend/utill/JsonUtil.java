package com.home.millionairebackend.utill;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @packageName : com.kisline.cuisine.common.util
 * @fileName : JsonUtil.java
 * @author : 2190009
 * @date : 2021.03.02
 * @description :
 * ================================================================================
 * 변경일 작성자 내용
 * --------------------------------------------------------------------------------
 * 2021.03.02 2190009 최초 생성
 *
 */
@Slf4j
@Component
public class JsonUtil {

    private static final Configuration conf = Configuration.defaultConfiguration()
            .addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL)
            //.addOptions(Option.AS_PATH_LIST)
            .addOptions(Option.SUPPRESS_EXCEPTIONS);
    
    /**
     * 
     * <pre>
     * 1. 개요       : Object를 JSONString으로 파싱
     * 2. 처리내용    :
     * </pre>
     * 
     * @Method Name : beanToJsonString
     * @date : 2021. 4. 12.
     * @author : 2190014
     * @history :
     * ================================================================================
     * 변경일 작성자 내용
     * --------------------------------------------------------------------------------
     * 2021.04.12 2190014 최초 작성
     * 
     * @param obj
     * @return
     * @throws JsonProcessingException
     */
    public static String beanToJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        return jsonString;
    }

    /**
     * 
     * <pre>
     * 1. 개요       : JSonString을 Object 형태로 파싱
     * 2. 처리내용    :
     * </pre>
     * 
     * @Method Name : jsonStringTobean
     * @date : 2021. 2. 26.
     * @author : 2190009
     * @param <T>
     * @history :
     * ================================================================================
     * 변경일 작성자 내용
     * --------------------------------------------------------------------------------
     * 2021.02.26 2190009 최초 작성
     * 
     * 
     * @param <T>
     * @param content
     * @param valueType
     * @return
     * @throws JsonProcessingException
     */
    public static <T> T jsonStringTobean(String content, Class<T> valueType) throws JsonProcessingException {
        return new ObjectMapper().configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true).readValue(content, valueType);
    }

    
    
    /**
     * 
     * <pre>
     * 1. 개요       : json에 Object구조체를 추가한다.
     * 2. 처리내용    : srcKey 변수명의 Object를 outputExpr(jsonPath)경로에 추가한다.
     * </pre>
     * @Method Name : jsonObjAdd
     * @date        : 2021. 10. 14.
     * @author      : 2190133
     * @history     : 
     * ================================================================================
     * 변경일       작성자                          내용
     * --------------------------------------------------------------------------------
     * 2021.10.14 2190133          최초 작성   
     * 
     * @param outputJson
     * @param outputExpr
     * @param srcKey
     * @return
     */
    public static String putJsonObj(String outputJson, String outputExpr, String srcKey) {
        
        //##############################################################
        //### 10. srcKey 변수명의 Object를 outputExpr(jsonPath)경로에 추가한다.
        //##############################################################
        DocumentContext putDoc = JsonPath.using(conf).parse(outputJson);
        String outData = putDoc.put(outputExpr, srcKey, new HashMap<String,Object>()).jsonString();
        return outData;
        
    }
    
    /**
     * 
     * <pre>
     * 1. 개요       : json에 Array구조체를 추가한다.
     * 2. 처리내용    : srcKey 변수명의 Array를 outputExpr(jsonPath)경로에 추가한다.
     * </pre>
     * @Method Name : jsonArrAdd
     * @date        : 2021. 10. 14.
     * @author      : 2190133
     * @history     : 
     * ================================================================================
     * 변경일       작성자                          내용
     * --------------------------------------------------------------------------------
     * 2021.10.14 2190133          최초 작성   
     * 
     * @param outputJson
     * @param outputExpr
     * @param srcKey
     * @return
     */
    public static String putJsonArr(String outputJson, String outputExpr, String srcKey) {
        
        //##############################################################
        //### 10. srcKey 변수명의 Array를 outputExpr(jsonPath)경로에 추가한다.
        //##############################################################
        DocumentContext putDoc = JsonPath.using(conf).parse(outputJson);
        String outData = putDoc.put(outputExpr, srcKey, new ArrayList<HashMap<String,Object>>()).jsonString();
        return outData;
        
    }
    
    /**
     * 
     * <pre>
     * 1. 개요       : json에 Key에 해당하는 Value를 추가
     * 2. 처리내용    : json에 Key에 해당하는 Value를 추가한다.
     * </pre>
     * @Method Name : jsonValueAdd
     * @date        : 2021. 10. 19.
     * @author      : 2190014
     * @history     : 
     * ================================================================================
     * 변경일       작성자                          내용
     * --------------------------------------------------------------------------------
     * 2021.10.19 2190014          최초 작성   
     * 
     * @param outputJson
     * @param outputExpr
     * @param srcKey
     * @return
     */
    public static String putJsonValue(String outputJson, String outputExpr, String srcKey, String value) {
        
        //##############################################################
        //### 10. srcKey 변수명의 Value 를 outputExpr(jsonPath)경로에 추가한다.
        //##############################################################
        DocumentContext putDoc = JsonPath.using(conf).parse(outputJson);
        
        String outData = putDoc.put(outputExpr, srcKey, value).jsonString();
        
        
        return outData;
        
    }
    
    
    /**
     * 
     * <pre>
     * 1. 개요       : json에 Key에 해당하는 Array를 추가
     * 2. 처리내용    : json에 Key에 해당하는 Array를 추가한다.
     * </pre>
     * @Method Name : putJsonArray
     * @date        : 2021. 11. 1.
     * @author      : 2190014
     * @history     : 
     * ================================================================================
     * 변경일       작성자                          내용
     * --------------------------------------------------------------------------------
     * 2021.11.01 2190014          최초 작성   
     * 
     * @param outputJson
     * @param outputExpr
     * @param srcKey
     * @param dataList
     * @return
     */
    public static String putJsonArray(String outputJson, String outputExpr, String srcKey, List<Map<String,Object>> dataList) {
        
      //##############################################################
        //### 10. srcKey 변수명의 Value 를 outputExpr(jsonPath)경로에 추가한다.
        //##############################################################
        DocumentContext putDoc = JsonPath.using(conf).parse(outputJson);
        
        String outData = putDoc.put(outputExpr, srcKey, dataList).jsonString();
        
        return outData;
        
    }

    
    /**
     * 
     * <pre>
     * 1. 개요       : json에 Key에 해당하는 Value 세팅
     * 2. 처리내용    : json에 Key에 해당하는 Value 세팅한다.
     * </pre>
     * @Method Name : jsonValueAdd
     * @date        : 2021. 10. 19.
     * @author      : 2190014
     * @history     : 
     * ================================================================================
     * 변경일       작성자                          내용
     * --------------------------------------------------------------------------------
     * 2021.10.19 2190014          최초 작성   
     * 
     * @param outputJson
     * @param outputExpr
     * @return
     */
    public static String setJsonValue(String outputJson, String outputExpr, String value) {
        
        //##############################################################
        //### 10. srcKey 변수명의 Value 를 outputExpr(jsonPath)경로에 추가한다.
        //##############################################################
        DocumentContext putDoc = JsonPath.using(conf).parse(outputJson);
        
        String outData = putDoc.set(outputExpr, value).jsonString();
        
        return outData;
        
    }
    
    /**
     * 
     * <pre>
     * 1. 개요       : json에 Key에 해당하는 List 세팅
     * 2. 처리내용    : json에 Key에 해당하는 List 세팅한다.
     * </pre>
     * @Method Name : setJsonArray
     * @date        : 2021. 11. 1.
     * @author      : 2190014
     * @history     : 
     * ================================================================================
     * 변경일       작성자                          내용
     * --------------------------------------------------------------------------------
     * 2021.11.01 2190014          최초 작성   
     * 
     * @param outputJson
     * @param outputExpr
     * @param dataList
     * @return
     */
    public static String setJsonArray(String outputJson, String outputExpr, List<Map<String,Object>> dataList) {
        
        //##############################################################
        //### 10. srcKey 변수명의 Value 를 outputExpr(jsonPath)경로에 추가한다.
        //##############################################################
        DocumentContext putDoc = JsonPath.using(conf).parse(outputJson);
        
        String outData = putDoc.set(outputExpr, dataList).jsonString();
        
        return outData;
        
    }

}
