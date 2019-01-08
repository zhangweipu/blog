package com.wp.weipu.test.translate.Utils;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class WriteDocx {

    public static final Logger logger= LoggerFactory.getLogger(WriteDocx.class);

    public static XWPFDocument toWrite(String words) throws IOException {
        XWPFDocument document= new XWPFDocument();
        XWPFParagraph firstParagraph = document.createParagraph();
        firstParagraph.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun run = firstParagraph.createRun();
        run.setText(words);
        run.addBreak();
        // run.setColor("696969");
        run.setFontSize(10);
        logger.info("写入文件");
        return document;
    }

    public static void createDocx(List<String> characters,String filePath) throws IOException {
        FileOutputStream out = new FileOutputStream(new File(filePath));
        XWPFDocument document= new XWPFDocument();
        XWPFParagraph firstParagraph = document.createParagraph();
        firstParagraph.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun run = firstParagraph.createRun();
        for (String s:characters){
            run.setText(s);
            run.addBreak();
            run.setFontSize(10);
        }
        document.write(out);
        out.close();
        logger.info("生成word文档："+filePath);
        // run.setColor("696969");


    }

}
