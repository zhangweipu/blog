package com.wp.weipu.test.Translate.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class StrSplit {

    public static final Logger logger= LoggerFactory.getLogger(StrSplit.class);

    public static final int LIMIT=6000;
    /**
     * 每六千个字符截断，判断当前字符是否为标点，否则游标加一
     * 分页算法totalPage = (totalCount+ loadCount-1) / loadCount;
     * @param str
     * @return
     */
    public static List<String> sentenceSplit(String str){
        List<String> strList=new ArrayList<>();
        int totalCount=str.length();
        logger.info("字符串总长度："+totalCount);
        int loadCount=LIMIT;
        int totalPage=(totalCount+ loadCount-1) / loadCount;
        int index=0;
        int i=0;
        while (index<totalCount){
            int endIndex=index+loadCount;
            if (endIndex>totalCount){
                endIndex=totalCount-1;
            }
            //保证截取的是一个完整的句子
            boolean flag=true;
            while (flag && endIndex<totalCount) {
                char currentChar =str.charAt(endIndex);
                if (currentChar==' '|| isPunctuation(currentChar)){
                    flag=false;
                }
                endIndex++;
            }
            String fragment=str.substring(index,endIndex);
            strList.add(fragment);
            index=endIndex;

            logger.info("装载当前页数"+(i++));
            logger.info("下次装载index："+index);
        }
        return strList;
    }

    public static void main(String[] args) {
        String ss="good. weather , go to .";
        System.out.println(ss.charAt(4));
        System.out.println(ss.substring(0,2));
        StrSplit strSplit=new StrSplit();
        char a=ss.charAt(4);
        if (strSplit.isPunctuation(a)){
            System.out.println("is");
        }

    }
    // 根据UnicodeBlock方法判断中文标点符号
    public boolean isChinesePunctuation(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS
                || ub == Character.UnicodeBlock.VERTICAL_FORMS) {
            return true;
        } else {
            return false;
        }
    }

    //使用UnicodeScript方法判断
    public boolean isChineseByScript(char c) {
        Character.UnicodeScript sc = Character.UnicodeScript.of(c);
        if (sc == Character.UnicodeScript.HAN) {
            return true;
        }
        return false;
    }
    public static boolean isPunctuation(char ch){
        if(isCjkPunc(ch)) return true;
        if(isEnPunc(ch)) return true;

        if(0x2018 <= ch && ch <= 0x201F) return true;
        if(ch == 0xFF01 || ch == 0xFF02) return true;
        if(ch == 0xFF07 || ch == 0xFF0C) return true;
        if(ch == 0xFF1A || ch == 0xFF1B) return true;
        if(ch == 0xFF1F || ch == 0xFF61) return true;
        if(ch == 0xFF0E) return true;
        if(ch == 0xFF65) return true;

        return false;
    }

    public static boolean isEnPunc(char ch){
        if (0x21 <= ch && ch <= 0x22) return true;
        if (ch == 0x27 || ch == 0x2C) return true;
        if (ch == 0x2E || ch == 0x3A) return true;
        if (ch == 0x3B || ch == 0x3F) return true;

        return false;
    }
    public static boolean isCjkPunc(char ch) {
        if (0x3001 <= ch && ch <= 0x3003) return true;
        if (0x301D <= ch && ch <= 0x301F) return true;

        return false;

    }

}
