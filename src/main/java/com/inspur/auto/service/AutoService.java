package com.inspur.auto.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface AutoService {
    public void insertExpectedResult(MultipartFile multipartfile,String platFormId) throws IOException;
    public void updateExpectedResult(MultipartFile multipartfile,String platFormId) throws IOException;
    public void autoExcute(String platFormId, Map<Object, Object> requestConstructMap) throws Exception;
}
