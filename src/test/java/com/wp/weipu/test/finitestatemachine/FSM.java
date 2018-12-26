package com.wp.weipu.test.finitestatemachine;



/**
 * 有限状态机
 */
public class FSM {
    //状态机转化表
    static State[][] transTable=new State[State.values().length][10];
    //初始化状态机
    static {
        //普通话请按1
        transTable[State.start.ordinal()][1]=State.chinese;
        //press two for english
        transTable[State.start.ordinal()][2]=State.english;
        transTable[State.chinese.ordinal()][0]=State.end;
        transTable[State.english.ordinal()][0]=State.end;
    }

    State current=State.start;

    //转换函数
    State step(State s,char c){
        return transTable[s.ordinal()][c-'0'];
    }

    public static void main(String[] args) {
        FSM fsm=new FSM();
        System.out.println(fsm.step(fsm.current,'1'));
    }
}
