package com.wp.weipu.test.ways;

public enum Direction {
    UP(1, "上面"),
    DOWN(2, "下面"),
    LEFT(3, "左面"),
    RIGHT(4, "右面");

    Integer value;

    String message;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    Direction(Integer value, String message) {
        this.message = message;
        this.value = value;
    }
}
