package com.thi.bt.knn.service;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class APIService {


    public HttpClient httpClient = null;
    private String tokenString = null;

    public APIService() {
        if (httpClient == null) {
            httpClient = getDefaultHttpClient();
        }
    }
    public static HttpClient getDefaultHttpClient() {
        HttpConnectionManagerParams params = new HttpConnectionManagerParams();
        params.setSoTimeout(120 * 1000);
        params.setConnectionTimeout(90 * 1000);
        params.setMaxTotalConnections(200);
        params.setDefaultMaxConnectionsPerHost(200);
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        connectionManager.setParams(params);

        return new HttpClient(connectionManager);
    }

    public String callApi(List<Long> data) {
        try {
            HashMap<String, String> headerParam = new HashMap<>();
            String response = null;
            PostMethod method = new PostMethod("https://kpdl.vjet.software/predict");
            headerParam.put("Accept", "application/json");
            headerParam.put("Content-Type", "application/json");
            for (Map.Entry<String, String> hdParam : headerParam.entrySet()) {
                method.addRequestHeader(hdParam.getKey(), hdParam.getValue());
            }
            method.getParams().setContentCharset("utf-8");
            StringRequestEntity requestEntity;
//            requestEntity = new StringRequestEntity("[36,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]", "application/json", "UTF-8");
            requestEntity = new StringRequestEntity(data.toString(), "application/json", "UTF-8");
            method.setRequestEntity(requestEntity);
            int status = httpClient.executeMethod(method);
            if (status == 200) {
                response = method.getResponseBodyAsString();
                response = (response != null) ? response.trim() : response;
                System.out.println("========= End Call API ===========");
                return response;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}