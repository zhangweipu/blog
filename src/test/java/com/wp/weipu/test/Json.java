package com.wp.weipu.test;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wp.weipu.entity.Demo;
import com.wp.weipu.test.translate.Main;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zwp
 */

public class Json {

    @Test
    public void test() throws IOException {
        ObjectMapper mapper=new ObjectMapper();
        String str="{\"id\":\"11\",\"name\":\"tom\"}";
        Demo d=mapper.readValue(str,Demo.class);
        //String to json
        JSONObject jsonObject = JSONObject.parseObject(str);
        System.out.println(d.toString());
    }

    public String getStr(){
        return "str";
    }

    @Test
    public void jsonToList(){
        LevelText levelText=new LevelText();
        levelText.setTitle("father");
        levelText.setContent("father content");
        levelText.setOther("father other");
        List<LevelText> levelTexts=new ArrayList<>();
        for(int i=0;i<5;i++){
            LevelText levelText1=new LevelText();
            levelText1.setTitle("son "+i);
            levelText1.setContent("son content"+i);
            levelText1.setOther("son other "+i);
            levelTexts.add(levelText1);
        }
        levelText.setChildren(levelTexts);

        String jsonstr=levelText.toString();
        System.out.println(levelText.toString());

        LevelText levelT=null;
        try {
            levelT=JsonToObject.mapper.readValue(jsonstr,LevelText.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(levelT.getChildren().size());
    }
public static void main(String[] args) {
    System.out.println("weipu");
}

}
