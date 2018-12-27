package com.wp.weipu.test.Translate;


import com.wp.weipu.test.JsonToObject;
import com.wp.weipu.test.Translate.Utils.ReadDocx;
import com.wp.weipu.test.Translate.Utils.StrSplit;
import com.wp.weipu.test.Translate.baidu.translate.demo.TransApi;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

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
        for (String s:strings){
            String result=api.getTransResult(s, "en", "zh");
            Response response= JsonToObject.mapper.readValue(result,Response.class);
            System.out.println(response);
        }

    }

}
