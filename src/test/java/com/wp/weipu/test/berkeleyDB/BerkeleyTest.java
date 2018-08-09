package com.wp.weipu.test.berkeleyDB;

import com.sleepycat.je.DatabaseException;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;

/**
 * @author zwp
 */

public class BerkeleyTest {
    private BerkeleyDBUtil dbUtil = null;

    @Before
    public void setup() {
        dbUtil = new BerkeleyDBUtil("D:/files");
    }


    @Test
    public void testWriteToDatabase() {
        for (int i = 0; i < 10; i++){
            dbUtil.writeToDatabase(i+"", "学生"+i, true);
        }
    }

    @Test
    public void testReadFromDatabase() {
        String value = null;
        try {
            value = dbUtil.readFromDatabase("2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        assertEquals(value, "学生2");
    }

    @Test
    public void testGetEveryItem() {
        int size = 0;
        try {
            size = dbUtil.getAllItems().size();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        assertEquals(size, 10);
    }

    @Test
    public void testDeleteFromDatabase() {
        dbUtil.deleteFromDatabase("4");
        try {
            assertEquals(9, dbUtil.getAllItems().size());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void cleanup() {
        try {
            dbUtil.closeDB();
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }
}
