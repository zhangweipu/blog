package com.wp.weipu.test.leetcode;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.Semaphore;

public class MT {
    /**
     * 回文的最大个数
     *
     * @param word
     * @return
     */
    public int getMaxHuiWen(String word) {
        int n = word.length();
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n + 1; j++) {
                StringBuilder sb = new StringBuilder(word.substring(i, j));
                if (isHuiWen(sb)) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean isHuiWen(StringBuilder word) {
        if (word.length() == 1) {
            return true;
        }
        Map<String, Integer> map = new HashMap<>();
        map.merge("dd", 1, Integer::sum);
        return word.toString().equals(word.reverse().toString());
    }

    /**
     * 首个父节点
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode parent = null;
        backtrack(parent, root, p, q);
        return parent;
    }

    public boolean backtrack(TreeNode parent, TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q) {
            return true;
        }
        boolean left = backtrack(parent, root.left, p, q);
        boolean right = backtrack(parent, root.right, p, q);
        if (left && right && parent != null) {
            parent = root;
        }
        return left || right;
    }

    /**
     * 难点在于怎么判断可达
     * 通过map实现深度遍历，
     * 构建邻接矩阵
     *
     * @param n
     * @param connections
     * @return
     */
    public int minReorder(int n, int[][] connections) {
        int[][] mat = new int[n][n];
        for (int[] arr : connections) {
            //怎么判断方向啊
            mat[arr[0]][arr[1]] = 1;
            mat[arr[1]][arr[0]] = -1;
        }
        Set<Integer> can = new HashSet<>();
        //可以直接到达0的
        for (int i = 1; i < n; i++) {
            if (mat[i][0] == -1) {
                can.add(i);
            }
        }
        //间接到达0的路径最长为n-1
        while (n - 1 > 0) {
            for (int i = 1; i < n; i++) {
                for (int j = 1; j < n; j++) {

                }
            }
            n--;
        }

        return 0;
    }

    /**
     * 填字游戏
     *
     * @return
     */
    public int getNums(int n, int k, int d) {
        //判断最大值范围
        int sum = 0;
        //保证最大的值大于等于d,还要去重啊
        for (int i = d; i <= k; i++) {
            sum += backtrace(n - i, i, k, 1);
        }
        return sum;
    }

    public int backtrace(int target, int g, int k, int loc) {
        if (target == 0) {
            return loc;
        }
        int sum = 0;
        for (int i = 1; i <= k; i++) {
            if (target >= i) {
                sum += backtrace(target - i, i, k, loc + 1);
            }
        }
        return sum;
    }


    @Test
    public void test6() {
        System.out.println(getNums(5, 3, 2));
    }

    public static int method(int[] a, int m, int k) {
        if (a.length == 0 || m == 0) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i <= a.length - m; ++i) {
            if (a[i] < k) {
                continue;
            }
            int j = 1;
            for (; j < m; ++j) {
                if (a[i + j] < k) {
                    i = i + j;
                    break;
                }
            }
            if (j == m) {
                result += 1;
            }
        }
        return result;
    }

    @Test
    public void test9() {
//        int[] arr = new int[5];
//        System.out.println(method(arr, 3, 2));
        int[][] arr = new int[][]{{1, 0, 1}, {0, 1, 0}, {0, 1, 0}, {1, 0, 1}, {1, 0, 1}, {0, 1, 0}};
        System.out.println(JSON.toJSONString(searchMat(6, 3, arr)));
    }

    public int[][] searchMat(int n, int m, int[][] mat) {
        StringBuilder[] ss = new StringBuilder[n];
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < m; j++) {
                sb.append(mat[i][j]);
            }
            ss[i] = sb;
        }
        int line = 0;
        //转化成查找回文数组
        for (int i = 1; i < ss.length; i += 2) {
            int j = 0, k = i;
            boolean flag = false;
            while (j < k) {
                //查找最早的回文串
                if (ss[j].reverse().toString().equals(ss[k].toString())) {
                    flag = true;
                } else {
                    flag = false;
                    break;
                }
                j++;
                k--;
            }
            if (flag) {
                line = i - 1;
                break;
            } else {
                line = i;
            }
        }
        int[][] newMat = new int[line][m];
        for (int i = 0; i < line; i++) {
            if (m >= 0) System.arraycopy(mat[i], 0, newMat[i], 0, m);
        }
        return newMat;
    }

