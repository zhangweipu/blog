package com.wp.weipu.test.Translate.Utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadDocx {

    public static final Logger logger= LoggerFactory.getLogger(ReadDocx.class);

    public static String doxc(String importPath) throws InvalidFormatException, IOException {
        FileInputStream inputStream=null;
        try {
            inputStream = new FileInputStream(importPath);
            XWPFDocument xDocument = new XWPFDocument(inputStream);
            List<XWPFParagraph> paragraphs = xDocument.getParagraphs();
            Map<String, String> map = new HashMap<>();
            String text = "";
            for(XWPFParagraph paragraph : paragraphs){
                List<XWPFRun> runs = paragraph.getRuns();
                for(XWPFRun run1 : runs){
                    if(run1.getCTR().xmlText().indexOf("<w:drawing>")!=-1){
                        String runXmlText = run1.getCTR().xmlText();
                        int rIdIndex1 = runXmlText.indexOf("r:embed");
                        int rIdEndIndex = runXmlText.indexOf("/>", rIdIndex1);
                        String rIdText = runXmlText.substring(rIdIndex1, rIdEndIndex);
                        logger.info(rIdText.split("\"")[1].substring("rId".length()));


                    }else{
                        if (!(run1.toString().trim().equals(""))){
                            text = text + run1;
                        }

                    }

                }
                text=text+"\n";
            }
            return text.trim();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            inputStream.close();
        }
        return null;
    }
}
