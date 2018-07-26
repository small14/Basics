package com.sakura.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.*;

public class PropertiesHelp {
    public static Map<String,String> propertiesMap = new HashMap<String,String>();

    public PropertiesHelp(){
         Logger log = LoggerFactory.getLogger(PropertiesHelp.class);
        try {
            InputStream in =this.getClass().getClassLoader().getResourceAsStream("properties/sysConfig.properties");
            Properties properties = new Properties();
            properties.load(in);

            Set<Map.Entry<Object,Object>> set = properties.entrySet();
            Iterator<Map.Entry<Object,Object>> iterator = set.iterator();
            while (iterator.hasNext()){
                Map.Entry<Object,Object> entry = iterator.next();
                propertiesMap.put(String.valueOf(entry.getKey()),String.valueOf(entry.getValue()));
            }
            in.close();
            log.info("初始化properties工具成功");
        }catch (Exception e){
            log.error("初始化properties工具失败:"+e.getMessage());
        }

    }

    public static String get(String key){
        return propertiesMap.get(key);
    }
}
