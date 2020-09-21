package com.wp.weipu.test.leetcode;

import java.util.*;

public class T58 {
    /**
     * 使用栈实现吧
     *
     * @param input
     * @return
     */
    public int calculate(String input) {
        // write code here
        Stack<Integer> num = new Stack<>();
        char lastOp = '+';
        char[] ops = input.toCharArray();
        for (int i=0;i<ops.length;i++){
            if (ops[i]==' '){
                continue;
            }
            if (Character.isDigit(ops[i])){
                int tempNum=ops[i]-'0';
                while (++i<ops.length&&Character.isDigit(ops[i])){
                    tempNum=tempNum*10+(ops[i]-'0');
                }
                i--;
                if (lastOp=='+'){
                    num.push(tempNum);
                }else if(lastOp=='-'){
                    num.push(-tempNum);
                }else {
                    num.push(res(lastOp,num.pop(),tempNum));
                }
            }else {
                lastOp=ops[i];
            }

        }
        int ans=0;
        for (int n:num){
            ans+=n;
        }
        return ans;
    }

    public int res(char op, int a, int b) {
        if (op == '*') return a * b;
        else if (op == '/') return a / b;
        else if (op == '+') return a + b;
        else
            return a - b;
    }

    public ArrayList<Integer> mergerArrays (ArrayList<Integer> arrayA, ArrayList<Integer> arrayB) {
        // write code here
        Collections.sort(arrayA);
        Collections.sort(arrayB);
        Map<Integer,Integer> map=new HashMap<>();
        for (int s:arrayA){
            map.merge(s,1,Integer::sum);
        }
        ArrayList<Integer> list=new ArrayList<>();
        for (int b:arrayB){
            if (map.getOrDefault(b,0)>0){
                list.add(b);
            }
        }
        return list;
    }
}
