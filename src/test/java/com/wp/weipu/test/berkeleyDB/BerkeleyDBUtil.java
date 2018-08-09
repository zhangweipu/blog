package com.wp.weipu.test.berkeleyDB;

import com.sleepycat.je.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * berkeley数据库
 *
 * @author zwp
 */

public class BerkeleyDBUtil {
    private static final Logger logger = LoggerFactory.getLogger(BerkeleyDBUtil.class);

    //数据库环境
    private Environment env = null;

    //数据库
    private static Database frontierDatabase = null;

    //数据库名称
    private static String dbName = "frontier_database";

    public BerkeleyDBUtil(String homeDirectory) {

        //创建EnvironmentConfig
        EnvironmentConfig envConfig = new EnvironmentConfig();
        envConfig.setTransactional(true);
        envConfig.setAllowCreate(true);
        //创建dbConfig
        DatabaseConfig dbConfig = new DatabaseConfig();
        dbConfig.setTransactional(true);
        dbConfig.setAllowCreate(true);
        try {
            //使用EnvironmentConfig配置Environment
            env = new Environment(new File(homeDirectory), envConfig);
            //打开数据库
            frontierDatabase = env.openDatabase(null, dbName, dbConfig);
        } catch (DatabaseException e) {
            logger.error("配置数据库出错", e);
        }
    }

    /**
     * 向数据库中写入记录，并判断是否可以有重复数据，传入key和value
     * 若可重复用put，不可重复用putNoOverwrite
     *
     * @param key
     * @param value
     * @param isOverWrite 是否可以往数据库中插入重复数据
     * @return
     */
    public boolean writeToDatabase(String key, String value, boolean isOverWrite) {

        //设置key/value,database内部使用的是byte数据
        DatabaseEntry theKey = new DatabaseEntry(key.getBytes());
        DatabaseEntry theValue = new DatabaseEntry(value.getBytes());
        OperationStatus status = null;
        Transaction tx = null;

        //transaction的设置
        TransactionConfig txConfig = new TransactionConfig();
        txConfig.setSerializableIsolation(true);
        try {
            tx = env.beginTransaction(null, txConfig);
            if (isOverWrite) {
                status = frontierDatabase.put(tx, theKey, theValue);
            } else {
                status = frontierDatabase.putNoOverwrite(tx, theKey, theValue);
            }
            tx.commit();
        } catch (DatabaseException e) {
            logger.error("插入或执行事务出错", e);
            return false;
        }
        if (null == status) {
            logger.error("没有执行插入操作");
            return false;
        } else if (status == OperationStatus.SUCCESS) {
            logger.info("向数据库写入数据成功");
            return true;
        } else if (status == OperationStatus.KEYEXIST) {
            logger.info("key存在，插入失败");
            return false;
        } else {
            logger.error("插入失败，未知原因");
            return false;
        }
    }

    /**
     * 从数据库通过key读取值
     *
     * @return
     */
    public String readFromDatabase(String key) throws UnsupportedEncodingException {
        DatabaseEntry theKey = new DatabaseEntry(key.getBytes());
        DatabaseEntry theValue = new DatabaseEntry();
        Transaction tx = null;

        TransactionConfig txConfig = new TransactionConfig();
        txConfig.setSerializableIsolation(true);
        try {
            tx = env.beginTransaction(null, txConfig);
            //读取数据
            OperationStatus status = frontierDatabase.get(tx, theKey, theValue, LockMode.DEFAULT);
            tx.commit();
            if (status == OperationStatus.SUCCESS) {
                logger.info("取数据成功");
                byte[] retData = theValue.getData();
                return new String(retData, "UTF-8");
            } else {
                logger.error("取数据失败");
                return null;
            }
        } catch (DatabaseException e) {
            logger.error("根据key值取数据时出现错误", e);
        }

        return null;
    }

    /**
     * 查询所有数据
     * @return
     * @throws UnsupportedEncodingException
     */
    public List<String> getAllItems() throws UnsupportedEncodingException {
        Cursor myCursor = null;
        List<String> resultList = new ArrayList<>();
        Transaction tx = null;

        try {
            tx = env.beginTransaction(null, null);
            CursorConfig curConfig = new CursorConfig();
            if (myCursor == null) {
                myCursor = frontierDatabase.openCursor(tx, curConfig);
            }
            DatabaseEntry foundKey = new DatabaseEntry();
            DatabaseEntry foundValue = new DatabaseEntry();
            //使用cursor
            if (myCursor.getFirst(foundKey, foundValue, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
                String theKey = new String(foundKey.getData(), "UTF-8");
                String theValue = new String(foundValue.getData(), "UTF-8");
                resultList.add(theKey);
                while (myCursor.getNext(foundKey, foundValue, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
                    theKey = new String(foundKey.getData(), "UTF-8");
                    theValue = new String(foundValue.getData(), "UTF-8");
                    resultList.add(theKey);
                }
            }
            myCursor.close();
            tx.commit();
            return resultList;
        } catch (DatabaseException e) {
            logger.error("取数据出错", e);
            try {
                tx.abort();
                myCursor.close();
            } catch (DatabaseException e1) {
                logger.error("出现异常回滚");
            }
        }
        return null;
    }

    /**
     * 根据key删除数据库中的一条记录
     * @param key
     * @return
     */
    public boolean deleteFromDatabase(String key){
        boolean success=false;
        long sleepMillis=0;
        for (int i=0;i<3;i++){
            if (sleepMillis != 0){
                try {
                    Thread.sleep(sleepMillis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sleepMillis=0;
            }
        }
        Transaction tx=null;

        TransactionConfig txConfig=new TransactionConfig();
        txConfig.setSerializableIsolation(true);
        try {
            tx=env.beginTransaction(null,txConfig);
            DatabaseEntry theKey=new DatabaseEntry(key.getBytes());
            //删除数据
            OperationStatus status=frontierDatabase.delete(tx,theKey);
            tx.commit();
            if (status==OperationStatus.SUCCESS){
                logger.info("删除成功");
                return true;
            }else {
                logger.error("删除失败");
                return false;
            }
        } catch (DatabaseException e) {
            logger.error("删除出错",e);
        }

        return false;
    }


    public void closeDB() throws DatabaseException {
        if (frontierDatabase != null){
            try {
                frontierDatabase.close();
            } catch (DatabaseException e) {
                logger.error("关闭数据库链接",e);
            }
        }
        if (env != null){
            env.close();
        }
    }

}
