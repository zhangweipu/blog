package com.wp.weipu.test.translate;

public class Request {

    private String q;
    private String from;
    private String to;
    //appid:20181226000252393
    //密钥：wRw9hP9OZtiXUSbf5aDb
    private String appid;
    private int salt;
    private String sign;

    public Request() {
    }

    public Request(String q, String from, String to, String appid, int salt, String sign) {
        this.q = q;
        this.from = from;
        this.to = to;
        this.appid = appid;
        this.salt = salt;
        this.sign = sign;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public int getSalt() {
        return salt;
    }

    public void setSalt(int salt) {
        this.salt = salt;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
