package com.wp.weipu.test.ways;

import java.util.Stack;

public class WaysMain {

    public Stack<WayNode> start(int[][] ways, int X, int Y, int n, Stack<WayNode> stack) {
        if (null == stack) {
            stack = new Stack<>();
        }
        if (n == stack.size()) {
            return stack;
        }
        WayNode top = new WayNode(X, Y, ways[X][Y], 0);
        //起始点进栈
        if (top.getKey() != 0 && isExist(stack, top.getX(), top.getY())) {
            stack.push(top);
            start(ways, top.getX() - 1, top.getY(), n, stack);
        } else {
            top = stack.peek();
            top.setDirection(Direction.UP.getValue());
            //重新分配坐标
            X = top.getX();
            Y = top.getY();
            //向上不能走，换个方向
            if (ways[X][Y + 1] != 0 && isExist(stack, X, Y + 1)) {
                WayNode top2 = new WayNode(X, Y + 1, ways[X][Y + 1], 0);
                top.setDirection(Direction.RIGHT.getValue());
                stack.push(top2);
                start(ways, top2.getX() + 1, top2.getY(), n, stack);
            } else if (ways[X + 1][Y] != 0 && isExist(stack, X + 1, Y)) {
                WayNode top2 = new WayNode(X + 1, Y, ways[X + 1][Y], 0);
                top.setDirection(Direction.DOWN.getValue());
                stack.push(top2);
                start(ways, top2.getX() + 1, top2.getY(), n, stack);
            } else if (ways[X][Y - 1] != 0 && isExist(stack, X, Y - 1)) {
                WayNode top2 = new WayNode(X, Y - 1, ways[X][Y - 1], 0);
                top.setDirection(Direction.LEFT.getValue());
                stack.push(top2);
                start(ways, top2.getX() + 1, top2.getY(), n, stack);
            } else {
                stack.pop();
                WayNode top3 = stack.peek();
                switch (top3.getDirection()) {
                    case 1:
                        start(ways, top3.getX(), top3.getY() + 1, n, stack);
                        break;
                    case 2:
                        start(ways, top3.getX() + 1, top3.getY(), n, stack);
                        break;
                    case 3:
                        start(ways, top3.getX(), top3.getY() - 1, n, stack);
                        break;
                }
            }
        }


        return null;
    }

    /**
     * 判断栈中是否存在该点
     *
     * @param wayNodes
     * @param X
     * @param Y
     * @return
     */
    public Boolean isExist(Stack<WayNode> wayNodes, int X, int Y) {
        for (WayNode w : wayNodes) {
            if (X == w.getX() && Y == w.getY()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
