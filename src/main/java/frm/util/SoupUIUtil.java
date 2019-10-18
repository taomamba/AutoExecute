package frm.util;

import frm.base.RequestConstruct;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * soupUI读取案例和请求报文工具
 *
 * 返回值：{caseCode,{endpoint:message})}
 */
public class SoupUIUtil {
    public static Map<String, Object> readSoupUIXml(MultipartFile multipartfile) {
        SAXReader reader = new SAXReader();
        Map<String, Object> requestConstructMap = new HashMap<>();
        //   File file = new File(url);
        try {
            Document document = reader.read(multipartfile.getInputStream());
            Element root = document.getRootElement();
            List<Element> childElement = root.elements();
            Element element = childElement.get(1);
            Element e = element.element("operation");
            List<Element> elements = e.elements("call");
            for (Element callElement : elements) {
                RequestConstruct requestConstruct = new RequestConstruct();
                String endpoint = callElement.element("endpoint").getText();
                String request = callElement.element("request").getText();
                requestConstruct.setEndpoint(endpoint);
                requestConstruct.setMessage(request);
                String caseCode = callElement.attributeValue("name");
                requestConstructMap.put(caseCode, requestConstruct);
            }
        }catch (Exception e1) {
            // TODO Auto-generated catch block3
            e1.printStackTrace();
        }
        return requestConstructMap;
    }
}
