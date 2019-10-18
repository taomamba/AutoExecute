package frm.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import frm.base.ExcelData;
import org.apache.commons.io.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelTest {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\taoyucan\\Desktop\\测试1.xlsx");
        List<String> list = new ArrayList<>();
        list.add("106.37.174.6");
        String[] titles = {"首次发生时间","最近发生时间","告警类型","威胁名称","受害IP","攻击结果","攻击IP","XFF代理","威胁情报IOC/规则ID"
        ,"域名","攻击阶段","威胁级别","URI","次数","状态","资产分组","载荷内容","请求头","请求体","响应头","响应体","webshell文件内容"};
        String sheetName = "浪潮IP";
        String headName = "浪潮服务器安全情况";
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "application/x-excel", IOUtils.toByteArray(fileInputStream));
            JSONArray jsonArray = POIUtil.readExcel(multipartFile);
            System.out.println(jsonArray);
            List<String> ipList = new ArrayList<>();
            JSONArray inspurArray = new JSONArray();
            for (Object jsonObject : jsonArray) {
                JSONObject json = (JSONObject) jsonObject;
                String ip = (String) json.get("受害IP");
                if (list.contains(ip)){
                    inspurArray.add(jsonObject);
                }
            }
            ExcelData excelData = new ExcelData();
            excelData.setTitles(titles);
            excelData.setDataList(inspurArray);
            excelData.setSheetName(sheetName);
            excelData.setHeadName(headName);
            File fileInspur = new File("C:\\Users\\taoyucan\\Desktop\\test1.xls");
            if (!fileInspur.exists()){
                fileInspur.createNewFile();
            }
            POIUtil.exportExcel(excelData,fileInspur);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
