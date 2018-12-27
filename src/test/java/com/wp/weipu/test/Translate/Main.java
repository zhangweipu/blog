package com.wp.weipu.test.Translate;


import com.wp.weipu.test.JsonToObject;
import com.wp.weipu.test.Translate.Utils.ReadDocx;
import com.wp.weipu.test.Translate.Utils.StrSplit;
import com.wp.weipu.test.Translate.Utils.WriteDocx;
import com.wp.weipu.test.Translate.baidu.translate.demo.TransApi;
import com.wp.weipu.test.Translate.baidu.translate.demo.TransResult;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static final Logger logger= LoggerFactory.getLogger(Main.class);

    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "20181226000252393";
    private static final String SECURITY_KEY = "wRw9hP9OZtiXUSbf5aDb";

    public static void main(String[] args) throws IOException, InvalidFormatException {
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);

        String query = ReadDocx.doxc("D://15.docx");
        String str="good\n     d";
        System.out.println(str.length());
        //6000个字符截取一下
        List<String> strings= StrSplit.sentenceSplit(query);
        List<Response> responses=new ArrayList<>();
        for (String s:strings){
            String result=api.getTransResult(s, "en", "zh");
            Response response= JsonToObject.mapper.readValue(result,Response.class);
            responses.add(response);
            logger.info(response.toString());
        }
        Main main=new Main();
        List<String> words= main.combineWords(responses);
        WriteDocx.createDocx(words,"D://151.docx");
        logger.info("翻译完成");
    }

    public List<String> combineWords(List<Response> responses){
        List<String> words=new ArrayList<>();
        responses.forEach(l->{
            for (TransResult r:l.getTrans_result()){
                words.add(r.getSrc());
                words.add(r.getDst());
            }
        });
        logger.info("生成"+words.size()+"句！！！");
        return words;
    }

}
