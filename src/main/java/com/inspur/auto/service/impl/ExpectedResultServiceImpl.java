package com.inspur.auto.service.impl;

import com.inspur.auto.dao.AutoExpectedResultMapper;
import com.inspur.auto.entity.po.AutoExpectedResult;
import com.inspur.auto.service.ExpectedResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.*;

import java.util.List;
@Service
public class ExpectedResultServiceImpl implements ExpectedResultService {
    @Autowired
    private AutoExpectedResultMapper autoExpectedResultMapper;
    @Override
    public List<AutoExpectedResult> selectExpectedResult(String platFormId) {
        Condition condition = new Condition(AutoExpectedResult.class);
        Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("platFormId",platFormId);
        List<AutoExpectedResult> autoExpectedResults = autoExpectedResultMapper.selectByCondition(condition);
        return autoExpectedResults;
    }
}
