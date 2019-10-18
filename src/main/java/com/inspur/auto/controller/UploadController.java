package com.inspur.auto.controller;

import frm.util.POIUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class UploadController {
    @RequestMapping("upload")
    public String uploadFile(@RequestParam("file") MultipartFile file[]) throws IOException {
        String result = POIUtil.readExcel(file[0]).toJSONString();
        System.out.println(result);
        return result;
    }
}
