package com.wp.weipu.test.finitestatemachine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.BitSet;
import java.util.StringTokenizer;

/**
 * 确定有限状态机
 */
public class DFA {

    /**
     * 判断x是否为终止状态
     * @param x
     * @param acceptStates
     * @return
     */
    static boolean isFinal(int x, BitSet acceptStates){
        return acceptStates.get(x);
    }

    //看状态机是否能接收word
    static boolean recongizeString(int[][] next,BitSet acceptStates,String word){
        //初始状态
        int currentState=0;
        for (int i=0;i<word.length();i++){
            //?? 为什么-a
            currentState=next[currentState][word.charAt(i)-'a'];
        }
        //判断是不是最后
        if (isFinal(currentState,acceptStates)){
            return true;
        }else {
            return false;
        }
    }

    public static void main(String[] args) {
        //读入需要处理的文件
        try {
            BufferedReader in= new BufferedReader(new FileReader("DFA.in"));
            StringTokenizer str=new StringTokenizer(in.readLine());
            //状态数量
            int n=Integer.valueOf(str.nextToken());
            int m=Integer.valueOf(str.nextToken());
            while (n!=0){
                //状态转移矩阵
                int[][] next=new int[n][m];
                for (int i=0;i<n;i++){
                    str=new StringTokenizer(in.readLine());
                    for (int j=0;j<m;j++){
                        next[i][j]=Integer.parseInt(str.nextToken());
                    }
                }
                String line=in.readLine();
                StringTokenizer finalTokenizer=new StringTokenizer(line);
                //结束状态

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
