package frm.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Slf4j
public class HttpClient {
    public static  CloseableHttpClient httpClient = HttpClients.createDefault();
    public static String post(String url, Map<String, String> params) {
        String body = null;
        HttpPost post = postForm(url, params);
        body = invoke(httpClient, post);
        return body;
    }

    public static String get(String url) {
        String body = null;
        HttpGet get = new HttpGet(url);
        body = invoke(httpClient, get);
        return body;
    }

    private static String invoke(CloseableHttpClient httpClient, HttpUriRequest httpost) {
        HttpResponse response = sendRequest(httpClient, httpost);
        String body = paseResponse(response);

        return body;
    }

    public static String paseResponse(HttpResponse response) {
        HttpEntity entity = response.getEntity();
        String body = null;
        try {
            body = EntityUtils.toString(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return body;
    }

    public static HttpResponse sendRequest(CloseableHttpClient httpClient, HttpUriRequest httpost) {
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpost);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private static HttpPost postForm(String url, Map<String, String> params) {

        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            nvps.add(new BasicNameValuePair(key, params.get(key)));
        }

        try {
            httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return httpost;
    }
    public static String postEntity(StringEntity stringEntity,HttpPost httpost){
        String result = null;
        try {
            httpost.setEntity(stringEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            HttpResponse sendResponse = HttpClient.sendRequest(httpClient, httpost);
            result = HttpClient.paseResponse(sendResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String postXML(String message, String url) throws Exception {
        StringEntity stringEntity = new StringEntity(message);
        HttpPost httpost = new HttpPost(url);
        return postEntity(stringEntity,httpost);
    }
    public static String postJSON(StringBuffer message, String url) throws Exception {
        StringEntity stringEntity = new StringEntity(message.toString());
        HttpPost httpost = new HttpPost(url);
        httpost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        httpost.setHeader("accept-encoding", "gzip, deflate");
        return postEntity(stringEntity,httpost);
    }
}