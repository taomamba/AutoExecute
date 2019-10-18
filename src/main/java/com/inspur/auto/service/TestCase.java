package com.inspur.auto.service;
import com.alibaba.fastjson.JSON;
import com.thoughtworks.xstream.XStream;
import frm.base.MessageBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import frm.base.RequestConstruct;
import frm.util.POIUtil;
import frm.util.XMLUtil;
import org.apache.commons.collections4.map.LinkedMap;
import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 * xml转bin的测试
 */
public class TestCase {


    @Test
    public void xmlToBean(){
        String body = "<bodys><exts></exts><rtnCode>MSSZ13</rtnCode><rtnMesg>123</rtnMesg><rtnTime>2019-03-19T14:46:46.670</rtnTime><trnsId>3Ij4kDgC</trnsId><trnsType>msp.004.001</trnsType><version>1.00</version></bodys>";
       MessageBody messageBody = (MessageBody)XMLUtil.convertXmlStrToObject(MessageBody.class,body);
      /* XStream xStream = new XStream();
        xStream.alias("bodys", MessageBody.class);
        xStream.alias("exts", LinkedMap.class);
        MessageBody messageBody = (MessageBody) xStream.fromXML(body);*/
        System.out.println(JSON.toJSONString(messageBody));
    }
    @Test
    public void redExcel() throws IOException {
        File file = new File("C:/Users/taoyucan/Desktop/公安服务地址列表.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), file.getName(),
                ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
        try {
           // List<String[]> strings = POIUtil.readExcel(multipartFile);
            com.alibaba.fastjson.JSONArray jsonArray =  POIUtil.readExcel(multipartFile);
            /*JSONArray jsonArray = JSONArray.fromObject(strings);
            JSONArray.toCollection(jsonArray, ExpectedResult.class)
            jsonArray.toJSONObject(jsonArray);*/
            System.out.println(jsonArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testMap(){
        RequestConstruct requestConstruct = new RequestConstruct();
        requestConstruct.setMessage("13");
        requestConstruct.setEndpoint("key");
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("tao",requestConstruct);
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            RequestConstruct requestConstruct1 = new RequestConstruct();
            requestConstruct1 = (RequestConstruct) entry.getValue();
            System.out.println(requestConstruct1.getMessage());
        }

    }


}
