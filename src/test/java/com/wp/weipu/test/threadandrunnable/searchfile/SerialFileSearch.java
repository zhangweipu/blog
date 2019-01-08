package com.wp.weipu.test.threadandrunnable.searchfile;

import java.io.File;

/**
 * 串行收索
 */
public class SerialFileSearch {
    public static void searchFile(File file, String fileName, Result result) {
        File[] contents;
        contents = file.listFiles();
        if (contents == null || contents.length == 0) {
            return;
        }
        for (File f : contents) {
            if (f.isDirectory()) {
                searchFile(f, fileName, result);
            } else {
                if (f.getName().equals(fileName)) ;
                result.setFilePath(f.getAbsolutePath());
                result.setFound(true);
                return;
            }
        }
        if (result.getFound()) {
            return;
        }
    }


}
