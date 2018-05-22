package com.wp.weipu.test;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zwp
 * @version 1.0
 * @description: 用于处理excel
 * @date 2018-04-28
 */

public class ExcelUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);
/**begin read excel file**/
    /**
     * 读取本地excel文件，判断文件类型，成功返回workbook对象
     *
     * @param realPath 文件路径
     * @param fileName 文件名称
     * @return Workbook对象
     */
    public static Workbook readExcelFile(String realPath, String fileName) {
        Workbook workbook = null;
        File file = null;
        InputStream in = null;
        file = new File(realPath + "/" + fileName);
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return getWorkbook(in, fileName);
    }

    /**
     * todo:补充
     * @param in
     * @param filename
     * @return
     */
    public static Workbook readExcelFileInputStream(FileInputStream in, String filename) {
        return null;
    }

    /**
     * @param in
     * @param filename
     * @return
     */
    public static Workbook readExcelInputStream(InputStream in, String filename) {
        return null;
    }

    /**
     * 判断文件类型，及获取workbook
     *
     * @param in
     * @param filename
     * @return
     */
    private static Workbook getWorkbook(InputStream in, String filename) {
        Workbook workbook = null;
        //获取文件类型后缀
        String suf = filename.substring(filename.indexOf(".") + 1);
        try {
            //判断文件类型
            if ("xls".equalsIgnoreCase(suf)) {
                //读取xls文件
                workbook = new HSSFWorkbook(in);
                in.close();
                logger.info("读取到xls文件！！");
            } else if ("xlsx".equalsIgnoreCase(suf)) {
                //读取xlsx文件
                workbook = new XSSFWorkbook(in);
                in.close();
                logger.info("读取到xlsx文件！！！");
            } else {
                in.close();
                logger.info("文件类型出错！！！");
                throw new IllegalStateException("文件类型出错！！！");
            }
            //why:多层catch有助于查找错误
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("文件读取失败！！！！", e);
        }
        return workbook;
    }
/**end read file**/
    /**
     * 读取文档标题头
     * 使用map是想组成 行列
     * @param workbook
     * @return
     */
    public static List<String> readExcelTitle(Workbook workbook) {
        if(null==workbook){
            logger.info("reading workbook title is null !!");
            throw new IllegalStateException("workbook is null in readExcelContent !!");
        }
        Sheet sheet=null;
        Row row=null;
        List<String> titles=new ArrayList<>();
        sheet=workbook.getSheetAt(0);
        row=sheet.getRow(0);
        int colNum=row.getRowNum();
        row.getCell(0);
        row.getLastCellNum();
        return null;
    }

    public static Map<Integer,Map<Integer,String>> readExcelContent(){

        return null;
    }
}
