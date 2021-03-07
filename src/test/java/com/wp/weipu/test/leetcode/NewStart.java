package com.wp.weipu.test.leetcode;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewStart {
    /**
     * target是original里面的，所以比较只能比较他们俩
     *
     * @param original
     * @param cloned
     * @param target
     * @return
     */
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned,
                                        final TreeNode target) {
        if (original == null) {
            return null;
        }
        if (original == target) {
            return cloned;
        }
        TreeNode root = getTargetCopy(original.left, cloned.left, target);
        if (root != null) {
            return root;
        }
        root = getTargetCopy(original.right, cloned.right, target);
        if (root != null) {
            return root;
        }
        return null;
    }

    /**
     * 求幂集
     * 利用了保存的记录
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        for (int n : nums) {
            int len = ans.size();
            for (int i = 0; i < len; i++) {
                List<Integer> tmp = new ArrayList<>();
                tmp.addAll(ans.get(i));
                tmp.add(n);
                ans.add(tmp);
            }
        }
        return ans;
    }

    public List<String> commonChars(String[] A) {
        int[] minfreq = new int[26];
        Arrays.fill(minfreq, Integer.MAX_VALUE);
        for (String a : A) {
            int[] freq = new int[26];
            int len = a.length();
            for (int i = 0; i < len; i++) {
                char ch = a.charAt(i);
                //计算这个字符出现的频率
                ++freq[ch - 'a'];
            }
            for (int i = 0; i < len; i++) {
                minfreq[i] = Math.min(minfreq[i], freq[i]);
            }
        }
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 26; ++i) {
            for (int j = 0; j < minfreq[i]; ++j) {
                ans.add(String.valueOf((char) (i + 'a')));
            }
        }
        return ans;
    }

    /**
     * 这个是怎么用dp做的
     *
     * @param
     */
    public void main() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String[] a = new String[n];
        String[] b = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.next();
        }
        for (int i = 0; i < n; i++) {
            b[i] = in.next();
        }
        //为什么是动态规划。。。。
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            if (a[i].equals(b[0])) {
                dp[i][0] = 1;
                dp[0][i] = 1;
            }
            if (b[i].equals(a[0])) {
                dp[i][0] = 1;
                dp[0][i] = 1;
            }
        }
        //这是啥意思。。。
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (a[i].equals(b[j])) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        System.out.println(dp[n - 1][n - 1]);
    }


    public void main2() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int p = sc.nextInt();
        int q = sc.nextInt();
        int[][] a = new int[p][3];
        int[][] b = new int[q][3];
        for (int i = 0; i < p; i++) {
            for (int j = 0; j < 3; j++) {
                a[i][j] = sc.nextInt();
            }
        }
        for (int i = 0; i < q; i++) {
            for (int j = 0; j < 3; j++) {
                b[i][j] = sc.nextInt();
            }
        }
        sc.close();
        int[][] c = new int[n][n];
        for (int i = 0; i < p; i++) {
            for (int j = 0; j < q; j++) {
                if (a[i][1] == b[j][0]) {//由图可以看出当A矩阵的列号等于B矩阵的行号时求和
                    c[a[i][0] - 1][b[j][1] - 1] += a[i][2] * b[j][2];
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println(i + 1 + " " + (j + 1) + " " + c[i][j]);
            }

        }
    }

    /**
     * 最长重复串
     * 求满足字符个数大于等于k的子串
     *
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char ss : s.toCharArray()) {
            map.merge(ss, 1, Integer::sum);
        }
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) < k) {
                sb.setCharAt(i, ',');
            }
        }
        System.out.println(sb.toString());
        //隔出满足条件的子串。。。
        String[] strings = sb.toString().split(",");
        if (strings.length == 1) {
            return strings[0].length();
        }
        int max = 0;
        for (String str : strings) {
            max = Math.max(max, longestSubstring(str, k));
        }
        return max;
    }

    @Test
    public void test() {
        System.out.println(longestSubstring("ababababa", 3));
    }

    public int[] decompressRLElist(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i += 2) {
            for (int j = 0; j < nums[i]; j++) {
                ans.add(nums[i + 1]);
            }
        }
        int[] res = new int[ans.size()];
        AtomicInteger s = new AtomicInteger(0);
        ans.forEach(x -> {
            res[s.getAndIncrement()] = x;
        });
        return res;
    }

    @Test
    public void test8() {
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            ans.add(i);
        }
        ans.remove(new Integer(8));
    }

    /**
     * 生成一个二叉树，每次都要找到每一部分的最大值
     *
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTree(nums, 0, nums.length);
    }

    public TreeNode constructMaximumBinaryTree(int[] nums, int i, int j) {
        if (i >= j) {
            return null;
        }
        int index = 0;
        int max = Integer.MIN_VALUE;
        for (int k = i; k < j; k++) {
            if (max < nums[k]) {
                max = nums[k];
                index = k;
            }
        }
        TreeNode root = new TreeNode(nums[index]);
        //这个地方出错了一次
        root.right = constructMaximumBinaryTree(nums, index + 1, j);
        root.left = constructMaximumBinaryTree(nums, i, index);
        return root;
    }

    public List<Integer> getPrior(int[][] person) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] - o2[1] > 0) {
                    return -1;
                } else if (o1[1] == o2[1]) {
                    if (o1[2] - o2[2] > 0) {
                        return -1;
                    } else {
                        return 1;
                    }

                }
                return 1;
            }
        });
        for (int[] a : person) {
            queue.offer(a);
        }
        while (!queue.isEmpty()) {
            System.out.println(queue.poll()[2]);
        }
        return null;
    }

    @Test
    public void test7() {
        Stack<Integer> stack = new Stack<>();
        int sum = 4;
        int start = 4;
        for (int i = 1; i <= 6; i++) {
            stack.push(start);
            sum += start;
            start *= 2;
        }
        stack.push(sum);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    /**
     * 全排列
     *
     * @param S
     * @return
     */
    public String[] permutation(String S) {
        char[] arr = S.toCharArray();
        int[] visited = new int[arr.length];
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(arr, visited, sb, ans);
        return ans.toArray(new String[ans.size()]);
    }

    public void dfs(char[] s, int[] visited, StringBuilder sb, List<String> ans) {
        if (sb.length() == visited.length) {
            ans.add(sb.toString());
        }
        for (int i = 0; i < visited.length; i++) {
            if (visited[i] == 1) {
                continue;
            }
            visited[i] = 1;
            dfs(s, visited, sb.append(s[i]), ans);
            visited[i] = 0;
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /**
     * 生成括号，也是用dfs
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        int left = 0, right = 0;
        String str = "";
        dfs(left, right, n, str, ans);
        return ans;
    }

    public void dfs(int left, int right, int n, String str, List<String> ans) {
        if (left == n && left == right) {
            ans.add(str);
            return;
        }
        if (left <= n) {
            dfs(left + 1, right, n, str + "(", ans);
        }
        if (right <= left) {
            dfs(left, right + 1, n, str + ")", ans);
        }
    }

    /**
     * 相当于层次遍历
     *
     * @param tree
     * @return
     */
    public ListNode[] listOfDepth(TreeNode tree) {
        List<ListNode> ls = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(tree);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ListNode pre, head = new ListNode(-1);
            pre = head;
            for (int i = 0; i < size && queue.peek() != null; i++) {
                TreeNode root = queue.poll();
                head.next = new ListNode(root.val);
                head = head.next;
                if (root.left != null) {
                    queue.offer(root.left);
                }
                if (root.right != null) {
                    queue.offer(root.right);
                }
            }
            ls.add(pre.next);
        }
        return ls.toArray(new ListNode[0]);
    }

    public boolean isBalance(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return false;
        }
        Arrays.sort(nums);
        int last = nums[n - 1];
        for (int i = 0; i < n - 1; i++) {

        }

        return true;
    }

    @Test
    public void test78() {
        char[] aa = {'h', 'e', 'l', 'l', 'o'};
        String a = new String("hello");
        System.out.println(a.equals(aa));
    }

    public int countDays(String[] days) {
        int num = days.length;
        int[] day = new int[31];
        for (String s : days) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '1') {
                    day[i]++;
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < 31; i++) {
            if (day[i] == num) {
                sum++;
            }
        }
        return sum;
    }

    @Test
    public void test67() {
        System.out.println(toChinese("DD233.50"));
        System.out.println(toChinese("6,007.14"));
        System.out.println(toChinese("1,680.32"));
    }

    private Pattern AMOUNT_PATTERN = Pattern.compile("^(0|[1-9]\\d{0,11})\\.(\\d\\d)$"); // 不考虑分隔符的正确性
    private char[] RMB_NUMS = "零壹贰叁肆伍陆柒捌玖".toCharArray();
    private String[] UNITS = {"元", "角", "分", "整"};
    private String[] U1 = {"", "拾", "佰", "仟"};
    private String[] U2 = {"", "万", "亿"};

    /**
     * 将金额（整数部分等于或少于 12 位，小数部分 2 位）转换为中文大写形式.
     *
     * @param amount 金额数字
     * @return 中文大写
     * @throws IllegalArgumentException
     */
    public String toChinese(String amount) {
        // 去掉分隔符
        amount = amount.replace(",", "");
        Matcher matcher = AMOUNT_PATTERN.matcher(amount);
        if (!matcher.find()) {
            return "抱歉，输入数字不符合要求！";
        }
        String integer = matcher.group(1); // 整数部分
        String fraction = matcher.group(2); // 小数部分
        if (integer.length() > 16) {
            return "超出计算能力！";
        }
        String result = "";
        if (!integer.equals("0")) {
            result += integer2rmb(integer) + UNITS[0]; // 整数部分
        }
        if (fraction.equals("00")) {
            result += UNITS[3]; // 添加[整]
        } else if (fraction.startsWith("0") && integer.equals("0")) {
            result += fraction2rmb(fraction).substring(1); // 去掉分前面的[零]
        } else {
            result += fraction2rmb(fraction); // 小数部分
        }

        return result;
    }

    // 将金额小数部分转换为中文大写
    private String fraction2rmb(String fraction) {
        char jiao = fraction.charAt(0); // 角
        char fen = fraction.charAt(1); // 分
        return (RMB_NUMS[jiao - '0'] + (jiao > '0' ? UNITS[1] : ""))
                + (fen > '0' ? RMB_NUMS[fen - '0'] + UNITS[2] : "");
    }

    // 将金额整数部分转换为中文大写
    private String integer2rmb(String integer) {
        StringBuilder buffer = new StringBuilder();
        // 从个位数开始转换
        int i, j;
        for (i = integer.length() - 1, j = 0; i >= 0; i--, j++) {
            char n = integer.charAt(i);
            if (n == '0') {
                // 当 n 是 0 且 n 的右边一位不是 0 时，插入[零]
                if (i < integer.length() - 1 && integer.charAt(i + 1) != '0') {
                    buffer.append(RMB_NUMS[0]);
                }
                // 插入[万]或者[亿]
                if (j % 4 == 0) {
                    if (i > 0 && integer.charAt(i - 1) != '0' || i > 1 && integer.charAt(i - 2) != '0'
                            || i > 2 && integer.charAt(i - 3) != '0') {
                        buffer.append(U2[j / 4]);
                    }
                }
            } else {
                if (j % 4 == 0) {
                    buffer.append(U2[j / 4]); // 插入[万]或者[亿]
                }
                buffer.append(U1[j % 4]); // 插入[拾]、[佰]或[仟]
                buffer.append(RMB_NUMS[n - '0']); // 插入数字
            }
        }
        return buffer.reverse().toString();
    }

    int[][] direction = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    //先找到开始的岛屿使用深搜
    public int islandPerimeter(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int sum = 0;
        int[][] visited = new int[m][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                }
            }
        }
        return sum;
    }

    public int dfs(int i, int j, int[][] visited, int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int sum = 0;
        for (int[] dir : direction) {
            int newX = i + dir[0];
            int newY = j + dir[1];
            if (newX < 0 || newX >= n || newY < 0 || newY > m || grid[newX][newY] == 0) {
                sum++;
                continue;
            }
            visited[newX][newY] = 1;
            sum += dfs(newX, newY, visited, grid);
            visited[newX][newY] = 1;
        }
        return sum;
    }

    public List<String> getValidT9Words(String num, String[] words) {
        Map<Character, Character> map = new HashMap<>();
        map.put('a', '2');
        map.put('b', '2');
        map.put('c', '2');
        map.put('d', '3');
        map.put('e', '3');
        map.put('f', '3');
        map.put('g', '4');
        map.put('h', '4');
        map.put('i', '4');
        map.put('j', '5');
        map.put('k', '5');
        map.put('l', '5');
        map.put('m', '6');
        map.put('n', '6');
        map.put('o', '6');
        map.put('p', '7');
        map.put('q', '7');
        map.put('r', '7');
        map.put('s', '7');
        map.put('t', '8');
        map.put('u', '8');
        map.put('v', '8');
        map.put('w', '9');
        map.put('x', '9');
        map.put('y', '9');
        map.put('z', '9');
        List<String> ans = new ArrayList<>();
        for (String d : words) {
            StringBuilder sb = new StringBuilder();
            for (char a : d.toCharArray()) {
                sb.append(map.get(a));
            }
            if (num.equals(sb.toString())) {
                ans.add(d);
            }
        }
        return ans;
    }

    /**
     * 奇数放在奇数位，偶数放在偶数位
     *
     * @param A
     * @return
     */
    public int[] sortArrayByParityII(int[] A) {
        int len = A.length;
        //原地交换，时间复杂度就高
        for (int i = 0; i < len; i++) {
            if (i % 2 == 0) {
                if (A[i] % 2 != 0) {
                    int j = i + 1;
                    while (j < len) {
                        if (j % 2 != 0 && A[j] % 2 == 0) {
                            break;
                        }
                        j++;
                    }
                    //j会outindex吧？ 讲道理不会的
                    int tmp = A[i];
                    A[i] = A[j];
                    A[j] = tmp;
                }
            } else {
                if (A[i] % 2 == 0) {
                    int j = i + 1;
                    while (j < len) {
                        if (j % 2 != 0 && A[j] % 2 != 0) {
                            break;
                        }
                        j++;
                    }
                    int tmp = A[i];
                    A[i] = A[j];
                    A[j] = tmp;
                }
            }
        }
        return A;
    }

    /**
     * 空间换时间
     *
     * @param A
     * @return
     */
    public int[] sortArrayByParityII1(int[] A) {
        int len = A.length;
        int[] ans = new int[len];
        int odd = 0, ev = 1;
        for (int i = 0; i < len; i++) {
            if (A[i] % 2 == 0) {
                ans[odd] = A[i];
                odd += 2;
            } else {
                ans[ev] = A[i];
                ev += 2;
            }
        }
        return ans;
    }

    /**
     * 用户分组
     *
     * @param groupSizes
     * @return
     */
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        int size = groupSizes.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            if (map.get(groupSizes[i]) == null) {
                List<Integer> tmp = new ArrayList<>();
                tmp.add(i);
                map.put(groupSizes[i], tmp);
            } else {
                map.get(groupSizes[i]).add(i);
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            int k = entry.getKey();
            List<Integer> tmp = entry.getValue();
            List<Integer> t = new ArrayList<>();
            for (int i = 0; i < tmp.size(); i++) {
                if (i % k == 0) {
                    t = new ArrayList<>();
                    ans.add(t);
                }
                t.add(tmp.get(i));

            }

        }
        return ans;
    }

    @Test
    public void test34() {
        int[] a = new int[]{3, 3, 3, 3, 3, 1, 3};
        List<List<Integer>> ans = groupThePeople(a);
        System.out.println(JSON.toJSONString(ans));
    }

    /**
     * 弄一个全局的参数保存
     * 用map保存
     * 效率太差，使用bfs求和最后一层就可
     *
     * @param root
     * @return
     */
    public int deepestLeavesSum(TreeNode root) {
        int level = 0;
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        dfs(root, map, level);
        List<Integer> ans = map.get(map.lastKey());
        //这个是保证线程安全的
        AtomicReference<Integer> sum = new AtomicReference<>(0);
        ans.forEach(x -> sum.updateAndGet(v -> v + x));
        return sum.get();
    }

    public void dfs(TreeNode root, Map<Integer, List<Integer>> map, int level) {
        if (root == null) {
            return;
        }
        if (root.right == null && root.left == null) {
            map.computeIfAbsent(level, k -> new ArrayList<>()).add(root.val);
        }
        level++;
        dfs(root.left, map, level);
        dfs(root.right, map, level);
    }

    public int deepestLeavesSum1(TreeNode root) {
        return bfs(root);
    }

    public int bfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int sum = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode tmp = queue.poll();
                assert tmp != null;
                sum += tmp.val;
                if (tmp.left != null) {
                    queue.offer(tmp.left);
                }
                if (tmp.right != null) {
                    queue.offer(tmp.right);
                }
            }
        }
        return sum;
    }

    /**
     * 祖父节点值为偶数的节点和
     *
     * @param root
     * @return
     */
    public int sumEvenGrandparent(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        dfs(root, 1, 1, ans);
        AtomicInteger a = new AtomicInteger(0);
        ans.forEach(a::addAndGet);
        return a.get();
    }

    public void dfs(TreeNode root, int father, int gradf, List<Integer> res) {
        if (root == null) {
            return;
        }
        if (gradf % 2 == 0) {
            res.add(root.val);
        }
        dfs(root.right, root.val, father, res);
        dfs(root.left, root.val, father, res);
    }

    /**
     * 奇偶链表，奇数位的节点放在一块，放在前面
     *
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        ListNode pre = head, cur = head.next, tmp = head;
        int i = 2;
        while (cur != null) {
            if (i % 2 == 0) {
                tmp = cur;
                cur = cur.next;
            } else {
                tmp.next = cur.next;
                cur.next = pre.next;
                pre.next = cur;
                pre = pre.next;
                cur = tmp.next;
            }
            i++;
        }
        return head;
    }

    /**
     * 挨个遍历的话会慢吧，怎么优化
     * 等差子数组
     *
     * @param nums
     * @param l
     * @param r
     * @return
     */
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        int m = l.length;
        List<Boolean> ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int n = r[i] - l[i];
            int[] tmp = new int[n + 1];
            int index = 0;
            for (int j = l[i]; j <= r[i]; j++) {
                tmp[index++] = nums[j];
            }
            Arrays.sort(tmp);
            ans.add(isSubEqual(tmp));
        }
        return ans;
    }

    /**
     * 输入之前需要排序
     *
     * @param nums
     * @return
     */
    public boolean isSubEqual(int[] nums) {
        int n = nums.length;
        //长度为1怎么办
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] - nums[i - 1] != nums[i + 1] - nums[i]) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test79() {
        int[] t = new int[]{4, 6, 5, 9, 3, 7};
        int[] l = {0, 0, 2};
        int[] r = {2, 3, 5};
        List<Boolean> ans = checkArithmeticSubarrays(t, l, r);
        System.out.println(JSON.toJSONString(ans));
    }

    /**
     * 不太好描述
     * leetcode 查询带键的排列，理解错了
     * 用list存，删除和添加方便些
     *
     * @param queries
     * @param m
     * @return
     */
    public int[] processQueries(int[] queries, int m) {
        int n = queries.length;
        int[] ans = new int[n];
        List<Integer> p = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            p.add(i);
        }
        for (int i = 0; i < n; i++) {
            int index = queries[i];
            int j;
            for (j = 0; j < p.size(); j++) {
                if (index == p.get(j)) {
                    break;
                }
            }
            int t = p.remove(j);
            p.add(0, t);
            ans[i] = j;
        }
        return ans;
    }

    @Test
    public void testd() {
        int[] te = new int[]{3, 1, 2, 1};
        int[] ans = processQueries(te, 5);
        System.out.println(JSON.toJSONString(ans));
    }

