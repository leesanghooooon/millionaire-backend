package com.home.millionairebackend.utill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.home.millionairebackend.constant.MillionaireCommonConstant;
import org.apache.commons.collections4.MapUtils;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class HttpClientUtil {

    /**
     * 
     * <pre>
     * 1. 개요       : Http Post 방식 응답 데이터 Json 
     * 2. 처리내용    : Http Post 방식 응답 데이터 Json 처리함 
     * </pre>
     * @Method Name : doGet
     * @date        : 2021. 9. 8.
     * @author      : 2190014
     * @history     : 
     * ================================================================================
     * 변경일       작성자                          내용
     * --------------------------------------------------------------------------------
     * 2021.09.08 2190014          최초 작성   
     * 
     * @param retryCnt
     * @param url
     * @param contentType
     * @return
     */
    public static Map<String,Object> doGetByJson(final int retryCnt, final String url, final String contentType){
        
        Map<String,Object> rslt = new HashMap<>();
        
        String rsltJson = "";
        
        int tryCnt = 1;
        
        while( tryCnt <= retryCnt ) {
            
            try (CloseableHttpClient client = HttpClients.createDefault()) {
                
                
                HttpGet httpGet = new HttpGet(url);
                httpGet.setHeader("Accept", contentType);
                httpGet.setHeader("Content-type", contentType);
                
                CloseableHttpResponse response = client.execute(httpGet);
                
                int statusCode = response.getStatusLine().getStatusCode();
                
                rslt.put("statusCode", statusCode);
                
                if(HttpStatus.SC_OK == statusCode) {
                    rsltJson = EntityUtils.toString(response.getEntity(), "UTF-8");
                    rslt.put("rsltJson", rsltJson);
                    log.info("rsltJson :: {}", rsltJson);
                    break;
                }else {
                    tryCnt++;
                    
                    if(retryCnt == tryCnt) {
                        String errMsg = "["+ statusCode +"] doGetByJson response code not valid";
                        log.info("errMsg :: {}", errMsg);
                    }
                }
                
            }catch(Exception e){
                tryCnt++;
                
                if(retryCnt == tryCnt) {
                    String errMsg = "doGetByJson error has occurred";
                    log.error(errMsg, e);
                    
                    rslt.put("statusCode", 999);
                }
                
            }
            
        }
        
        
        return rslt;
    }
    
    /**
     * 
     * <pre>
     * 1. 개요       : Http Post 방식 요청/응답 데이터 Json 
     * 2. 처리내용    : Http Post 방식 요청/응답 데이터 Json 처리함 
     * </pre>
     * @Method Name : doPost
     * @date        : 2021. 9. 7.
     * @author      : 2190014
     * @history     : 
     * ================================================================================
     * 변경일       작성자                          내용
     * --------------------------------------------------------------------------------
     * 2021.09.07 2190014          최초 작성   
     * 
     * @param retryCnt
     * @param url
     * @param contentType
     * @param jsonData
     * @return
     */
    public static Map<String,Object> doPostByJson(final int retryCnt, final String url, final String contentType, final String jsonData) {
        
        log.info(" doPostByJson url :: {} ", url);
        log.info(" doPostByJson contentType :: {} ", contentType);
        log.info(" doPostByJson jsonData :: {} ", jsonData);
        
        Map<String,Object> rslt = new HashMap<>();
        
        String rsltJson = "";
        
        int tryCnt = 1;
        
        while( tryCnt <= retryCnt ) {
            
            try (CloseableHttpClient client = HttpClients.createDefault()) {
                
                
                HttpPost httpPost = new HttpPost(url);
                StringEntity entity = new StringEntity(jsonData, "UTF-8");
                log.info("entity : {}", entity.toString());
                httpPost.setEntity(entity);
                httpPost.setHeader("Accept", contentType);
                httpPost.setHeader("Content-type", contentType);

                CloseableHttpResponse response = client.execute(httpPost);

                int statusCode = response.getStatusLine().getStatusCode();

                rslt.put("statusCode", statusCode);

                log.info("response : {}", response.toString());

                if(HttpStatus.SC_OK == statusCode) {
                    rsltJson = EntityUtils.toString(response.getEntity(), "UTF-8");
                    rslt.put("rsltJson", rsltJson);
                    log.info("rsltJson :: {}", rsltJson);
                    break;
                }else {
                    tryCnt++;
                    
                    if(retryCnt == tryCnt) {
                        String errMsg = "["+ statusCode +"] doPostByJson response code not valid";
                        log.info("errMsg :: {}", errMsg);
                    }
                }
                
            }catch(Exception e){
                tryCnt++;
                
                if(retryCnt == tryCnt) {
                    String errMsg = "doPostByJson error has occurred";
                    log.error(errMsg, e);
                    
                    rslt.put("statusCode", 999);
                }
                
            }
            
        }
        
        
        return rslt;
    }
    
    
    /**
     * 
     * <pre>
     * 1. 개요       : Http Put 방식 요청/응답 데이터 Json 
     * 2. 처리내용    : Http Put 방식 요청/응답 데이터 Json 처리함 
     * </pre>
     * @Method Name : doPutByJson
     * @date        : 2021. 10. 13.
     * @author      : 2190014
     * @history     : 
     * ================================================================================
     * 변경일       작성자                          내용
     * --------------------------------------------------------------------------------
     * 2021.10.13 2190014          최초 작성   
     * 
     * @param retryCnt
     * @param url
     * @param contentType
     * @param jsonData
     * @return
     */
    public static Map<String,Object> doPutByJson(final int retryCnt, final String url, final String contentType, final String jsonData){
        
        log.info(" doPutByJson url :: {} ", url);
        log.info(" doPutByJson contentType :: {} ", contentType);
        log.info(" doPutByJson jsonData :: {} ", jsonData);
        
        Map<String,Object> rslt = new HashMap<>();
        
        String rsltJson = "";
        
        int tryCnt = 1;
        
        while( tryCnt <= retryCnt ) {
            
            try (CloseableHttpClient client = HttpClients.createDefault()) {
                
                
                HttpPut httpPut = new HttpPut(url);
                StringEntity entity = new StringEntity(jsonData, "UTF-8");
                httpPut.setEntity(entity);
                httpPut.setHeader("Accept", contentType);
                httpPut.setHeader("Content-type", contentType);
                
                CloseableHttpResponse response = client.execute(httpPut);
                
                int statusCode = response.getStatusLine().getStatusCode();
                
                rslt.put("statusCode", statusCode);
                
                if(HttpStatus.SC_OK == statusCode) {
                    rsltJson = EntityUtils.toString(response.getEntity(), "UTF-8");
                    rslt.put("rsltJson", rsltJson);
                    log.info("rsltJson :: {}", rsltJson);
                    break;
                }else {
                    tryCnt++;
                    
                    if(retryCnt == tryCnt) {
                        String errMsg = "["+ statusCode +"] doPutByJson response code not valid";
                        log.info("errMsg :: {}", errMsg);
                    }
                }
                
            }catch(Exception e){
                tryCnt++;
                
                if(retryCnt == tryCnt) {
                    String errMsg = "doPutByJson error has occurred";
                    log.error(errMsg, e);
                }
                
            }
            
        }
        
        
        return rslt;
    }
    
    /**
     * 
     * <pre>
     * 1. 개요       : Http Delete 방식 요청/응답 데이터 Json 
     * 2. 처리내용    : Http Delete 방식 요청/응답 데이터 Json 처리함
     * </pre>
     * @Method Name : doDeleteByJson
     * @date        : 2021. 10. 13.
     * @author      : 2190014
     * @history     : 
     * ================================================================================
     * 변경일       작성자                          내용
     * --------------------------------------------------------------------------------
     * 2021.10.13 2190014          최초 작성   
     * 
     * @param retryCnt
     * @param url
     * @param contentType
     * @param jsonData
     * @return
     */
    public static Map<String,Object> doDeleteByJson(final int retryCnt, final String url, final String contentType, final String jsonData){
        
        log.info(" doDeleteByJson url :: {} ", url);
        log.info(" doDeleteByJson contentType :: {} ", contentType);
        log.info(" doDeleteByJson jsonData :: {} ", jsonData);
        
        Map<String,Object> rslt = new HashMap<>();
        
        String rsltJson = "";
        
        int tryCnt = 1;
        
        while( tryCnt <= retryCnt ) {
            
            try (CloseableHttpClient client = HttpClients.createDefault()) {
                
                
                HttpDelete httpDelete = new HttpDelete(url);
                httpDelete.setHeader("Accept", contentType);
                httpDelete.setHeader("Content-type", contentType);
                
                CloseableHttpResponse response = client.execute(httpDelete);
                
                int statusCode = response.getStatusLine().getStatusCode();
                
                rslt.put("statusCode", statusCode);
                
                if(HttpStatus.SC_OK == statusCode) {
                    rsltJson = EntityUtils.toString(response.getEntity(), "UTF-8");
                    rslt.put("rsltJson", rsltJson);
                    log.info("rsltJson :: {}", rsltJson);
                    break;
                }else {
                    tryCnt++;
                    
                    if(retryCnt == tryCnt) {
                        String errMsg = "["+ statusCode +"] doDeleteByJson response code not valid";
                        log.info("errMsg :: {}", errMsg);
                    }
                }
                
            }catch(Exception e){
                tryCnt++;
                
                if(retryCnt == tryCnt) {
                    String errMsg = "doDeleteByJson error has occurred";
                    log.error(errMsg, e);
                }
                
            }
            
        }
        
        
        return rslt;
    }
    
    
    /**
     * 
     * <pre>
     * 1. 개요       : Http Post 파라메터 방식 연동 
     * 2. 처리내용    : Http Post 파라메터 방식을 연동 처리함
     * </pre>
     * @Method Name : doPostByParam
     * @date        : 2021. 10. 13.
     * @author      : 2190014
     * @history     : 
     * ================================================================================
     * 변경일       작성자                          내용
     * --------------------------------------------------------------------------------
     * 2021.10.13 2190014          최초 작성   
     * 
     * @param retryCnt
     * @param url
     * @param param
     * @return
     */
    public static Map<String,Object> doPostByParam(final int retryCnt, final String url, final Map<String,Object> param){
        
        log.info(" doPostByParam url :: {} ", url);
        log.info(" doPostByParam param :: {} ", param);
        
        Map<String,Object> rslt = new HashMap<>();
        
        String rsltXml = "";
        
        int tryCnt = 1;
        
        while( tryCnt <= retryCnt ) {
            
            try (CloseableHttpClient client = HttpClients.createDefault()) {
                
                
                HttpPost httpPost = new HttpPost(url);
                
                List<NameValuePair> paramList = new ArrayList<>();
                
                if(MapUtils.isNotEmpty(param)) {
                    
                    Set<Entry<String, Object>> entrySet = param.entrySet();
                    
                    for (Entry<String, Object> entry : entrySet) {
                        String dataKey = entry.getKey();
                        paramList.add(new BasicNameValuePair(dataKey, (String) entry.getValue()));
                    }
                }
                
                httpPost.setEntity(new UrlEncodedFormEntity(paramList, "UTF-8"));
                httpPost.addHeader("Connection", "close");
                
                CloseableHttpResponse response = client.execute(httpPost);
                
                int statusCode = response.getStatusLine().getStatusCode();
                
                rslt.put("statusCode", statusCode);
                
                if(HttpStatus.SC_OK == statusCode) {
                    rsltXml = EntityUtils.toString(response.getEntity(), "UTF-8");
                    rslt.put("rsltXml", rsltXml);
                    log.info("rsltXml :: {}", rsltXml);
                    break;
                }else {
                    tryCnt++;
                    
                    if(retryCnt == tryCnt) {
                        String errMsg = "["+ statusCode +"] doPostByParam response code not valid";
                        log.info("errMsg :: {}", errMsg);
                    }
                }
                
            }catch(Exception e){
                tryCnt++;
                
                if(retryCnt == tryCnt) {
                    String errMsg = "doPostByParam error has occurred";
                    log.error(errMsg, e);
                }
                
            }
            
        }
        
        
        return rslt;
    }
    
    
    /**
     * 
     * <pre>
     * 1. 개요       : Http Get 파라메터 방식 연동 
     * 2. 처리내용    : Http Get 파라메터 방식을 연동 처리함
     * </pre>
     * @Method Name : doGetByParam
     * @date        : 2021. 10. 13.
     * @author      : 2190014
     * @history     : 
     * ================================================================================
     * 변경일       작성자                          내용
     * --------------------------------------------------------------------------------
     * 2021.10.13 2190014          최초 작성   
     * 
     * @param retryCnt
     * @param url
     * @param param
     * @return
     */
    public static Map<String,Object> doGetByParam(final int retryCnt, final String url, final Map<String,Object> param){
        
        log.info(" doGetByParam param :: {} ", param);
        
        Map<String,Object> rslt = new HashMap<>();
        
        String rsltXml = "";
        
        int tryCnt = 1;
        
        while( tryCnt <= retryCnt ) {
            
            try (CloseableHttpClient client = HttpClients.createDefault()) {
                
                StringBuilder urlBuf = new StringBuilder(url);
                
                if(MapUtils.isNotEmpty(param)) {
                    
                    Set<Entry<String, Object>> entrySet = param.entrySet();
                    
                    int i = 0;
                    
                    for (Entry<String, Object> entry : entrySet) {
                        String dataKey      = entry.getKey();
                        String dataValue    = String.valueOf(entry.getValue());
                    
                        if(i == 0) {
                            urlBuf.append("?");
                        }else {
                            urlBuf.append("&");
                        }
                        
                        urlBuf.append(dataKey).append("=");
                        urlBuf.append(dataValue);
                        
                        i++;
                    }
                }

                log.info(" doGetByParam url :: {} ", urlBuf.toString());
                HttpGet httpGet = new HttpGet(urlBuf.toString());
                
                CloseableHttpResponse response = client.execute(httpGet);
                
                int statusCode = response.getStatusLine().getStatusCode();
                
                rslt.put("statusCode", statusCode);
                
                if(HttpStatus.SC_OK == statusCode) {
                    rsltXml = EntityUtils.toString(response.getEntity(), "UTF-8");
                    rslt.put("rsltXml", rsltXml);
                    log.info("rsltXml :: {}", rsltXml);
                    break;
                }else {
                    tryCnt++;
                    
                    if(retryCnt == tryCnt) {
                        String errMsg = "["+ statusCode +"] doGetByParam response code not valid";
                        log.info("errMsg :: {}", errMsg);
                    }
                }
                
            }catch(Exception e){
                tryCnt++;
                
                if(retryCnt == tryCnt) {
                    String errMsg = "doGetByParam error has occurred";
                    log.error(errMsg, e);
                }
                
            }
            
        }
        
        
        return rslt;
    }

    /**
     *
     * <pre>
     * 1. 개요       : Http Post 방식 요청/응답 데이터 Json
     * 2. 처리내용    : Http Post 방식 요청/응답 데이터 Json 처리함
     * </pre>
     * @Method Name : doPostByFrom
     * @date        : 2021. 9. 7.
     * @author      : 2190014
     * @history     :
     * ================================================================================
     * 변경일       작성자                          내용
     * --------------------------------------------------------------------------------
     * 2021.09.07 2190014          최초 작성
     *
     * @param retryCnt
     * @param url
     * @param contentType
     * @param param
     * @return
     */
    public static Map<String,Object> doPostByFrom(final int retryCnt, final String url, final String contentType, final Map<Object,String> paramMap) {

        log.info(" doPostByJson url :: {} ", url);
        log.info(" doPostByJson contentType :: {} ", contentType);
        log.info(" doPostByJson paramMap :: {} ", paramMap);

        Map<String,Object> rslt = new HashMap<>();

        String rsltJson = "";

        int tryCnt = 1;

        while( tryCnt <= retryCnt ) {

            try (CloseableHttpClient client = HttpClients.createDefault()) {

                HttpPost httpPost = new HttpPost(url);

                List<NameValuePair> nameValuePairs = setHttpNameValuePair(paramMap);

                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                httpPost.setHeader("Content-type", contentType);

                CloseableHttpResponse response = client.execute(httpPost);

                int statusCode = response.getStatusLine().getStatusCode();

                rslt.put("statusCode", statusCode);

                log.info("response : {}", response.toString());

                if(HttpStatus.SC_OK == statusCode) {
                    rsltJson = EntityUtils.toString(response.getEntity(), "UTF-8");
                    rslt.put("rsltJson", rsltJson);
                    log.info("rsltJson :: {}", rsltJson);
                    break;
                }else {
                    tryCnt++;

                    if(retryCnt == tryCnt) {
                        String errMsg = "["+ statusCode +"] doPostByJson response code not valid";
                        log.info("errMsg :: {}", errMsg);
                    }
                }

            }catch(Exception e){
                tryCnt++;

                if(retryCnt == tryCnt) {
                    String errMsg = "doPostByJson error has occurred";
                    log.error(errMsg, e);

                    rslt.put("statusCode", 999);
                }

            }

        }

        return rslt;
    }

    private static List<NameValuePair> setHttpNameValuePair(Map<Object,String> paramMap){
        List<NameValuePair> nameValuePairs = new ArrayList<>();

        if(MapUtils.isNotEmpty(paramMap)){
            for (Map.Entry<Object, String> entry : paramMap.entrySet()) {
                String key = (String) entry.getKey();
                String value = entry.getValue();
                nameValuePairs.add(new BasicNameValuePair(key,value));
            }
        }else{
            return nameValuePairs;
        }

        return nameValuePairs;
    }

}
