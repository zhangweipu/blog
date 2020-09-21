package com.wp.weipu.test;

import com.wp.weipu.test.leetcode.LNode;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class XM {

    public static void main(String[] args) {
//        Scanner in=new Scanner(System.in);
//        String str=in.next();
        String str = "www  ffff";
        Set<Character> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (!set.contains(c)) {
                sb.append(c);
                set.add(c);
            }
        }
        System.out.println(sb.toString());
    }

    /**
     * @param mat
     * @return
     */
    public int[] getSubMatrix(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[] b = new int[m];
        int max = 0;
        int maxsum = Integer.MIN_VALUE;
        int[] res = new int[4];
        int leftI = 0, leftJ = 0;
        for (int i = 0; i < n; i++) {
            //b数组归零
            for (int j = 0; j < m; j++) {
                b[j] = 0;
            }
            //计算i-j每行的和

            for (int j = i; j < n; j++) {
                max = 0;
                for (int l = 0; l < m; l++) {
                    //这一段是求最大子串
                    b[l] += mat[j][l];
                    if (max > 0) {
                        max += b[l];
                    } else {
                        max = b[l];
                        //i才是其左上角
                        leftI = j;
                        leftJ = l;
                    }
                    if (max > maxsum) {
                        maxsum = max;
                        res[0] = leftI;
                        res[1] = leftJ;
                        res[2] = l;
                        res[3] = j;
                    }
                }

            }
        }
        return res;
    }

    public LNode reserveNode(LNode head) {
        LNode newHead = null;
        while (head != null) {
            LNode tmp = head.next;
            head.next = newHead;
            newHead = head;
            head = tmp;
        }
        return newHead;
    }

    public LNode dfsReserve(LNode head) {
        if (head.next == null) {
            return head;
        }
        LNode tmp = dfsReserve(head.next);
        tmp.next = head;
        return head;
    }

    @Test
    public void test() {
        LNode head = new LNode(3);
        LNode tmp = head;
        head.next = new LNode(4);
        head = head.next;
        head.next = new LNode(2);
        LNode tmp1 = new LNode(-1);
        tmp1.next = tmp;
        tmp = dfsReserve(tmp);
        while (tmp != null) {
            System.out.println(tmp.val);
            tmp = tmp.next;
        }
    }
}
