package com.sakura.controller;

import com.sakura.entity.ExcelTest;
import com.sakura.entity.Sex;
import com.sakura.util.OutExcelHelp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/util")
public class OutExcelController {

    @RequestMapping("/outexcel")
    public String outExcel(){

        //创建实例 泛型为需要导出的List的单个元素的类型
        OutExcelHelp<ExcelTest> outExcelHelp = new OutExcelHelp<ExcelTest>();
        //创建表头
        String [] title = new String[]{"账号","用户名","创建时间","密码","金额"};
        //创建表头与对象的属性对应关系
        HashMap<String,String> titleMap = new HashMap<String, String>();
        titleMap.put("账号","id");
        titleMap.put("用户名","userName");
        titleMap.put("创建时间","creatTime");
        titleMap.put("密码","password");
        titleMap.put("金额","money");
        //创建要打印的list对象
        List<ExcelTest> dataList = new ArrayList<>();
        for (int i = 0;i<100;i++){
            ExcelTest excelTest = new ExcelTest();
            excelTest.setId(1000+i);
            excelTest.setCreatTime("2018-07-20 11:40");
            excelTest.setMoney(i+50);
            excelTest.setUserName("sakura"+i);
            excelTest.setPassword((45645+i)+"");
            dataList.add(excelTest);
        }
        //创建保存地址
        String saveAddress = "E:\\pio\\utilTest1.xlsx";
        //调用方法生成
        outExcelHelp.outToExcel(title,titleMap,dataList,saveAddress);
        return "success";
    }
}
