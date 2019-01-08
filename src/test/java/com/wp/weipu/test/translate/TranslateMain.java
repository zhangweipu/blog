package com.wp.weipu.test.translate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wp.weipu.test.JsonToObject;
import com.wp.weipu.test.webservice.HttpService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TranslateMain {

    public static final String TRANSLATEURL="http://api.fanyi.baidu.com/api/trans/vip/translate";

    public static final Logger logger= LoggerFactory.getLogger(TranslateMain.class);

    HttpService httpService=new HttpService();

    TranslateMainPre translateMainPre=new TranslateMainPre();
    @Test
    public void Post(){
        String str="english";
        Request request=translateMainPre.getRequest(str);
        String jsonStr="";
        try {
            jsonStr= JsonToObject.mapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String responseStr="";
        try {
            responseStr=httpService.httpPost(TRANSLATEURL,jsonStr,httpService.initHttpHead());
        } catch (Exception e) {
            logger.error("translate error");
            e.printStackTrace();
        }
        System.out.println(responseStr);
    }

}
