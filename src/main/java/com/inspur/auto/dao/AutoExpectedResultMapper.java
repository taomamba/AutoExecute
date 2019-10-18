package com.inspur.auto.dao;

import com.inspur.auto.entity.po.AutoExpectedResult;
import frm.base.BaseMapper;

import java.util.List;

public interface AutoExpectedResultMapper extends BaseMapper<AutoExpectedResult> {
    public List<String> selectIdsByFormId(String platFormId);
    public void batchUpdateExpectedResult(List<AutoExpectedResult> autoExpectedResults);
}