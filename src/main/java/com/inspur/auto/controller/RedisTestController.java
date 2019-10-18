package com.inspur.auto.controller;

import com.inspur.auto.entity.po.AutoActualResult;
import frm.base.BaseController;
import frm.cache.RedisConstants;
import frm.util.RedisUtil;
import frm.base.StateParameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: RedisTestController
 * @Auther: zhangyingqi
 * @Date: 2018/8/28 17:24
 * @Description:
 */
@Controller
@RequestMapping("/redis")
@Slf4j
public class RedisTestController extends BaseController {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * @auther: taoyucan
     * @date: 17:26 2018/8/28
     * @param: []
     * @return: org.springframework.ui.ModelMap
     * @Description: 测试redis存储&读取
     */
    @RequestMapping(value="/test")
    @ResponseBody
    public ModelMap test(){
        try {
            redisUtil.set("redisTemplate","这是一条测试数据", RedisConstants.datebase1);
            String value = redisUtil.get("redisTemplate",RedisConstants.datebase1).toString();
            logger.info("redisValue="+value);
            logger.info("读取redis成功");
            return getModelMap(StateParameter.SUCCESS, value, "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return getModelMap(StateParameter.FAULT, null, "操作失败");
        }
    }

    @RequestMapping(value="/setUser")
    @ResponseBody
    public ModelMap setUser(){
        try {
            AutoActualResult actualResult = new AutoActualResult();
            actualResult.setId(getUuid());
            redisUtil.set("user",actualResult, RedisConstants.datebase1);
            AutoActualResult res = (AutoActualResult)redisUtil.get("user",RedisConstants.datebase1);
            logger.info("res="+res.toString());
            logger.info("读取redis成功");
            return getModelMap(StateParameter.SUCCESS, res, "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return getModelMap(StateParameter.FAULT, null, "操作失败");
        }
    }

}