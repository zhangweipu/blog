package com.wp.weipu.test.leetcode;

import org.junit.Test;

import javax.validation.constraints.Min;
import java.nio.charset.Charset;
import java.util.*;

/**
 * 新的开始
 */
public class EnjoyArg {

    /**
     * 使用分治法进行大数相乘
     *
     * @param a
     * @param b
     * @return
     */
    public long karatsuba(long a, long b) {
        String a1 = String.valueOf(a);
        String b1 = String.valueOf(b);
        //位数足够小的时候直接相乘
        if (a1.length() == 1 || b1.length() == 1) {
            return a * b;
        }
        int k = Math.max(a1.length(), b1.length()) / 2;
        //拆分成两个数,这两个参数都是根据推导公式得到的
        long big1 = (long) Math.pow(10, k);
        long big2 = (long) Math.pow(10, k * 2);
        //截取大数
        long high1 = Long.parseLong(a1.substring(0, k));
        long low1 = Long.parseLong(a1.substring(k));
        long high2 = Long.parseLong(b1.substring(0, k));
        long low2 = Long.parseLong(b1.substring(k));
        //根据公式可得n
        long z0 = karatsuba(low1, low2);
        long z1 = karatsuba((high1 + low1), (high2 + low2));
        long z2 = karatsuba(high1, high2);
        long zk = z1 - z2 - z0;
        z2 = z2 * big2;
        zk = zk * big1;
        return z0 + z2 + zk;
    }

    /**
     * 1和0
     * 挨个数也行的吧
     * 是总数，我这做错了
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int length = strs.length;
        //三个状态量
        int[][][] dp = new int[length + 1][m + 1][n + 1];
        for (int i = 1; i <= length; i++) {
            int[] count = count(strs[i - 1]);
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    if (count[0] > j || count[1] > k) {
                        dp[i][j][k] = dp[i - 1][j][k];
                    } else {
                        //这个转移怎么做的不懂
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - count[0]][k - count[1]] + 1);
                    }
                }
            }
        }
        return dp[length][m][n];
    }

    public int[] count(String str) {
        int[] res = new int[2];
        for (char a : str.toCharArray()) {
            if (a == '1') {
                res[1]++;
            } else {
                res[0]++;
            }
        }
        return res;
    }

    /**
     * 暴力试试
     * 双循环的就是多余了。。。。傻了傻了。。。todo：还是没学会动态规划
     *
     * @param arr
     * @return
     */
    public int maxTurbulenceSize(int[] arr) {
        int n = arr.length;
        int ans = 1;
        //记录初始索引
        int anchor = 0;
        for (int i = 1; i < n; i++) {
            //比较大小
            int c = Integer.compare(arr[i - 1], arr[i]);
            //达到转换条件了。。 下面这句说明相邻的出现同号了。。。
            if (i == n - 1 || c * Integer.compare(arr[i], arr[i + 1]) != -1) {
                if (c != 0) ans = Math.max(ans, i - anchor + 1);
                anchor = i;
            }
        }
        return ans;
    }

