package com.inspur.auto.controller;

import frm.base.ExcelData;
import frm.util.POIUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Controller
public class ExportController {
    @RequestMapping("/export")
    public void exportExcel(HttpServletResponse response) throws UnsupportedEncodingException {
        ExcelData excelData = new ExcelData();
        response.setContentType("application/x-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String("用户列表.xls".getBytes(),"ISO-8859-1"));
        try {
            ServletOutputStream servletOutputStream = response.getOutputStream();
            FileOutputStream fileOutputStream = new FileOutputStream("");
          //  POIUtil.exportExcel(excelData,fileOutputStream);
            if (servletOutputStream!=null){
                servletOutputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
