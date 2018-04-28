package com.wp.weipu.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author zwp
 */

public class FileUtil {
    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 读取文件内容，将其转化成String
     *
     * @param file 读取文件
     * @return sb 字符串
     */
    public static String readFile(File file) {
        //使用于单线程
        StringBuilder sb = new StringBuilder();
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
            BufferedReader br = new BufferedReader(reader);
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            reader.close();
        } catch (Exception e) {
            logger.error("error", e);
            throw new IllegalStateException("read file error", e);
        }

        return sb.toString();
    }

    /**
     * 将字符串写入目标文件
     *
     * @param file 字符串写入的目标文件
     * @Param str 希望写入的字符串
     */
    public static void writeFile(String str, File file) {
        BufferedWriter bw = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            bw = new BufferedWriter(new FileWriter(file));
            bw.write(str);
            bw.flush();
            bw.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new IllegalStateException("write error");
        }
    }

    /**
     * 读取输入输入流转化成字符串
     *
     * @param inputStream 输入流
     * @return Str 输出的字符串
     */
    public static String readFile(InputStream inputStream) {
        //适用于多线程，线程安全
        StringBuffer sb = new StringBuffer();
        if (inputStream != null) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(inputStream));
                String line = br.readLine();
                while (line != null) {
                    sb.append(line);
                    line = br.readLine();
                }
                br.close();
                inputStream.close();
            } catch (Exception e) {
                logger.error(e.getMessage());
                throw new IllegalStateException("read inputStream error");
            }
        }
        return sb.toString();
    }

    /**
     * 将输入流存到文件
     *
     * @param file        目标文件
     * @param inputStream 输入流
     */
    public static void writeFile(InputStream inputStream, File file) {

        try {
            OutputStream out = new FileOutputStream(file);
            int bytesWritten = 0;
            int byteCount = 0;
            byte[] bytes = new byte[1024];
            while ((byteCount = inputStream.read(bytes)) != -1) {
                out.write(bytes, bytesWritten, byteCount);
                bytesWritten += byteCount;
            }
            //out.flush();
            out.close();
            inputStream.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new IllegalStateException("write file error");
        }
    }
}