    @Test
    public void test() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (j == 3) {
                    break;
                }
                System.out.println(j);
            }
        }
    }

    /**
     * 这事啥三角
     *
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        if (numRows == 0) {
            return ans;
        }
        List<Integer> subList = new ArrayList<>();
        subList.add(1);
        ans.add(subList);
        for (int i = 0; i < numRows - 1; i++) {
            List<Integer> preList = ans.get(i);
            subList = new ArrayList<>();
            subList.add(1);
            for (int j = 0; j < i; j++) {
                subList.add(preList.get(j) + preList.get(j + 1));
            }
            subList.add(1);
            ans.add(subList);
        }
        return ans;
    }

    /**
     * 反转矩阵后的得分
     *
     * @param A
     * @return
     */
    public int matrixScore(int[][] A) {
        //把每行的第一位全部改成1
        int m = A.length, n = A[0].length;
        for (int i = 0; i < m; i++) {
            if (A[i][0] == 0) {
                for (int j = 0; j < n; j++) {
                    //0,1转换
                    A[i][j] = 1 - A[i][j];
                }
            }
        }
        //判断列的1的数量是否大于0
        for (int j = 0; j < n; j++) {
            int count = 0;
            for (int i = 0; i < m; i++) {
                if (A[i][j] == 1) {
                    count++;
                }
            }
            if (count < (m + 1) / 2) {
                for (int i = 0; i < m; i++) {
                    A[i][j] = 1 - A[i][j];
                }
            }
        }
        //计算二进制数
        double res = 0;
        for (int i = 0; i < m; i++) {
            double s = 0;
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 1) {
                    s += Math.pow(2, n - j - 1);
                }
            }
            res += s;
        }
        return (int) res;
    }

    @Test
    public void test3() {
        int[][] arr = new int[][]{{0, 1}, {1, 1}};
        System.out.println(matrixScore(arr));
    }

    /**
     * 最富的人，easy级别
     *
     * @param accounts
     * @return
     */
    public int maximumWealth(int[][] accounts) {
        int m = accounts.length, n = accounts[0].length;
        int max = 0;
        for (int i = 0; i < m; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                count += accounts[i][j];
            }
            max = Math.max(max, count);
        }
        return max;
    }

    /**
     * 设计 Goal 解析器,简单级别
     *
     * @param command
     * @return
     */
    public String interpret(String command) {
        Map<String, String> mapK = new HashMap<>();
        mapK.put("G", "G");
        mapK.put("()", "o");
        mapK.put("(al)", "al");
        StringBuilder key = new StringBuilder();
        StringBuilder res = new StringBuilder();
        for (char s : command.toCharArray()) {
            if (s == 'G') {
                res.append("G");
            } else if (s == '(') {
                key.append('(');
            } else if (s == ')') {
                key.append(')');
                res.append(mapK.get(key.toString()));
                key = new StringBuilder();
            } else {
                key.append(s);
            }
        }
        return res.toString();
    }

    /**
     * 将数组拆分成斐波那契序列
     * 使用回溯法
     *
     * @param S
     * @return
     */
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> ans = new ArrayList<>();
        int length = S.length();
        backTrack(ans, S, length, 0, 0, 0);
        return ans;
    }

    //前两个数怎么取,前两个数不做比较。。。。
    public boolean backTrack(List<Integer> ans, String S, int length, int index, int sum, int prev) {
        if (index == length) {
            //至少三个数才能组成斐波那契数列
            return ans.size() >= 3;
        }
        //防止越界
        long currLong = 0;
        for (int i = index; i < length; i++) {
            //0开头的数是无效的
            if (i > index && S.charAt(index) == '0') {
                break;
            }
            currLong = currLong * 10 + S.charAt(i) - '0';
            if (currLong > Integer.MAX_VALUE) {
                break;
            }
            int cur = (int) currLong;
            //如果没有那么多，就不用比较
            if (ans.size() >= 2) {
                if (cur < sum) {
                    continue;
                } else if (cur > sum) {
                    break;
                }
            }
            ans.add(cur);
            if (backTrack(ans, S, length, i + 1, prev + cur, cur)) {
                return true;
            } else {
                ans.remove(ans.size() - 1);
            }
        }
        return false;
    }

    /**
     * 柠檬水找零
     *
     * @param bills
     * @return
     */
    public boolean lemonadeChange(int[] bills) {
//        int n=bills.length;
//        LinkedList<Integer> gets=new LinkedList<>();
//        int sub=0;
//        for (int i=0;i<n;i++){
//            sub=bills[i]-5;
//            while (sub>0&&!gets.isEmpty()){
//                if (sub>=gets.getFirst()){
//                    sub-=gets.removeFirst();
//                }else if (sub>=gets.getLast()){
//                    sub-=gets.removeLast();
//                }
//            }
//            //能找就存进去
//            if(sub==0){
//                if (bills[i]>=gets.getFirst()){
//                    gets.addFirst(bills[i]);
//                }else if (bills[i]<=gets.getLast()){
//                    //这种情况没有办法进行插入啊
//                }
//
//                gets.offer(bills[i]);
//            }
//        }
//        return sub==0;
        //找零只能找5或者十，优先找10的
        int five = 0, ten = 0;
        for (int bill : bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                if (five == 0) {
                    return false;
                }
                five--;
                ten++;
            } else {
                //20的时候
                if (ten == 0) {
                    if (five >= 3) {
                        five -= 3;
                    } else {
                        return false;
                    }
                } else {
                    if (five == 0) {
                        return false;
                    }
                    ten--;
                    five--;
                }
            }
        }
        return true;
    }

    /**
     * Dota2 参议院
     * 我这样做只能支持一轮投票的，当平局的时候就会出错啊啊啊啊
     *
     * @param senate
     * @return
     */
    public String predictPartyVictory(String senate) {
        Map<Character, Integer> res = new HashMap<>();
        int forbidR = 0, forBidD = 0;
        for (char c : senate.toCharArray()) {
            if (c == 'R') {
                if (forbidR > 0) {
                    forbidR--;
                    continue;
                }
                res.merge('R', 1, Integer::sum);
                if (res.getOrDefault('D', 0) > 0) {
                    res.merge('D', -1, Integer::sum);
                } else {
                    forBidD++;
                }
            } else {
                if (c == 'D') {
                    if (forBidD > 0) {
                        forBidD--;
                        continue;
                    }
                    res.merge('D', 1, Integer::sum);
                    if (res.getOrDefault('R', 0) > 0) {
                        res.merge('R', -1, Integer::sum);
                    } else {
                        forbidR++;
                    }
                }
            }
        }
        //改成递归的形式吗
        if (res.getOrDefault('R', 0).equals(res.getOrDefault('D', 0))) {
            if (senate.charAt(0) == 'R') {
                return "Radiant";
            } else {
                return "Dire";
            }
        }
        return res.getOrDefault('R', 0) > res.getOrDefault('D', 0) ? "Radiant" : "Dire";
    }

    @Test
    public void test6() {
        System.out.println(predictPartyVictory("RRDDD"));
    }

    /**
     * 官方答案
     * 使用队列存放R，D出现的次序，
     *
     * @param senate
     * @return
     */
    public String predictPartyVictory2(String senate) {
        Queue<Integer> radiant = new LinkedList<>();
        Queue<Integer> dire = new LinkedList<>();
        int n = senate.length();
        //记录了位置的信息
        for (int i = 0; i < n; i++) {
            if (senate.charAt(i) == 'R') {
                radiant.offer(i);
            } else {
                dire.offer(i);
            }
        }
        while (!radiant.isEmpty() && !dire.isEmpty()) {
            //
            int rIndex = radiant.poll(), dIndex = dire.poll();
            if (rIndex < dIndex) {
                //这个议员变大了就会被他前面的干掉,同理
                radiant.offer(rIndex + n);
            } else {
                dire.offer(dIndex + n);
            }
        }
        return !radiant.isEmpty() ? "Radiant" : "Dire";
    }

    /**
     * 存在重复元素
     * 如果用位运算，存在的问题是，不一定重复几个，使用map
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        Map<Integer, Integer> set = new HashMap<>();
        for (int n : nums) {
            //效率太差，它是o(n)的啊
            if (set.getOrDefault(n, 0) == 0) {
                set.put(n, 1);
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 十二进制的最少数目
     * 动态规划，我不会用。
     * 挨个遍历吧
     * 找到除0以外的最小值
     * 直接返回最大的数就可以了啊啊啊啊
     *
     * @param n
     * @return
     */
    public int minPartitions(String n) {

        return 9;
    }

    /**
     * 题目：小张刷题计划
     * 刷题计划，一天的最小耗时
     * 给定一个数组，将其划分成M份，使得每份元素之和得最大值最小
     * 每份可以任意减去其中一个元素
     * 怎么划分的，怎么求最大值最小的
     * 还使用了二分法实现
     * 查找一天最多能够处理的题，二分查找的一天里处理的最大值
     *
     * @param time
     * @param m
     * @return
     */
    public int minTime(int[] time, int m) {
        int left = 0, right = 0, middle = 0;
        for (int i = 0; i < time.length; i++) {
            right += time[i];
        }
        while (left <= right) {
            middle = (left + right) >> 1;
            //为了判断m天最多可以处理多少
            if (check(middle, time, m)) {
                //如果可以再给多一些题试试
                right = middle - 1;
            } else {
                //m天完不成那么多少给一些
                left = middle + 1;
            }
        }
        //其他是m-1天可以处理掉的意思吧
        return left;
    }

    /**
     * check 什么的
     * 是否可以让每组删除最大值之后，总和都小于等于t
     * limit是某一天最多用的时间
     *
     * @return
     */
    public boolean check(int limit, int[] cost, int day) {
        //这三个参数是做什么的，检查题的完整性的？？？
        int useday = 1, total = 0, maxtime = 0;
        for (int i = 0; i < cost.length; i++) {
            //时间长的就请外援。
            int nextTime = Math.min(maxtime, cost[i]);
            //一天用的时间是否超过最大值
            if (nextTime + total <= limit) {
                total += nextTime;
                maxtime = cost[i];
            } else {
                //这一天装不下了，需要加一天
                useday++;
                maxtime = cost[i];
            }
        }
        //useday最大用时
        return useday < day;
    }

    /**
     * 49. 字母异位词分组
     * 每个字符串内做排序，然后用排序后的字符串做key，存到map中
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] s = str.toCharArray();
            Arrays.sort(s);
            String key = String.valueOf(s);
            if (map.getOrDefault(key, null) == null) {
                List<String> sub = new ArrayList<>();
                sub.add(str);
                map.put(key, sub);
            } else {
                map.get(key).add(str);
            }
        }
        List<List<String>> ans = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            ans.add(entry.getValue());
        }
        return ans;
    }

    @Test
    public void test78() {
        String aa = "eati";
        char[] bb = aa.toCharArray();
        Arrays.sort(bb);
        System.out.println(String.valueOf(bb) instanceof String);
    }

    /**
     * 单调递增的数字
     * 先转化成字符串吧
     * 效率还可以
     *
     * @param N
     * @return
     */
    public int monotoneIncreasingDigits(int N) {
        String nn = String.valueOf(N);
        char[] nnn = nn.toCharArray();
        int len = nnn.length;
        char[] newNum = new char[len];
        for (int i = 0; i < len - 1; i++) {
            if (nnn[i] <= nnn[i + 1]) {
                newNum[i] = nnn[i];
            } else {
                int j = i;
                while (j >= 0) {
                    if (j - 1 >= 0 && nnn[j] == nnn[j - 1]) {
                        j--;
                    } else {
                        newNum[j] = (char) (nnn[j] - 1);
                        for (int l = j + 1; l < len; l++) {
                            newNum[l] = '0' + 9;
                        }
                        return charsToInt(newNum);
                    }
                }
            }
        }
        newNum[len - 1] = nnn[len - 1];
        return charsToInt(newNum);
    }

    public int charsToInt(char[] nums) {
        int ans = 0;
        for (char n : nums) {
            ans = ans * 10 + n - '0';
        }
        return ans;
    }

    @Test
    public void test56() {
        char[] aaa = new char[]{'0', '2', '3'};
        System.out.println(monotoneIncreasingDigits(1254));
    }

    /**
     * 魔术排列
     * 使用递归吧
     * 略难啊
     * 这个题我不会。。。。。。。。
     *
     * @param target
     * @return
     */
    public boolean isMagic(int[] target) {
        if (target == null) {
            return true;
        }
        //划分奇偶位
        int n = target.length;

        return false;
    }

    /**
     * 洗牌
     * 用什么数据结构存，不是很明确
     *
     * @param target
     * @return
     */
    public int[] magicSort(int[] target, int index) {
        int newLen = target.length - index;
        //返回交换结果
        int[] res = new int[newLen];
        //奇数 偶数分开
        int o = 0, e = newLen / 2;
        for (int i = index; i < target.length; i++) {
            if ((i + 1) % 2 == 0) {
                res[o++] = target[i];
            } else {
                res[e++] = target[i];
            }
        }
        return res;
    }

    @Test
    public void test58() {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6};
        System.out.println(String.valueOf(magicSort(arr, 2)));
    }

    /**
     * 单词规律
     * pattern = "abba", str = "dog cat cat dog"
     * 这个怎么产生对应呢
     * 我想用map存他们的状态，
     * todo:这题失败在，考虑问题不全面啊啊啊啊啊
     * 垃圾代码，，，，，
     *
     * @param pattern
     * @param s
     * @return
     */
    public boolean wordPattern(String pattern, String s) {
        //key主要是用来分组的
        Map<Character, List<Integer>> map = new HashMap<>();
        //lambda好像有分组的功能
        for (int i = 0; i < pattern.length(); i++) {
            char key = pattern.charAt(i);
            if (map.getOrDefault(key, null) == null) {
                List<Integer> sub = new ArrayList<>();
                sub.add(i);
                map.put(key, sub);
            } else {
                map.get(key).add(i);
            }
        }
        String[] ss = s.split(" ");
        if (ss.length != pattern.length()) {
            return false;
        }
        Set<String> set = new HashSet<>();
        for (Map.Entry<Character, List<Integer>> entry : map.entrySet()) {
            List<Integer> ls = entry.getValue();
            if (ls.size() == 1) {
                continue;
            } else {
                String base = ss[ls.get(0)];
                set.add(base);
                for (int i = 1; i < ls.size(); i++) {
                    if (!base.equals(ss[ls.get(i)])) {
                        return false;
                    }
                }
            }
        }
        return set.size() == map.size();
    }

    @Test
    public void test68() {
        System.out.println(wordPattern("abba",
                "dog dog dog dog"));
    }
}





