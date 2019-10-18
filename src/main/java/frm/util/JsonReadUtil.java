package frm.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import frm.base.RequestConstruct;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class JsonReadUtil {
    public static Map<String, Object> readJsonFile(MultipartFile multipartfile)throws Exception {
        Map<String, Object> requestConstructMap = new HashMap<>();
        Resource resource = multipartfile.getResource();
        File jsonFile = resource.getFile();
        JSONArray gaspArr = null;
        //读取文件
        String input = FileUtils.readFileToString(jsonFile, "UTF-8");
        //将读取的数据转换成JSONObject
        JSONObject jsonObject = JSONObject.parseObject(input);
        if (jsonObject != null) {
            gaspArr = jsonObject.getJSONArray("list");
        }
        int length = gaspArr.size();
        Map<String, String> resultMap = new HashMap<>();
        for (int i = 0; i < length; i++) {
            RequestConstruct requestConstruct = new RequestConstruct();
            //解析json报文
            JSONObject json = (JSONObject) gaspArr.get(i);
            String caseCode = JSON.toJSONString(json.get("caseCode"));
            String endpoint = JSON.toJSONString(json.get("endpoint"));
            String message = JSON.toJSONString(json.get("message"));
            requestConstruct.setEndpoint(endpoint);
            requestConstruct.setMessage(message);
            requestConstructMap.put(caseCode,requestConstruct);
        }
        return requestConstructMap;

    }
}
