package com.inspur.auto.service;

import com.inspur.auto.entity.po.AutoExpectedResult;

import java.util.List;

public interface ExpectedResultService {
    public List<AutoExpectedResult> selectExpectedResult(String formId);
}
