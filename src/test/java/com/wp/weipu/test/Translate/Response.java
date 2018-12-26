package com.wp.weipu.test.Translate;

import com.wp.weipu.test.Translate.baidu.translate.demo.TransResult;

import java.util.List;

public class Response {
    private String from;
    private String to;
    private List<TransResult> trans_result;

    @Override
    public String toString() {
        return "Response{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", trans_result=" + trans_result +
                '}';
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

    public List<TransResult> getTrans_result() {
        return trans_result;
    }

    public void setTrans_result(List<TransResult> trans_result) {
        this.trans_result = trans_result;
    }
}
