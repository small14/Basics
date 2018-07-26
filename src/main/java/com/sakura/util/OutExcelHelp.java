package com.sakura.util;


import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

public class OutExcelHelp <T> {

    private static final Logger log = LoggerFactory.getLogger(OutExcelHelp.class);

    /**
     * 注①：下文中的"E:\pio\workbook.xlsx" 的文件是一定要存在的，如果没有的话就自己创建一个就可以了
     * 注②：该版本生成的是xlsx格式的EXCEL文件，所以saveAddress的文件结尾得为 .xlsx
     * @param title 标头 根据下标按顺序生成
     * @param titleMap key=标头 value=对应list的每个实体元素的属性名
     * @param dataList 需要生成EXCLE表格的list数据
     * @param saveAddress 保存的地址 包括文件名和格式
     * @return
     */
    public void outToExcel(String[] title, HashMap<String,String> titleMap, List<T> dataList,String saveAddress){
        try {
            //先创建一个EXcel文件
            String excleFilePath = "E:\\pio\\workbook.xlsx";
            XSSFWorkbook workbook1 = new XSSFWorkbook(new FileInputStream(new File(excleFilePath)));
            SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(workbook1, 100);
            Sheet first = sxssfWorkbook.getSheetAt(0);

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
}
