package com.gxitsky.httpclient;

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

public class HttpClientDemo {

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

//            HttpGet httpget = new HttpGet("https://www.bin.org/");
            HttpPost httpPost = new HttpPost("https://localhost:8080");

            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            String head1 = "";
            String head2 = "";
            String body = "";
            String signdate = "";

            Map<String, String> stringMap = new HashMap<>();
            stringMap.put("head1", secInHead);
            stringMap.put("head2", busInHead);
            stringMap.put("body", busInBody);

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
