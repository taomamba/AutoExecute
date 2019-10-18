package com.inspur.auto.controller;

import frm.util.StringUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {
    @PostMapping(value = "/login")
    public String login(@RequestParam("userName")String userName, @RequestParam("password")String passWord, Map<String,Object> resultMap){
        if ("admin".equals(userName)&&"123".equals(passWord)){
            resultMap.put("msg","登录成功");
            return "成功";
        }else{
            resultMap.put("msg","登录失败");
            return "失败";

        }


    }

}
