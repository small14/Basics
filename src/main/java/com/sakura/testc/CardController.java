package com.sakura.testc;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@Controller
@RequestMapping()
public class CardController {

    private static final String key = "AAA20180731BBBBB";
    private static final String auth = "AAA20180731B";

    private static final Logger log = LoggerFactory.getLogger(CardController.class);

    /**
     * @describe 虚拟卡开卡
     * @param request
     * @return
     */
    @RequestMapping(value="/plateform/opencard.action",produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    @ResponseBody
    public Object openCard(HttpServletRequest request){
        String sign = request.getParameter("sign");
        SortedMap<String,String> paramMap = new TreeMap<>();
        paramMap.put("userCardId",request.getParameter("userCardId"));
        paramMap.put("cardId",request.getParameter("cardId"));
        paramMap.put("ownerId",request.getParameter("ownerId"));
        paramMap.put("nonceStr",request.getParameter("nonceStr"));
        paramMap.put("timestamp",request.getParameter("timestamp"));
        paramMap.put("key",key);
        paramMap.put("auth",auth);
        String resultSign = getSign(paramMap);
        Map<String,Object> responseMap = null;
        if (sign.equals(resultSign)){//签名验证通过
            log.info("【虚拟卡开卡记录上报】签名验证通过,返回成功数据");
           responseMap = new HashMap<>();
           byte normal = 1;
           responseMap.put("normal",normal);
           responseMap.put("msg","成功");
        }
        log.info(JSON.toJSONString(responseMap));
        return JSON.toJSONString(responseMap);
    }

    /**
     * @describe 虚拟卡删除
     * @param request
     * @return
     */
    @RequestMapping(value = "/plateform/delcard.action",produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    @ResponseBody
    public Object delcard(HttpServletRequest request){
        String sign = request.getParameter("sign");
        SortedMap<String,String> paramMap = new TreeMap<>();
        paramMap.put("userCardId",request.getParameter("userCardId"));
        paramMap.put("nonceStr",request.getParameter("nonceStr"));
        paramMap.put("timestamp",request.getParameter("timestamp"));
        paramMap.put("key",key);
        paramMap.put("auth",auth);
        String resultSign = getSign(paramMap);
        Map<String,Object> responseMap = null;
        if (sign.equals(resultSign)){//签名验证通过
            log.info("【虚拟卡删除记录上报】签名验证通过,返回成功数据");
            responseMap = new HashMap<>();
            byte normal = 1;
            responseMap.put("normal",normal);
            responseMap.put("msg","成功");
        }
        log.info(JSON.toJSONString(responseMap));
        return JSON.toJSONString(responseMap);
    }


    /**
     * @describe M1本地卡开卡记录上报
     * @param request
     * @return
     */
    @RequestMapping(value = "/openiccard.action",produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    @ResponseBody
    public Object openICcard(HttpServletRequest request){
        String sign = request.getParameter("sign");
        SortedMap<String,String> paramMap = new TreeMap<>();
        paramMap.put("cardId",request.getParameter("cardId"));
        paramMap.put("phone",request.getParameter("phone"));
        paramMap.put("userName",request.getParameter("userName"));
        paramMap.put("agentKey",request.getParameter("agentKey"));
        paramMap.put("nonceStr",request.getParameter("nonceStr"));
        paramMap.put("timestamp",request.getParameter("timestamp"));
        paramMap.put("key",key);
        paramMap.put("auth",auth);
        String resultSign = getSign(paramMap);
        Map<String,Object> responseMap = null;
        if (sign.equals(resultSign)){//签名验证通过
            log.info("【本地卡开卡记录上报】签名验证通过,返回成功数据");
            responseMap = new HashMap<>();
            byte normal = 1;
            responseMap.put("normal",normal);
            responseMap.put("msg","成功");
        }
        log.info(JSON.toJSONString(responseMap));
        return JSON.toJSONString(responseMap);

    }

    /**
     * @describle M1本地卡充值记录上报
     * @param request
     * @return
     */
    @RequestMapping(value = "/addiccardrecharge.action",produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    @ResponseBody
    public Object addICcardRecharge(HttpServletRequest request){
        String sign = request.getParameter("sign");
        SortedMap<String,String> paramMap = new TreeMap<>();
        paramMap.put("cardId",request.getParameter("cardId"));
        paramMap.put("money",request.getParameter("money"));
        paramMap.put("balance",request.getParameter("balance"));
        paramMap.put("count",request.getParameter("count"));
        paramMap.put("agentKey",request.getParameter("agentKey"));
        paramMap.put("nonceStr",request.getParameter("nonceStr"));
        paramMap.put("timestamp",request.getParameter("timestamp"));
        paramMap.put("key",key);
        paramMap.put("auth",auth);
        String resultSign = getSign(paramMap);
        Map<String,Object> responseMap = null;
        if (sign.equals(resultSign)){//签名验证通过
            log.info("【本地卡充值记录上报】签名验证通过,返回成功数据");
            responseMap = new HashMap<>();
            byte normal = 1;
            responseMap.put("normal",normal);
            responseMap.put("msg","成功");
        }
        log.info(JSON.toJSONString(responseMap));
        return JSON.toJSONString(responseMap);

    }





    //加密
    public String getSign(SortedMap<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String,String> entry : params.entrySet()) {
            if (!entry.getKey().equals("sign")) { //拼装参数,排除sign
                if (!StringUtils.isEmpty(entry.getKey()) && !StringUtils.isEmpty(entry.getValue()))
                    sb.append(entry.getValue());
            }
        }
        return DigestUtils.md5Hex(sb.toString()).toUpperCase();
    }
}
