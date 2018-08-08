package com.wp.weipu.test.spider;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author zwp
 */

public class DownLoadFile {

    private static final Logger logger= LoggerFactory.getLogger(DownLoadFile.class);

    /**
     * 根据url和网页类型生成需要保存的网页的文件名，去除url中的非文件名称
     * @param url
     * @param contentType
     * @return
     */
    public String getFileNameByUrl(String url,String contentType){
        //移除http://
        url=url.substring(7);
        //text/html类型
        if(contentType.indexOf("html")!= -1){
            url=url.replaceAll("[\\?/:*|<>\"]","_")+".html";
            return url;
        }else {
            //如果是application/pdf类型
            return url.replaceAll("[\\?/:*|<>\"]","_")+"."+
                    contentType.substring(contentType.lastIndexOf("/")+1);
        }
    }

    /**
     * 保存网页字节数组到本地文件，filepath为要保存的文件的相对地址
     * @param data
     * @param filePath
     */
    private void saveToLocal(byte[] data,String filePath){
        logger.info("保存文件到"+filePath);
        try {
            DataOutputStream out=new DataOutputStream(new FileOutputStream(new File(filePath)));
            for (int i=0;i<data.length;i++){
                out.write(data[i]);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            logger.error("操作文件流失败",e);
        }

    }

    /**
     * 下载url指向的网页
     * @param url
     * @return
     */
    public String downloadFile(String url){
        logger.info("下载文件的地址"+url);
        String filePath=null;
        //1.生成HttpClient对象并设置参数
        HttpClient httpClient=new HttpClient();
        //设置Http连接超时
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(50000);
        //2.生成GetMethod对象并设置参数
        GetMethod getMethod=new GetMethod(url);
        //设置get请求超时 5s
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,5000);
        //设置请求重试处理
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
        //3.执行httpget请求
        try {
            int statusCode=httpClient.executeMethod(getMethod);
            logger.info(url+"返回的状态码"+statusCode);
            //判断返回状态码
            if (statusCode != HttpStatus.SC_OK){
                logger.error("method failed"+getMethod.getStatusLine());
                filePath=null;
            }
            //4.处理返回内容
            //读取字节数组
            byte[] responseBody=getMethod.getResponseBody();
            //根据url生成保存文件名
             filePath=getFileNameByUrl(url,getMethod.getResponseHeader("Content-Type").getValue());
             saveToLocal(responseBody,filePath);
        } catch (IOException e) {
            logger.error("执行httpget请求出错",e);
        }finally {
            getMethod.releaseConnection();
        }
        return filePath;
    }
}
