package com.wp.weipu.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Properties;

/**
 * 可变参数测试
 * @author zwp
 */

public class DynamicParamTest {

    public static void test1(String ...str){
        for (String s:str){
            System.out.println(s);
        }
    }

    public static void test2()throws UnknownHostException{
        //打印环境信息
        System.getProperties().list(System.out);
        Properties aa=System.getProperties();
        System.out.println(aa.getProperty("java.version"));
        //获取本机ip地址
        InetAddress address = InetAddress.getLocalHost();
        System.out.println(address.toString());
        //获取电脑mac地址
        String addre="";
        String[] command={"cmd" ,"/c" ,"ipconfig/all","cd /","dir"};
        try {
            Process p=Runtime.getRuntime().exec(command);
            BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line=reader.readLine())!=null){
                System.out.println(new String(line.getBytes("utf-8"),"utf-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void test3(){
        File file=new File("E:/迅雷下载");
        File[] files=file.listFiles();
        int i=0;
        String name="name";
        for (File file1:files){
            System.out.println(file1.getName());
            String fileName=file1.getName();
            String lastName=fileName.substring(fileName.lastIndexOf("."));
            File file2=new File("E:/迅雷下载"+"/"+name+i+lastName);
            file1.renameTo(file2);
            i++;
        }

    }

    public static void main(String[] args)  {
        test3();

    }
}
