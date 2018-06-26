package com.wp.weipu.test.webservice;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * HTTP接口调用服务
 *
 * @author long.jin
 * @version 1.0
 */
@Service
public class HttpService {

    private static final String APPLICATION_JSON = "application/json";
    private static final String CHARSET = "UTF-8";

    /**
     * 请求连接超时
     */
    private static final int CONNECTTIMEOUT = 10000;

    /**
     * 连接池获取可用连接超时
     */
    private static final int CONNECTIONREQUESTTIMEOUT = 10000;

    /**
     * 等待响应超时
     */
    private static final int SOCKETTIMEOUT = 10000;

    /**
     * http请求（get方式，传递拼接参数）
     *
     * @param getUrl 请求的地址（不校验）
     * @return java.lang.String
     * @throws IOException 任意情况发生时，方法会抛出IO异常：HttpGet调用失败；返参内容不存在;请求连接关闭异常。
     */
    public String httpGet(String getUrl) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(getUrl);

        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(CONNECTTIMEOUT)
                .setConnectionRequestTimeout(CONNECTIONREQUESTTIMEOUT)
                .setSocketTimeout(SOCKETTIMEOUT)
                .build();

        httpGet.setConfig(requestConfig);
        String msg;
        HttpResponse res = httpClient.execute(httpGet);
        int statusCode = res.getStatusLine().getStatusCode();
        if (statusCode == HttpStatus.SC_OK) {
            HttpEntity entity = res.getEntity();
            msg = new BufferedReader(new InputStreamReader(entity.getContent(), StandardCharsets.UTF_8)).lines().collect(Collectors.joining("\n"));
        } else {
            msg = new StringBuffer()
                    .append("HttpStatus：").append(statusCode).append("-").append(res.getStatusLine().getReasonPhrase()).toString();
        }

        // 关闭连接 ,释放资源
        httpClient.close();

        return msg;
    }

    /**
     * http请求（post方式，传递json参数）
     *
     * @param postUrl         请求的地址（不做格式校验）
     * @param jsonStr         请求的json串（不做格式校验）
     * @param basicHeaderList 请求头信息
     * @return java.lang.String
     * @throws IOException    抛出IO异常
     *
     */
    public String httpPost(String postUrl, String jsonStr, List<BasicHeader> basicHeaderList) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(postUrl);
        String msg;
        StringEntity se = new StringEntity(jsonStr.toString(), CHARSET);
        se.setContentType(APPLICATION_JSON);
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
        if (basicHeaderList != null && basicHeaderList.size() > 0) {
            for (BasicHeader bh : basicHeaderList) {
                httpPost.setHeader(bh);
            }
        }
        httpPost.setEntity(se);

        // 发送请求
        HttpResponse res = httpClient.execute(httpPost);

        int statusCode = res.getStatusLine().getStatusCode();
        if (statusCode == HttpStatus.SC_OK) {

            // 得到应答的字符串
            msg = EntityUtils.toString(res.getEntity());

            httpClient.close();
            return msg;
        } else {
            httpClient.close();
            throw new Exception(new StringBuffer()
                    .append("HttpStatus：").append(statusCode).append("-").append(res.getStatusLine().getReasonPhrase()).toString());
        }
    }

    /**
     * http请求（post方式，传递拼接参数）
     *
     * @param postUrl  请求地址
     * @param paramMap 参数键值对
     * @return java.lang.String
     * @throws IOException 抛出IO异常
     */
    public String httpURLConnectionPOST(String postUrl, Map<String, String> paramMap) throws IOException {
        URL url = new URL(postUrl);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // 设置连接输出流为true
        connection.setDoOutput(true);
        connection.setDoInput(true);

        // 设置请求方式为post
        connection.setRequestMethod("POST");

        // post请求缓存设为false
        connection.setUseCaches(false);

        // 设置该HttpURLConnection实例是否自动执行重定向
        connection.setInstanceFollowRedirects(true);

        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // 建立连接
        connection.connect();

        // 参数拆分
        String param = "";
        if (paramMap != null && paramMap.size() > 0) {
            Iterator<String> ite = paramMap.keySet().iterator();
            while (ite.hasNext()) {
                String key = ite.next();
                String value = paramMap.get(key);
                param += key + "=" + value + "&";
            }
            param = param.substring(0, param.length() - 1);
        }

        // 创建输入输出流,用于往连接里面输出携带的参数\
        DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());

        // 将参数输出到连接
        dataout.writeBytes(param);

        // 刷新流
        dataout.flush();

        // 关闭流
        dataout.close();

        // 连接发起请求,处理服务器响应(从连接获取到输入流并包装为bufferedReader)
        BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String line;

        // 用来存储响应数据
        StringBuffer sb = new StringBuffer();

        // 循环读取流
        while ((line = bf.readLine()) != null) {
            sb.append(line).append(System.getProperty("line.separator"));
        }

        // 关闭流
        bf.close();

        // 销毁连接
        connection.disconnect();

        return sb.toString();
    }

    /**
     * 初始化HTTP消息头
     *
     * @return java.util.List
     */
    public List<BasicHeader> initHttpHead() {
        List<BasicHeader> basicHeaderList = new ArrayList<BasicHeader>();
        basicHeaderList.add(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
        basicHeaderList.add(new BasicHeader(HTTP.CONTENT_ENCODING, CHARSET));
        basicHeaderList.add(new BasicHeader(HTTP.DATE_HEADER, new Date().toString()));
        return basicHeaderList;
    }

}