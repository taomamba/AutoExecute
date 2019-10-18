package frm.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import frm.base.ExcelData;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * excel读写工具类
 */
@Slf4j
public class POIUtil {
    private final static String xls = "xls";
    private final static String xlsx = "xlsx";

    /**
     * 读入excel文件，解析后返回JSonArray
     *
     * @param file
     * @throws IOException
     */
    public static JSONArray readExcel(MultipartFile file) throws IOException {
        //检查文件  
        checkFile(file);
        //获得Workbook工作薄对象  
        Workbook workbook = getWorkBook(file);
        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回  
        List<String[]> list = new ArrayList<String[]>();
        //把数据格式化为jsonArray
        JSONArray jsonArray = new JSONArray();

        if (workbook != null) {
            for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
                //获得当前sheet工作表  
                Sheet sheet = workbook.getSheetAt(sheetNum);
                if (sheet == null) {
                    continue;
                }
                //获得当前sheet的开始行  
                int firstRowNum = sheet.getFirstRowNum();
                //获得当前sheet的结束行  
                int lastRowNum = sheet.getLastRowNum();
                //获取第一行的数据
                Row firstRow = sheet.getRow(firstRowNum);
                //获取第一行的开始列
                int firstRowFirstCellNum = firstRow.getFirstCellNum();
                //获取第一行的结束列
                int firstRowLastCellNum = firstRow.getLastCellNum();
                //循环第一行，把标题加入Map中，方便后续处理
                Map<Integer, String> titleMap = new HashMap<>();
                for (int cellNum = firstRowFirstCellNum; cellNum < firstRowLastCellNum; cellNum++) {
                    Cell cell = firstRow.getCell(cellNum);
                    titleMap.put(cellNum, getCellValue(cell));
                }

                //循环除了第一行的所有行  
                for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {
                    //获得当前行  
                    Row row = sheet.getRow(rowNum);
                    if (row == null) {
                        continue;
                    }
                    //获得当前行的开始列  
                    int firstCellNum = row.getFirstCellNum();
                    //获得当前行的列数  
                    int lastCellNum = row.getPhysicalNumberOfCells();
                    // String[] cells = new String[row.getPhysicalNumberOfCells()];
                    JSONObject jsonObject = new JSONObject();

                    //循环当前行
                    for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
                        Cell cell = row.getCell(cellNum);
                        jsonObject.put(titleMap.get(cellNum), getCellValue(cell));
                        //cells[cellNum] = getCellValue(cell);
                    }
                    //list.add(cells);
                    jsonArray.add(jsonObject);
                }
            }
            workbook.close();
        }
        //  return list;
        return jsonArray;
    }

    public static void checkFile(MultipartFile file) throws IOException {
        //判断文件是否存在  
        if (null == file) {
            log.error("文件不存在！");
            throw new FileNotFoundException("文件不存在！");
        }
        //获得文件名  
        String fileName = file.getOriginalFilename();
        //判断文件是否是excel文件  
        if (!fileName.endsWith(xls) && !fileName.endsWith(xlsx)) {
            log.error(fileName + "不是excel文件");
            throw new IOException(fileName + "不是excel文件");
        }
    }

    public static Workbook getWorkBook(MultipartFile file) {
        //获得文件名  
        String fileName = file.getOriginalFilename();
        //创建Workbook工作薄对象，表示整个excel  
        Workbook workbook = null;
        try {
            //获取excel文件的io流  
            InputStream is = file.getInputStream();
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象  
            if (fileName.endsWith(xls)) {
                //2003  
                workbook = new HSSFWorkbook(is);
            } else if (fileName.endsWith(xlsx)) {
                //2007  
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            log.info(e.getMessage());
        }
        return workbook;
    }

    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        //把数字当成String来读，避免出现1读成1.0的情况  
        if (cell.getCellType() == CellType.NUMERIC) {
            cell.setCellType(CellType.STRING);
        }
        //判断数据的类型  
        switch (cell.getCellType()) {
            case NUMERIC: //数字
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case STRING: //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA: //公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case BLANK: //空值
                cellValue = "";
                break;
            case ERROR: //故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }

    public static void exportExcel(ExcelData excelData, File file) {
        OutputStream out = null;
        try {
             out = new FileOutputStream(file);
            //1.创建工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            //1.1创建合并单元格对象
            CellRangeAddress callRangeAddress = new CellRangeAddress(0, 0, 0, 4);//起始行,结束行,起始列,结束列
            //1.2头标题样式
            HSSFCellStyle headStyle = createCellStyle(workbook, (short) 16);
            //1.3列标题样式
            HSSFCellStyle colStyle = createCellStyle(workbook, (short) 13);
            //2.创建工作表
            HSSFSheet sheet = workbook.createSheet(excelData.getSheetName());
            //2.1加载合并单元格对象
            sheet.addMergedRegion(callRangeAddress);
            //设置默认列宽
            sheet.setDefaultColumnWidth(25);
            //3.创建行
            //3.1创建头标题行;并且设置头标题
            HSSFRow row = sheet.createRow(0);
            HSSFCell cell = row.createCell(0);

            //加载单元格样式
            cell.setCellStyle(headStyle);
            cell.setCellValue(excelData.getHeadName());

            //3.2创建列标题;并且设置列标题
            HSSFRow row2 = sheet.createRow(1);
            String[] titles = excelData.getTitles();
            for (int i = 0; i < titles.length; i++) {
                HSSFCell cell2 = row2.createCell(i);
                //加载单元格样式
                cell2.setCellStyle(colStyle);
                cell2.setCellValue(titles[i]);
            }
            //4.操作单元格;将数据写入excel
            int dataSize;
            int titlesSize;
            JSONArray jsonArray = null;
            if (excelData.getDataList() != null) {
                jsonArray = excelData.getDataList();
                dataSize = jsonArray.size();
                titlesSize = titles.length;
                for (int j = 0; j < dataSize; j++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(j);
                    //创建数据行,前面有两行,头标题行和列标题行
                    HSSFRow row3 = sheet.createRow(j + 2);
                    for (int i = 0; i < titlesSize; i++) {
                        HSSFCell cell1 = row3.createCell(i);
                        cell1.setCellValue((String) jsonObject.get(titles[i]));
                    }
                }
            }
            //5.输出
            workbook.write(out);
            workbook.close();
            //out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param workbook
     * @param fontsize
     * @return 单元格样式
     */
    private static HSSFCellStyle createCellStyle(HSSFWorkbook workbook, short fontsize) {
        // TODO Auto-generated method stub
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);//水平居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        //创建字体
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints(fontsize);
        //加载字体
        style.setFont(font);
        return style;
    }
}