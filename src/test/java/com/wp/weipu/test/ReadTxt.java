package com.wp.weipu.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author zwp
 */

public class ReadTxt {
    private static final Logger logger= LoggerFactory.getLogger(ReadTxt.class);

    @Test
    public void read(){
        File file=new File("d:/module2.txt");
            StringBuffer sb=new StringBuffer();
            BufferedReader reader=null;
            try {
                reader=new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
                String str=null;
                while ((str=reader.readLine())!=null){
                    sb.append(str);
                }
            } catch (Exception e) {
                logger.error("读取文件失败"+file.getName());
            }
        System.out.printf(sb.toString());

    }
}
