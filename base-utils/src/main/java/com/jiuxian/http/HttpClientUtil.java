package com.jiuxian.http;


import okhttp3.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class HttpClientUtil {

    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();

    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_XML = "application/xml";
    public static final String APPLICATION_JSON = "application/json";

    /**
     * 默认的 GET 请求
     * @param url
     * @return
     */
    public static String doGet(String url) {
        return doGet(url, new Headers.Builder().build());
    }

    /**
     * 默认的 POST 请求
     * @param url
     * @param data
     * @return
     */
    public static String doPost(String url, String data) {
        return doPost(url, data, null);
    }

    /**
     * 发送 GET 请求
     * @param url
     * @param headers
     * @return
     */
    public static String doGet(String url, Headers headers) {
        try {
            Request request = new Request.Builder().url(url).headers(headers).build();
            Call call = okHttpClient.newCall(request);
            ResponseBody body = call.execute().body();
            System.out.println(body);
            return Objects.requireNonNull(call.execute().body()).string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 发送 POST 请求
     * @param url
     * @param data
     * @param headers
     * @return
     */
    public static String doPost(String url, String data, Headers headers) {
        try {
            if (headers == null || StringUtils.isEmpty(headers.get(CONTENT_TYPE))) {
                headers = Headers.of(CONTENT_TYPE, APPLICATION_JSON);
            }
            MediaType mediaType = MediaType.parse(headers.get(CONTENT_TYPE));
            RequestBody requestBody = RequestBody.create(mediaType, data);
            Request request = new Request.Builder().post(requestBody).url(url).headers(headers).build();
            Call call = okHttpClient.newCall(request);
            return Objects.requireNonNull(call.execute().body()).string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加载证书
     */
    private static CloseableHttpClient closeableHttpClientOfCert(String certPath, String mchId) {
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            FileInputStream inputStream = new FileInputStream(new File(certPath));
            keyStore.load(inputStream, mchId.toCharArray());
            SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, mchId.toCharArray()).build();
            SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslcontext);
            return HttpClients.custom().setSSLSocketFactory(socketFactory).build();
        } catch (Exception e) {
            throw new RuntimeException("加载微信证书时出现异常！" + e.getMessage());
        }
    }

    /**
     * 带证书的POST提交
     * @param certPath
     * @param url
     * @param requestBody
     * @return
     */
    public static String doPostOfCert(String mchId, String certPath, String url, String requestBody) {
        try {
            CloseableHttpClient closeableHttpClient = HttpClientUtil.closeableHttpClientOfCert(certPath, mchId);
            HttpPost httpPost = new HttpPost(url);
            StringEntity postEntity = new StringEntity(requestBody, Charset.forName(StandardCharsets.UTF_8.name()));
            httpPost.addHeader("Content-Type", "text/xml");
            httpPost.setEntity(postEntity);
            HttpResponse response = closeableHttpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, Charset.forName(StandardCharsets.UTF_8.name()));
        } catch (Exception e) {
            throw new RuntimeException("执行请求失败！" + e.getMessage());
        }
    }


}