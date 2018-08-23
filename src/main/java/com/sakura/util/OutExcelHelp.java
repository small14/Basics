package com.sakura.util;


import com.sakura.entity.ExcelTest;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OutExcelHelp <T> {

    private static final Logger log = LoggerFactory.getLogger(OutExcelHelp.class);

    /**
     *
     * 注①：该版本生成的是xlsx格式的EXCEL文件，所以saveAddress的文件结尾得为 .xlsx
     * @param title 标头 根据下标按顺序生成
     * @param titleMap key=标头 value=对应list的每个实体元素的属性名
     * @param dataList 需要生成EXCLE表格的list数据
     * @param saveAddress 保存的地址 包括文件名和格式
     * @param sheetName excel表格的sheetName
     * @return
     */
    public void outToExcel(String[] title, HashMap<String,String> titleMap, List<T> dataList,String saveAddress,String sheetName){
        try {
            XSSFWorkbook workbook1 = new XSSFWorkbook();
            SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(workbook1, 100);
            //创建一个 Sheet
            Sheet first = sxssfWorkbook.createSheet(sheetName);
            //因为要添加标头 所以需要多出一行 从-1开始
            for (int rowCount = -1;rowCount < dataList.size();rowCount++){
                //创建行的时候不能从-1开始所以+1变成0,例如第5条数据则为5+1 是第六行
                Row row = first.createRow(rowCount+1);
                for (int columnCount = 0;columnCount < title.length;columnCount++){
                    if (rowCount == -1){
                        //设置标题头
                        row.createCell(columnCount).setCellValue(title[columnCount]);
                    }else {
                        //得到此列对应的属性名
                         String attribute = titleMap.get(title[columnCount]);
                         //将首字母大写加上get取得方法名
                        StringBuffer get = new StringBuffer("get");
                        StringBuffer getMethod = get.append(captureName(attribute));
                        //获取当前行的对象
                        T data = dataList.get(rowCount);
                        //通过反射的方式获取当前格子的值
                        Method method = data.getClass().getMethod(getMethod.toString());
                        String value = method.invoke(data)+"";
                        row.createCell(columnCount).setCellValue(value);
                    }
                }
            }
            FileOutputStream out = new FileOutputStream(saveAddress);
            sxssfWorkbook.write(out);
            out.close();
        }catch (Exception e){
            log.error("生成EXCEL文件失败:",e);
        }
    }

    //首字母大写
    public String captureName(String name) {
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return  name;
    }


    public static void main(String[] args) {
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
        outExcelHelp.outToExcel(title,titleMap,dataList,saveAddress,"sakura");
    }
}