//    public List<List<Integer>> pathSum(TreeNode root, int sum) {
//        List<List<Integer>> ans = new ArrayList<>();
//        if (root.val > sum) {
//            return ans;
//        }
//        LinkedList<Integer> res = new LinkedList<>();
//        dfs(root, sum, ans, res);
//        return ans;
//    }

//    public void dfs(TreeNode root, int sum, List<List<Integer>> ans,
//                    LinkedList<Integer> res) {
//
//        if (root == null) {
//            return;
//        }
//        res.add(root.val);
////        sum-=root.val;
//        if (sum == root.val && root.left == null && root.right == null) {
//            ans.add(new ArrayList<>(res));
//        }
//
//        dfs(root.left, sum - root.val, ans, res);
//        dfs(root.right, sum - root.val, ans, res);
//        res.removeLast();
//    }

    @Test
    public void test61() {
        TreeNode root = new TreeNode(5);
        TreeNode root1 = new TreeNode(4);
        TreeNode root2 = new TreeNode(8);
        TreeNode root3 = new TreeNode(11);
        TreeNode root4 = new TreeNode(7);
        TreeNode root5 = new TreeNode(2);
        TreeNode root6 = new TreeNode(13);
        TreeNode root7 = new TreeNode(4);
        TreeNode root8 = new TreeNode(5);
        TreeNode root9 = new TreeNode(1);
        root.left = root1;
        root.right = root2;
        root1.left = root3;
        root3.left = root4;
        root3.right = root5;
        root2.left = root6;
        root2.right = root7;
        root7.left = root8;
        root7.right = root9;
//        List<List<Integer>> ans = pathSum(root, 22);
//        System.out.println(JSON.toJSONString(ans));
    }

    public int findNthDigit(int n) {
        int digit = 1;
        long start = 1;
        long count = 9;
        while (n > count) { // 1.
            n -= count;
            digit += 1;
            start *= 10;
            count = digit * start * 9;
        }
        long num = start + (n - 1) / digit; // 2.
        return Long.toString(num).charAt((n - 1) % digit) - '0'; // 3.
    }

    public int strToInt(String str) {
        StringBuilder sb = new StringBuilder();
        str = str.trim();
        int n = str.length();
        boolean neg = false;
        int i = 0;
        if (str.charAt(0) == '-') {
            neg = true;
            i = 1;
        }
        for (; i < n; i++) {
            if (Character.isDigit(str.charAt(i))) {
                sb.append(str.charAt(i));
            } else {
                break;
            }
        }
        str = sb.toString();
        i = 1;
        System.out.println(str);
        //这里设置了一个边界。。。。。
        int bndry = Integer.MAX_VALUE / 10;
        int sum = 0;
        for (int j = str.length() - 1; j >= 0; j--) {
            //溢出怎么处理
            if (sum > bndry || (sum == bndry && str.charAt(i) > '7')) {
                return neg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            sum += (str.charAt(j) - '0') * i;
            i *= 10;
        }

        return neg ? -sum : sum;
    }

    @Test
    public void test5() {
        System.out.println(strToInt("2147483648"));
    }

    public boolean isNum(String str) {
        if (str == null || str.trim().equals("")) {
            return false;
        }
        char[] arr = str.trim().toCharArray();
        boolean isNum = false, isEOre = false, isDot = false;
        for (int i = 0; i < arr.length; i++) {
            if (Character.isDigit(arr[i])) {
                //判断数字
                isNum = true;
            } else if (arr[i] == '.') {
                //小数点前面必须有数字，不能有e
                if (!isNum || isEOre || isDot) {
                    return false;
                }
                isDot = true;
            } else if (arr[i] == 'e' || arr[i] == 'E') {
                //前面必须有数，不能有e
                if (!isNum || isEOre) {
                    return false;
                }
                isEOre = true;
                //重新开始
                isNum = false;
            } else if (arr[i] == '-') {
                if (i != 0 && arr[i - 1] != 'e' || arr[i - 1] != 'E') {
                    return false;
                }
            } else if (arr[i] == '+') {
                if (i != 0) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return isNum;
    }

    public boolean isNumber2(String s) {
        if (s == null || s.length() == 0) return false; // s为空对象或 s长度为0(空字符串)时, 不能表示数值
        boolean isNum = false, isDot = false, ise_or_E = false; // 标记是否遇到数位、小数点、‘e’或'E'
        char[] str = s.trim().toCharArray();  // 删除字符串头尾的空格，转为字符数组，方便遍历判断每个字符
        for (int i = 0; i < str.length; i++) {
            if (str[i] >= '0' && str[i] <= '9') isNum = true; // 判断当前字符是否为 0~9 的数位
            else if (str[i] == '.') { // 遇到小数点
                if (isDot || ise_or_E) return false; // 小数点之前可以没有整数，但是不能重复出现小数点、或出现‘e’、'E'
                isDot = true; // 标记已经遇到小数点
            } else if (str[i] == 'e' || str[i] == 'E') { // 遇到‘e’或'E'
                if (!isNum || ise_or_E) return false; // ‘e’或'E'前面必须有整数，且前面不能重复出现‘e’或'E'
                ise_or_E = true; // 标记已经遇到‘e’或'E'
                isNum = false; // 重置isNum，因为‘e’或'E'之后也必须接上整数，防止出现 123e或者123e+的非法情况
            } else if (str[i] == '-' || str[i] == '+') {
                if (i != 0 && str[i - 1] != 'e' && str[i - 1] != 'E')
                    return false; // 正负号只可能出现在第一个位置，或者出现在‘e’或'E'的后面一个位置
            } else return false; // 其它情况均为不合法字符
        }
        return isNum;
    }

    @Test
    public void test45() {
        int i = 1;
        int j = 2;
        //短的
        if (i == 1 || (j = 3) == 3) {
            System.out.println("执行了吗");
        }
        System.out.println(j);
        //长的
        if (i == 1 | (j = 4) == 5) {
            System.out.println("这个是啥");
        }
        System.out.println(j);
        if (i != 1 & (j = 6) == 6) {
            System.out.println("不执行");
        }
        System.out.println(j);
        if (i != 1 && (j = 8) == 8) {
            System.out.println("不执行");
        }
        System.out.println(j);
    }


    public int findFriendNum(int[][] M) {
        // write code here
        int[][] dire = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int n = M.length, m = M.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (M[i][j] == 1) {
                    sum++;
                    dfs(M, dire, i, j);
                }
            }
        }

        return sum;
    }

    public void dfs(int[][] M, int[][] dire, int i, int j) {
        int n = M.length, m = M.length;
        for (int[] dir : dire) {
            int nex = i + dir[0];
            int ney = j + dir[1];
            if (nex < 0 || nex >= n || ney < 0 || ney >= m ||
                    M[nex][ney] == 2 || M[nex][ney] == 0) {
                continue;
            }
            M[nex][ney] = 2;
            dfs(M, dire, nex, ney);
        }
    }

    @Test
    public void test19() {
        System.out.println();
    }

    /**
     * 会有n为魔法次数
     *
     * @param m
     * @param n
     * @param s
     * @param nums
     * @return
     */

    static int mm = 0;

    public static int getNum(int m, int n, int s, int[] nums) {

        int ans = dfs(s, n, m, nums, 0);

        return ans + mm;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        System.out.println(getNum(m, n, s, nums));
    }

    public static int dfs(int s, int n, int m, int[] nums, int i) {
        if (s == 0) {
            return 1;
        }
        int res = 0;
        for (int j = i; j < n; j++) {
            if (s < nums[j]) {
                continue;
            }
            int jie = getN(nums[j]);
            //魔法没用完的情况
            if (jie == s && m > 0) {
                m--;
                mm++;
            }
            //魔法没用完
            if (jie < s && m > 0) {
                res += dfs(s - jie, n, m, nums, j + 1);
            }
            res += dfs(s - nums[j], n, m, nums, j + 1);
        }
        return res;
    }

    public static int getN(int n) {
        int ans = 1;
        for (int i = 1; i <= n; i++) {
            ans *= i;
        }
        return ans;
    }

    public String[] countString(String[] inputArray) {
        // write code here
        Map<String, Integer> map = new HashMap<>();
        for (String s : inputArray) {
            map.merge(s, 1, Integer::sum);
        }
        List<String> ans = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                ans.add(entry.getKey());
            }
        }
        return ans.toArray(new String[0]);
    }

    class TreeNode {
        long val;
        TreeNode left;
        TreeNode right;

        TreeNode(long val) {
            this.val = val;
        }
    }

    public long[] buildPostOrder(long[] pre, long[] in) {
        // write code here
        if (pre == null || pre.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
        int length = pre.length;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int inIndex = 0;
        for (int i = 1; i < length; i++) {
            long preVal = pre[i];
            TreeNode node = stack.peek();
            if (node.val != in[inIndex]) {
                node.left = new TreeNode(preVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == in[inIndex]) {
                    node = stack.pop();
                    inIndex++;
                }
                node.right = new TreeNode(preVal);
                stack.push(node.right);
            }
        }
        List<Long> ans = new ArrayList<>();
        post(root, ans);
        long[] res = new long[ans.size()];
        int i = 0;
        for (long d : ans) {
            res[i++] = d;
        }
        return res;
    }

    public void post(TreeNode root, List<Long> ans) {
        if (root == null) {
            return;
        }
        post(root.left, ans);
        post(root.right, ans);
        ans.add(root.val);
    }

    @Test
    public void test89() {
        long[] a = new long[]{3, 9, 20, 15, 7};
        long[] b = new long[]{9, 3, 15, 20, 7};
        for (long d : buildPostOrder(a, b)) {
            System.out.println(d);
        }
    }

    /**
     * 笨阶乘
     * 减的时候不好处理
     * 每三个计算一下，减另外处理
     * 难点主要在于计算顺序上
     *
     * @param N
     * @return
     */
    public int clumsy(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1;
        if (N == 2) return 2;
        if (N == 3) return 6;
        if (N == 4) return 7;
        return N * (N - 1) / (N - 2) + getD(N - 3);
    }

    public int getD(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1;
        if (N == 2) return 1;
        if (N == 3) return 1;
        return N - (N - 1) * (N - 2) / (N - 3) + getD(N - 4);
    }

    @Test
    public void test78() {
        int an = clumsy(10);
        System.out.println(an);
    }

    public boolean canPartition(int[] nums) {
        int n = nums.length;
        if (n == 1 || n == 0) {
            return false;
        }
        //先排序
        int a = Arrays.stream(nums).sum();
        if (a % 2 != 0) {
            return false;
        }
        int target = a / 2;
        if (nums[n - 1] > target) {
            return false;
        }
        int[] visited = new int[n];
        return dfs(nums, visited, target);

    }

    public boolean dfs(int[] nums, int[] visited, int target) {
        if (target == 0) {
            return true;
        }
        int n = nums.length;
        boolean res = false;
        for (int j = 0; j < n; j++) {
            if (target < nums[j] || visited[j] == 1) {
                continue;
            }
            visited[j] = 1;
            res = res || dfs(nums, visited, target - nums[j]);
            visited[j] = 0;
        }
        return res;
    }
}
