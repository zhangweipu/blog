package com.wp.weipu.test.callpython;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 使用Java调用python代码
 * 使用Runtime.getRuntime()执行脚本
 */
public class CallPythonTest1 {
    public static final Logger logger = LoggerFactory.getLogger(CallPythonTest1.class);

    @Test
    public void test1() {
        logger.info("start call python");
        String pythonPath = "d:\\work\\callme.py";
        String paramter = "i am java";
        //调用python的执行命令，对应的是命令，python文件的路径，传递的参数(不论传递什么类型都是str)
        String[] in = new String[]{"python", pythonPath, paramter};
        Process pr = null;
        BufferedReader br = null;
        try {
            //调用cmd命令
            pr = Runtime.getRuntime().exec(in);
            //通过缓冲流接收返回结果
            br = new BufferedReader(new InputStreamReader(pr.getInputStream(), "utf-8"));
            String line = "";
            String result = "";
            //返回的是python中print语句，这是输出流
            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println(result);
            //关闭流
            br.close();
            pr.waitFor();
        } catch (IOException | InterruptedException e) {
            logger.error("调用期间出现异常", e);
        }

    }
}
