package com.wp.weipu.test.ways;

public class WayNode {
    private int x;
    private int y;
    private int key;
    /**
     * 上下左右四个方向分别是 1，2，3，4
     */
    private int direction;

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public WayNode() {
    }

    public WayNode(int x, int y, int key,int direction) {
        this.x = x;
        this.y = y;
        this.key = key;
        this.direction=direction;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
