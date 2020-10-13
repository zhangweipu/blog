package com.wp.weipu.test.leetcode;

import org.junit.Test;

import java.util.*;

public class Mt2 {

    static class Books {
        public String code;
        public String x;
        public String y;
        //1有效，0无效
        public int state;

        public Books(String code, String x, String y) {
            this.code = code;
            this.x = x;
            this.y = y;
        }

        public Books(String code, String y) {
            this.code = code;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int q = in.nextInt();
        Map<String, List<Books>> maps = new HashMap<>();
        for (int u = 0; u < q; u++) {
            while (in.hasNextLine()) {
                String a = in.nextLine();
                String[] ss = a.split(" ");
                if ("1".equals(ss[0])) {
                    if (maps.get("1") != null) {
                        maps.get("1").add(new Books(ss[0], ss[1], ss[2]));
                    } else {
                        List<Books> b = new ArrayList<>();
                        maps.put("1", b);
                    }
                } else {
                    if (maps.get(ss[0]) != null) {
                        maps.get(ss[0]).add(new Books(ss[0], ss[1]));
                    } else {
                        List<Books> b = new ArrayList<>();
                        b.add(new Books(ss[0], ss[1]));
                        maps.put(ss[0], b);
                    }
                }
            }
        }
        //加锁
        List<Books> locks = maps.get("2");
        List<Books> onBook = maps.get("1");
        for (Books b : locks) {
            for (Books a : onBook) {
                if (b.y.equals(a.y)) {
                    //锁上了
                    a.state = 1;
                }
            }
        }
        List<Books> unLocks = maps.get("3");
        //解锁
        for (Books ul : unLocks) {
            for (Books a : onBook) {
                if (ul.y.equals(a.y)) {
                    //锁上了
                    a.state = 0;
                }
            }
        }
        //还书啥意思

        //借书
        List<Books> borrows = maps.get("4");
        for (Books bor : borrows) {
            String row = "-1";
            for (Books a : onBook) {
                if (a.state == 0) {
                    if (bor.y.equals(a.x)) {
                        row = a.y;
                    }
                }
            }
            System.out.println(row);
        }

    }

    public int getNums(int[] arr) {
        int n = arr.length;
        int i = 0, j = n - 1;
        int sum = 0;
        while (i < j - 1) {
            for (int k = i + 1; k < j; k++) {
                if (arr[i] <= arr[k] && arr[k] <= arr[j]) {
                    sum++;
                }
            }
            i++;
        }
        return sum;
    }

    @Test
    public void tets6() {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        System.out.println(getTimes(arr, 5));
    }

    public int getTimes(int[] arr, int n) {
        Arrays.sort(arr);
        int ave = Arrays.stream(arr).sum() / n;
        int i = 0, j = n - 1;
        int time = 0;
        while (i < j) {
            if (arr[i] < ave) {
                int tmp = ave - arr[i];
                while (i < j && arr[j] - tmp < ave) {
                    if (arr[j] < ave) {
                        return time;
                    }
                    j--;
                    arr[j] -= ((ave - tmp) - arr[j + 1]);

                }
                time += tmp;
            }
            i++;
        }
        return time;
    }

    public int numIdenticalPairs(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.merge(i, 1, Integer::sum);
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int n=getNum(entry.getValue());
            if (n==1){
                continue;
            }
            ans += getNum(entry.getValue());
        }
        return ans;
    }

    public int getNum(int n) {
        int sum = 1;
        for (int i = 3; i <= n; i++) {
            sum = sum + i - 1;
        }
        return sum;
    }

    @Test
    public void test7(){
        System.out.println(getNum(1));
    }
}
