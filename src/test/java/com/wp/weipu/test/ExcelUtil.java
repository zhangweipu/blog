package com.wp.weipu.test;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

/**
 * @description: 用于处理excel
 * @author zwp
 * @date 2018-04-28
 * @version 1.0
 */

public class ExcelUtil {

    private static final Logger logger= LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * 读取本地excel文件
     * @param realPath 文件路径
     * @param fileName 文件名称
     * @return
     */
    public static Workbook isExcelFile(String realPath,String fileName){
        Workbook workbook=null;
        File file=null;
        String suf=fileName.substring(fileName.indexOf(".")+1);
        if("xls".equalsIgnoreCase(suf)){

        }
        try {
            file=new File(realPath+"/"+fileName);
        } catch (Exception e) {

        }


        return workbook;
    }

    public static Map<Integer,Map<Integer,String>> readExcelContent(InputStream inputStream){


        return null;
    }
}
