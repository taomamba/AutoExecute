package frm.base;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;

@Data
public class ExcelData {
    public String[] titles;
    public JSONArray dataList;
    public String sheetName;
    public String headName;
}