//    public int countVowelStrings(int n) {
//  动态规划有点难
//    }

    /**
     * 假的正则匹配
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        if (s == null && p == null) {
            return true;
        }
        if ((p == null && s != null) || (s == null && p != null)) {
            return false;
        }
        int j = 0, i = 0;
        while (i < s.length() && j < p.length()) {
            if (p.charAt(j) == s.charAt(i)) {
                i++;
                j++;
                continue;
            }
            if (p.charAt(j) == '.') {
                i++;
                j++;
            } else if (p.charAt(j) == '*') {
                if (p.charAt(j - 1) == '.') {
                    if (s.charAt(i - 1) == s.charAt(i) && i == s.length() - 1) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (p.charAt(j - 1) == s.charAt(i)) {
                    i++;
                } else {
                    //aab c*a*b 这个应该是true的
                    if (j < p.length() - 1 && (s.charAt(i) == p.charAt(j + 1) || p.charAt(j + 1) == '.')) {
                        j += 2;
                    } else {
                        return false;
                    }
                    i++;
                }
            } else if (p.charAt(j) != s.charAt(i)) {
                j++;
            }
        }
        return j == p.length();
    }

    @Test
    public void test56() {
        System.out.println(isMatch("ab",
                ".*c"));

    }

    public boolean isMatch1(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        boolean match = !s.isEmpty() && ((s.charAt(0) == p.charAt(0)) || p.charAt(0) == '.');
        //*是匹配任意字符了而不是他前面的了
        if (p.length() >= 2 && p.charAt(1) == '*')
            return isMatch(s, p.substring(2)) || (match && isMatch(s.substring(1), p));
        //匹配下一个
        return match && isMatch(s.substring(1), p.substring(1));
    }

    int[][] mem;

    public boolean isMatchChar(char[] s, int s1, char[] p, int p1) {
        if (p1 >= p.length) return s1 >= s.length;
        if (mem[s1][p1] != 0) return mem[s1][p1] > 0;
        boolean match = s1 < s.length && ((s[s1] == p[p1]) || p[p1] == '.');
        if (p.length - p1 >= 2 && p[p1 + 1] == '*') {
            boolean t = isMatchChar(s, s1, p, p1 + 2) || (match && isMatchChar(s, s1 + 1, p, p1));
            //保存递归结果，怎么看不出来。。。。
            if (t) mem[s1][p1] = 1;
            else mem[s1][p1] = -1;
            return t;
        }
        boolean t = match && isMatchChar(s, s1 + 1, p, p1 + 1);
        if (t) mem[s1][p1] = 1;
        else mem[s1][p1] = -1;
        return t;
    }

    /**
     * 改进的
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch3(String s, String p) {
        this.mem = new int[s.length() + 1][p.length() + 1];
        char[] ss = s.toCharArray(), pp = p.toCharArray();
        return isMatchChar(ss, 0, pp, 0);
    }

    /**
     * 134. 加油站
     * 加油站加的油要大于消耗的，写个环形队列。。
     * 就是遍历，从失败的地方重新开始，索引使用取余
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int i = 0;
        while (i < n) {
            int useGas = 0;
            int costGas = 0;
            int count = 0;
            while (count < n) {
                i = (i + count) % n;
                //双重循环有点慢
                useGas += gas[i];
                costGas += cost[i];
                if (useGas - costGas < 0) {
                    break;
                }
                count++;
            }
            if (count == n) {
                return i;
            } else {
                i = i + count + 1;
            }
        }
        return -1;
    }

    public int strToInt(String str) {
        StringBuilder sb = new StringBuilder();
        str = str.trim();
        int n = str.length();
        if (n == 0) {
            return 0;
        }
        boolean neg = false;
        int i = 0;
        if (str.charAt(0) == '-') {
            neg = true;
            i = 1;
        } else if (str.charAt(0) == '+') {
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
        //这里设置了一个边界。。。。。
        int bndry = Integer.MAX_VALUE / 10;
        int sum = 0;
        //这个地方错了
        for (int j = str.length() - 1; j >= 0; j--) {
            //溢出怎么处理
            if (sum > bndry || sum == bndry) {
                return neg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            sum += (str.charAt(j) - '0') * i;
            i *= 10;
        }
        System.out.println(sum);
        return neg ? -sum : sum;
    }

    @Test
    public void test89() {
        System.out.println(strToInt("2147483648"));
    }

    public int[] singleNumber(int[] nums) {
        Map<Integer, Integer> hash = new HashMap<>();
        for (int x : nums) {
            hash.merge(x, 1, Integer::sum);
        }
        int[] ans = new int[2];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : hash.entrySet()) {
            if (entry.getValue() == 1) {
                ans[i++] = entry.getKey();
            }
        }
        return ans;
    }

    /**
     * 任何数和 00 做异或运算，结果仍然是原来的数，即 a \oplus 0=aa⊕0=a。
     * 任何数和其自身做异或运算，结果是 00，即 a \oplus a=0a⊕a=0。
     * 异或运算满足交换律和结合律，即 a \oplus b \oplus a=b \oplus a \oplus a=b \oplus (a \oplus a)=b \oplus0=ba⊕b⊕a=b⊕a⊕a=b⊕(a⊕a)=b⊕0=b。
     *
     * @param nums
     * @return
     */
    public int singleNumber2(int[] nums) {
        int res = 0;
        for (int n : nums) {
            res ^= n;
        }
        return res;
    }

    /**
     * 无重复子集
     *
     * @param nums
     * @return
     */
    LinkedList<Integer> sub = new LinkedList<>();
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> subsets1(int[] nums) {
        int n = nums.length;
        ans.add(sub);
        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
            sub = new LinkedList<>();
            getSub(i + 1, nums, 0, sub);
//            }
        }
        return ans;
    }

    public void getSub(int n, int[] nums, int i, LinkedList<Integer> tmp) {
        if (tmp.size() == n) {
            ans.add(new ArrayList<>(tmp));
            return;
        }
        for (int j = i; j < nums.length; j++) {
            tmp.addLast(nums[j]);
            getSub(n, nums, j + 1, tmp);
            tmp.removeLast();
        }
    }

    @Test
    public void test90() {
        int[] t = new int[]{1, 2, 3};
        List<List<Integer>> ans = subsets1(t);
        System.out.println(JSON.toJSONString(ans));
    }

    /**
     * 秋叶收藏集
     * 采用一个二维数组，分别存前面的，中间的，最右边的
     *
     * @param leaves
     * @return
     */
    public int minimumOperations(String leaves) {
        int len = leaves.length();
        char[] leave = leaves.toCharArray();
        int[][] dp = new int[len][3];
        //如果第一个是y需要改成r，增加一步
        dp[0][0] = leave[0] == 'y' ? 1 : 0;
        //置成无效
        dp[0][1] = dp[0][2] = dp[1][2] = Integer.MAX_VALUE;
        int isYellow = 0;//判断 当前叶子是不是黄色
        for (int i = 1; i < len; i++) {
            isYellow = leave[i] == 'y' ? 1 : 0;
            dp[i][0] = dp[i - 1][0] + isYellow;
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1] + (1 - isYellow));
            if (i > 1) {
                dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][2] + isYellow);
            }
        }
        return dp[len - 1][2];
    }

    /**
     * 最大矩形
     * 我这样写不一定对
     *  
     *
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] mat = new int[m + 1][n + 1];
        if (matrix[0][0] == '1') {
            mat[0][0] = 1;
        }
        int max = 0;
        int isOne = 0;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                isOne = matrix[i][j] == '1' ? 1 : 0;
                if (matrix[i][j - 1] == '1' && matrix[i - 1][j] == '1') {
                    if (isOne == 1) {
                        mat[i][j] = mat[i - 1][j - 1] + 1;
                    } else {
                        mat[i][j] = 0;
                    }
                }
                max = Math.max(max, mat[i][j]);
            }
        }
        return max;
    }

}

