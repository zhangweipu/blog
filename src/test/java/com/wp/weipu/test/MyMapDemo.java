package com.wp.weipu.test;

/**
 * 尝试使用重写map的toString方法转化对象
 * @author zwp
 */

public class MyMapDemo {

    private String name;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"id\":\"")
                .append(id).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
