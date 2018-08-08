package com.wp.weipu.test;


import org.apache.commons.httpclient.HttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 爬虫测试
 *
 * @author zwp
 */

public class HttpService {

    private static final Logger logger= LoggerFactory.getLogger(HttpService.class);

    private CloseableHttpClient httpClient;

    public void createHttpClient(){
        logger.info("打开HttpClient");
        httpClient=HttpClients.createDefault();
    }

    public void closeHttpClient(){
        try {
            httpClient.close();
        } catch (IOException e) {
            logger.error("关闭httpClient失败！");
        }
    }

    public void httpPost(){
        
    }

    public void httpGet(String url) {
        //创建httpClient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建httpGet实例
        HttpGet httpGet=new HttpGet(url);
        //设置User—Agent模拟浏览器
        httpGet.setHeader("User-Agent","Mozilla/5.0(Windows NT 10.0;WOW64) AppleWebKit/537.36(KHTML,like Gecko) Chrome/62.0.3202.94 Safari/537.36");
        //获取返回信息
        CloseableHttpResponse response=null;
        try {
            response=httpClient.execute(httpGet);
            if (response!=null){
                HttpEntity entity=response.getEntity();
                String result= EntityUtils.toString(entity);
                System.out.println("网页内容"+result);
            }
        } catch (IOException e) {
            logger.info("获取网页信息出错",e);
        }finally {
            if (response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    logger.info("关闭返回流出错",e);
                }
            }
            if (httpClient !=null){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    logger.info("关闭HttpClient失败",e);
                }
            }
        }

    }


}
