package com.springboot.rest.template.httpclient;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @name: FuMingBank
 * @desc: TODO
 * @author: gxing
 * @date: 2019-05-17 14:41
 **/
public class FuMingBank {

    public static void main(String[] args) throws Exception {

        // Trust own CA and all self-signed certs
//        SSLContext sslcontext = SSLContexts.custom()
//                .loadTrustMaterial(new File("my.keystore"), "nopassword".toCharArray(),
//                        new TrustSelfSignedStrategy())
//                .build();
        SSLContext sslContext = SSLContexts.createSystemDefault();

        // Allow TLSv1 protocol only
//        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
//                sslContext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());

//        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
//                sslContext, new String[]{"TLSv1"}, null, NoopHostnameVerifier.INSTANCE);

//        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);

        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        try {

//            HttpGet httpget = new HttpGet("https://httpbin.org/");
            HttpPost httpPost = new HttpPost("https://icip-test.cqfmbank.com:9012/tcipi/pass/toward/tavTrade.tav");

            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            String secInHead = "{\"channelPass\":\"D212A3412C193FEE39DF15F7BD27E27B\",\"askforSys\":\"77777\",\"transTime\":\"140521\",\"channelNo\":\"790104\",\"transDate\":\"20190517\",\"signType\":\"RSA\",\"transCode\":\"STX007\",\"serialNo\":\"IlQhLBlS\"}";
            String busInHead = "{\"partnerNo\":\"90104\"}";
            String busInBody = "{\"mobile\":\"18829220936\",\"certId\":\"91510107050097138P\"}";
            String signdate = "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";

            Map<String, String> stringMap = new HashMap<>();
            stringMap.put("secInHead", secInHead);
            stringMap.put("busInHead", busInHead);
            stringMap.put("busInBody", busInBody);

//            nvps.add(new BasicNameValuePair("secInHead", secInHead));
//            nvps.add(new BasicNameValuePair("busInHead", busInHead));
//            nvps.add(new BasicNameValuePair("busInBody", busInBody));

            httpPost.setHeader("signdate", signdate);

            String string = JSON.toJSONString(stringMap);

            StringEntity basicHttpEntity = new StringEntity(string, ContentType.APPLICATION_JSON);

            httpPost.setEntity(basicHttpEntity);

            System.out.println("Executing request " + httpPost.getRequestLine());

            CloseableHttpResponse response = httpclient.execute(httpPost);

            try {
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();

                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }
}