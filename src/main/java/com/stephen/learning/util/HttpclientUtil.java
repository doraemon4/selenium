package com.stephen.learning.util;

import com.stephen.learning.config.HttpConnectionManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @Auther: jack
 * @Date: 2018/8/30 22:37
 * @Description:
 */
@Slf4j
@Component
public class HttpclientUtil {
    @Autowired
    private HttpConnectionManager connManager;

    private CookieStore cookieStore=null;

    /**
     * get请求不含有参数
     *
     * @param url
     * @return
     */
    public void get(String url) {
        CloseableHttpClient httpClient = connManager.getHttpClient(cookieStore);
        HttpGet request = new HttpGet(url);
        CloseableHttpResponse httpResponse = null;
        getResponse(httpClient, httpResponse, request);
    }


    /**
     * 发送post请求
     *
     * @param url
     * @param queries
     * @param map
     * @param headers
     * @param encoding
     * @return
     */
    public void post(String url, Map<String, String> queries, Map<String, String> map, Map<String, String> headers, String encoding)
            throws UnsupportedEncodingException {
        CloseableHttpClient httpClient = connManager.getHttpClient(cookieStore);
        //请求参数在路径上
        StringBuilder sb = new StringBuilder(url);
        if (queries != null && queries.keySet().size() > 0) {
            boolean firstFlag = true;
            Iterator iterator = queries.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry<String, String>) iterator.next();
                if (firstFlag) {
                    sb.append("?" + entry.getKey() + "=" + entry.getValue());
                    firstFlag = false;
                } else {
                    sb.append("&" + entry.getKey() + "=" + entry.getValue());
                }
            }
        }
        HttpPost httpPost = new HttpPost(sb.toString());

        //装填请求数据
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        //设置参数到请求对象中
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));

        //请求头
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.addHeader(entry.getKey(), entry.getValue());
            }
        }
        CloseableHttpResponse httpResponse = null;
        getResponse(httpClient, httpResponse, httpPost);
    }

    private void getResponse(CloseableHttpClient httpClient, CloseableHttpResponse httpResponse, HttpRequestBase request) {
        try {
            httpResponse = httpClient.execute(request);
            String content = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            System.out.println(content);
        } catch (Exception e) {
            log.error("请求出错", e);
        } finally {
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 添加cookie
     * @param name
     * @param value
     * @param domain
     * @param path
     */
    private void addCookie(String name, String value, String domain, String path) {
        if (name == null || name.isEmpty())
            return;
        for (Cookie cookie : this.cookieStore.getCookies()) {
            if (name.equals(cookie.getName())) {
                return;
            }
        }
        BasicClientCookie cookie = new BasicClientCookie(name, value);
        //设置domain
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setExpiryDate(DateUtils.addMinutes(new Date(), 30));
        this.cookieStore.addCookie(cookie);
    }

    public void restoreCookie(String cookieStr, String domain) {
        Map<String, String> cookieMap;
        if (cookieStr != null) {
            cookieMap = convertJsonToMap(cookieStr);
            for (Map.Entry<String, String> entry : cookieMap.entrySet()) {
                this.addCookie(entry.getKey(), entry.getValue(), domain, "/");
            }
        }
    }

    public static Map<String, String> convertJsonToMap(String cookieJson) {
        Map<String, String> map = new HashMap<>();
        cookieJson = cookieJson.trim();
        if (cookieJson.contains(";")) {
            String[] pairs = cookieJson.split(";");
            for (String pair : pairs) {
                int index = pair.indexOf("=");
                String key = pair.substring(0, index).trim();
                String value = pair.substring(index + 1).trim();
                map.put(key, value);
            }
        }
        return map;
    }
}
