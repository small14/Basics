package com.sakura.controller;

import com.alibaba.fastjson.JSONObject;
import com.sakura.service.UserService;
import com.sakura.util.HttpHelp;
import com.sakura.util.PropertiesHelp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @RequestMapping("/update")
    @ResponseBody
    public String updateUser(){
        userService.updateUser();
        log.info("dada");
        return "success";
    }

    @RequestMapping("/httpget")
    @ResponseBody
    public String httpGetTest(){
        String  access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
        String url = access_token_url.replace("APPID",PropertiesHelp.get("wechat_appid")).replace("APPSECRET",PropertiesHelp.get("wechat_appsecret"));
        JSONObject jsonObject = HttpHelp.get(url);
        return "success";
    }

}
