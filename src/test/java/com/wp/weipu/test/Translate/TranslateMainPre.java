package com.wp.weipu.test.Translate;

import com.wp.weipu.test.Translate.Utils.MD5;
import com.wp.weipu.test.Translate.Utils.UrlEncode;
import com.wp.weipu.test.webservice.HttpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TranslateMainPre {

    public static final Logger logger= LoggerFactory.getLogger(TranslateMainPre.class);

    public static final String APPID="20181226000252393L";
    public static final String SK="wRw9hP9OZtiXUSbf5aDb";
    public static final int SALT=8;
    public static final String FROM="en";
    public static final String TO="zh";

    @Autowired
    private HttpService httpService;

    public Request getRequest(String q){
        String query= UrlEncode.urlEncode(q);
        logger.info("make request");
        return new Request(query,FROM,TO,APPID,SALT,getSign(q));
    }

    private String getSign(String q){
        logger.info("make sign");
        return MD5.crypt(APPID+q+SALT+SK);
    }
}
