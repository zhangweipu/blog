package com.wp.weipu.common.utils;

import com.jcraft.jsch.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;


@Component("SFTPUtil")
@ConfigurationProperties
public class SFTPUtil {
	private final static Log logger = LogFactory.getLog(SFTPUtil.class);
      
    private ChannelSftp sftp;  
        
    private Session session;  
    /** SFTP 登录用户名 */
    @Value("${sftp.username}")
    private String username;  
    /** SFTP 登录密码 */
    @Value("${sftp.password}")
    private String password;  
    /** 私钥 */    
    private String privateKey;  
    /** SFTP 服务器地址IP地址 */
    @Value("${sftp.host}")
    private String host;
    /** SFTP 端口 */
    @Value("${sftp.port}")
    private int port;
    /** SFTP  下载上传文件的根目录 */
    @Value("${sftp.home}")
    private String home;

    /**
     * 构造基于密码认证的sftp对象,指定要上传下载的根目录
     * @param username
     * @param password
     * @param host
     * @param port
     * @param home
     */
    public SFTPUtil(String username, String password, String host, int port, String home) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
        this.home = home;
    }

    /**  
     * 构造基于密码认证的sftp对象  
     * @param username
     * @param password  
     * @param host  
     * @param port  
     */    
    public SFTPUtil(String username, String password, String host, int port) {
        this.username = username;  
        this.password = password;  
        this.host = host;  
        this.port = port;  
    }  
    
    /**  
     * 构造基于秘钥认证的sftp对象 
     * @param username
     * @param host 
     * @param port 
     * @param privateKey 
     */  
    public SFTPUtil(String username, String host, int port, String privateKey) {  
        this.username = username;  
        this.host = host;  
        this.port = port;  
        this.privateKey = privateKey;  
    }  
    
    public SFTPUtil(){}  
    
    /** 
     * 连接sftp服务器 
     * 
     * @throws Exception  
     */  
    public void login(){  
        try {  
            JSch jsch = new JSch();  
            if (privateKey != null) {  
                jsch.addIdentity(privateKey);// 设置私钥  
                logger.info("sftp私钥连接，私钥：{"+privateKey+"}" );  
            }  
            logger.info("sftp连接地址:{"+host+"} 用户名:{"+username+"}");
            session = jsch.getSession(username, host, port);  
            if (password != null) {  
                session.setPassword(password);    
            }  
            Properties config = new Properties();  
            config.put("StrictHostKeyChecking", "no");  
                
            session.setConfig(config);  
            session.connect();  
            logger.info("Session连接");  
              
            Channel channel = session.openChannel("sftp");  
            channel.connect();  
            logger.info("channel连接");  
    
            sftp = (ChannelSftp) channel;  
            logger.info(String.format("sftp连接服务器地址:[%s] 端口号:[%s] 成功", host, port));  
        } catch (JSchException e) {  
            logger.error("sftp连接服务器失败 : {"+host+"}:{"+port+"} \n 异常为: {"+e.getMessage()+"}");    
        }  
    }    
    
    /** 
     * 关闭连接 server  
     */  
    public void logout(){
        if (sftp != null) {
            if (sftp.isConnected()) {
                sftp.disconnect();
                logger.info("sftp关闭");
            }
        }
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
                logger.info("sshSession关闭");
            }
        }
        if (sftp != null) {
            sftp.exit();
        }
    }

    /**
     * 组装文件名
     * @param fileName 原始文件名
     * @return
     */
    public String createFileName(String fileName){
        //uuid+&*!+文件名
//        String name = UUID.randomUUID().toString().replaceAll("-", "")+"&*!"+fileName;
        //当前时间为文件名
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间
        String suff = fileName.substring(fileName.indexOf("."));
        String name = date+suff;
        logger.info("组装文件名:"+name);
        return name;
    }

    /**
     * 创建文件目录
     * @param fileName 原始文件名
     * @return
     */
    public String createDirectory(String fileName){

        int hashCode = fileName.hashCode();
        // 一级目录
        int d1 = hashCode & 0xf;
        // 二级目录
        int d2 = (hashCode >> 4) & 0xf;
        return d1+"/"+d2+"/";
    }

    /**
     * 将输入流的数据上传到sftp作为文件
     *
     * @param directory
     *            上传到该目录
     * @param sftpFileName
     *            sftp端文件名
     * @param input
     *            输入流
     * @throws SftpException
     * @throws Exception
     */
    public void upload(String directory, String sftpFileName, InputStream input) throws SftpException{
        this.getHome(this.home);
        if(directory.indexOf("\\")>-1){
            directory = directory.replace("\\", "/");
        }
        //sftp创建文件目录，要一个一个的创建
        try {
            //有此目录则进入
            sftp.cd(directory);
        } catch (SftpException e) {
            //无此目录则创建(只能一个一个创建)
            String [] dirs;
            if(directory.indexOf("/")>-1){
                dirs = directory.split("/");
                for(String dir : dirs){
                    if(dir.length()>0){
                        try {
                            sftp.cd(dir);
                        } catch (SftpException e1) {
                            sftp.mkdir(dir);
                            sftp.cd(dir);
                        }
                    }
                }
            }
        }
        sftp.put(input, sftpFileName);
        try {
            input.close();
        } catch (IOException e) {
            if(input != null){
                input = null;
            }
            e.printStackTrace();
        }
    }


    /**  
     * 上传单个文件 
     * 
     * @param directory  
     *            上传到sftp目录  
     * @param uploadFile 
     *            要上传的文件,包括路径  
     * @throws FileNotFoundException 
     * @throws SftpException 
     * @throws Exception 
     */  
    public void upload(String directory, String uploadFile) throws FileNotFoundException, SftpException{  
        File file = new File(uploadFile);  
        upload(directory, file.getName(), new FileInputStream(file));  
    }  
    

    /** 
     * 列出目录下的文件 
     *  
     * @param directory 
     *            要列出的目录 
     * @param directory
     * @return 
     * @throws SftpException 
     */  
    public Vector<?> listFiles(String directory) throws SftpException {  
        return sftp.ls(directory);  
    }
    /**
     * 下载文件
     *
     * @param directory
     *            下载目录
     * @param downloadFile
     *            下载的文件
     * @param saveFile
     *            存在本地的路径
     * @throws SftpException
     * @throws FileNotFoundException
     * @throws Exception
     */
    public void download(String directory, String downloadFile, String saveFile) throws SftpException, FileNotFoundException{
        this.getHome(this.home);
        if (directory.indexOf("\\") > -1) {
            directory = directory.replace("\\", "/");
        }
        try {
            sftp.cd(directory);
            logger.info("进入路径:\t" + directory);
        } catch (SftpException e) {
            e.printStackTrace();
            logger.info("无此路径:\t" + directory);
            throw new SftpException(2,"服务器上无此文件，请检查服务器上路径 "+directory+" 是否存在！！！");
        }
        File file = new File(saveFile);
        if (!file.getParentFile().isDirectory()) {
            file.getParentFile().mkdirs();
        }
        OutputStream outputStream= new FileOutputStream(file);
        sftp.get(downloadFile, outputStream);
        logger.info("下载成功:\n" + saveFile);
        try {
            outputStream.close();
        } catch (IOException e) {
            if(outputStream!=null){
                outputStream = null;
            }
            e.printStackTrace();
        }
    }


    /**
     * 删除文件
     *
     * @param directory
     *            要删除文件所在目录
     * @param deleteFile
     *            要删除的文件
     * @throws SftpException
     * @throws Exception
     */
    public void delete(String directory, String deleteFile) throws SftpException{
        this.getHome(this.home);
        if (directory.indexOf("\\") > -1) {
            directory = directory.replace("\\", "/");
        }
        try {
            sftp.cd(directory);
            logger.info("进入路径:\t" + directory);
        } catch (SftpException e) {
            e.printStackTrace();
            logger.info("无此路径:\t" + directory);
            throw e;
        }
        sftp.rm(deleteFile);
    }
    /**
     * 进入文件上传下载根目录地址
     * @param home
     * @return
     * @throws SftpException
     */
    public void getHome(String home) throws SftpException{
        if(null!=home&&!"".equals(home)){
            logger.info("进入根目录" + home);
            try {
                sftp.cd(home);
            } catch (SftpException e1) {
                sftp.cd("/");
            }
        }else{
            sftp.cd("/");
        }
    }
    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }


}