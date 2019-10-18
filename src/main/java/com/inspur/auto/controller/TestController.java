package com.inspur.auto.controller;

import com.inspur.auto.dao.AutoActualResultMapper;
import com.inspur.auto.entity.po.AutoActualResult;
import frm.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestController extends BaseController {
    @Autowired
    private AutoActualResultMapper actualResultMapper;

    @ResponseBody
    @RequestMapping("test")
    public Map test() {
        Map<String, Object> paramMap = new HashMap<>();
        List<AutoActualResult> actualResults = actualResultMapper.selectAll();
        paramMap.put("inpsur", actualResults);
        return paramMap;
    }

    @RequestMapping("start")
    public String index(Model model) {
        return "head";
    }
}
