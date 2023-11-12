package com.wp.weipu.test.forjob;


import com.jcraft.jsch.ChannelSftp;
import com.wp.weipu.test.forjob.data.ListNode;
import com.wp.weipu.test.forjob.data.TreeNode;
import org.apache.http.util.TextUtils;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;

public class LeetCode {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if (map.containsKey(nums[i])) {
                return new int[]{map.get(nums[i]),i};
            }
            int key = target - nums[i];
            map.put(key, i);
        }
        for (Integer key : map.keySet()) {

        }
        return null;
    }

//    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//
//    }

    /**
     * 记录最左边的
     * @param s
     */
    public void lengthOfLongestSubstring(String s){
        if (s.length() == 1) {
            return;
        }
        Set<Integer> set = new HashSet<>();
        StringBuilder sb= new StringBuilder();
        sb.reverse();
        int left = 0, max = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i< s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                left = map.get(s.charAt(i)) + 1;
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - left +1);
        }
    }
    public String trans(String s, int n) {
        // write code here
        s.split(" ",-1 );
        StringBuilder sb = new StringBuilder();
        StringBuilder subStr = new StringBuilder();
        for(int i = n-1;i>=0;i--) {
            if (s.charAt(i) ==' ') {
                subStr.reverse();
                sb.append(subStr);
                sb.append(" ");
                subStr = new StringBuilder();
            } else {
                if(s.charAt(i)<='Z' && s.charAt(i)>='A') {
                    subStr.append((char)((int)s.charAt(i) + 32));
                } else {
                    subStr.append((char)((int)s.charAt(i) - 32));
                }
            }
        }
        if(subStr.length() > 0) {
            subStr.reverse();
            sb.append(subStr);
        }
        return sb.toString();
    }
    //快慢指针
    public ListNode reverseKGroup(ListNode head, int k) {
       ListNode fast = head, slow = head;
       ListNode tmp = new ListNode(-1, head);
       ListNode prevHead = tmp;
       while (fast != null) {
           int nodeNode = 0;
           for (; nodeNode < k && fast != null; nodeNode++) {
               fast = fast.next;
           }
           if (nodeNode == k) {
               ListNode subNode = reverseList(slow, fast);
               prevHead.next = subNode;
               prevHead = slow;
               slow = fast;
           }
       }
       return tmp.next;
    }

    public ListNode reverseList(ListNode head, ListNode tail) {
        ListNode curr = head, pre = null;
        while (curr != null && curr != tail) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }
    class Trie {
        public Trie[] children;
        public boolean isEnd;
        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        public void insert(String word) {
            Trie node = this;
            for (char i : word.toCharArray()) {
                int index = i - 'a';
                if (node.children[index] != null) {
                    node.children[index] = new Trie();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            Trie root = this;
            for (char i : word.toCharArray()) {
                int index = i -'a';
                if (root.children[index] == null) {
                    return false;
                }
                root = root.children[index];
            }
            return root.isEnd;
        }

        public boolean startsWith(String prefix) {
            Trie root = this;
            for (char i : prefix.toCharArray()) {
                int index = i -'a';
                if (root.children[index] == null) {
                    return !root.isEnd;
                }
                root = root.children[index];
            }
            return !root.isEnd;
        }
    }

    int max = 0;
    public int minSubArrayLen(int target, int[] nums) {
        for (int i= 0; i< nums.length; i++) {
            back(i, nums, target, 1);
        }
        return max;
    }

    public int minSubArrayLen2(int target, int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int i= 0; i< nums.length; i++) {
            int sum = 0;
            for (int j = i; j< nums.length; j++) {
                sum += nums[j];
                if (sum >= target) {
                    min = Math.min(min, j -i + 1);
                }
            }
        }
        return min == Integer.MAX_VALUE ? 0: min;
    }

    public void back(int index, int[] nums, int target, int len) {
        if (index >= nums.length || nums[index] > target) {
            return;
        }
        if (nums[index] == target) {
            max = Math.max(max, len);
        }
        target -= nums[index];
        index++;
        Queue<Integer> an = new LinkedList<>();

        back(index, nums, target, ++len);
    }

    public void text() {

    }

    public int[] searchRange(int[] nums, int target) {
        int leftIndex = binarySearch(nums, target);
        if (leftIndex>= nums.length || nums[leftIndex] != target) {
            return new int[]{-1,-1};
        }
        int rightIndex = binarySearch(nums, target + 1);
        return new int[] {leftIndex, rightIndex - 1};
    }

    public int binarySearch(int[] nums, int target) {
        int left = 0, right= nums.length-1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            //只查最左边的
            if (nums[mid] >= target) {
                right = mid -1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    //0,1,2原地排序,

    /**
     * 解1就是0和1分别排序一次
     * 解2就是遍历0放前面，2放后面
     * @param nums
     */
    public void sortColors(int[] nums) {
        int left = 0, right = nums.length -1;
        for (int i = 0; i < nums.length; i++) {
            while (i<=right && nums[i] == 2) {
                int temp = nums[right];
                nums[right] = nums[i];
                nums[i] = temp;
                right --;
            }
            if (nums[i] == 0) {
                int temp = nums[left];
                nums[left] = nums[i];
                nums[i] = temp;
                left ++;
            }
        }
    }

    public void exchange(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * 无重复全排列
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.stream(nums).sum();
        boolean[] used = new boolean[nums.length];
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        Arrays.sort(nums);
        trackBack(nums, used, cur, res);
        return res;
    }

    public void trackBack(int[] nums, boolean[] used, List<Integer> cur, List<List<Integer>> res) {

        if (cur.size() == nums.length) {
            res.add(cur);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if ((i > 0 && nums[i] == nums[i-1] && !used[i-1]) || used[i]) {
                continue;
            }
            cur.add(nums[i]);
            int index= cur.size() - 1;
            used[i] = true;
            trackBack(nums, used, cur, res);
            cur.remove(index);
            used[i] = false;
        }
    }

    public double myPow(double x, int n) {
        long N = n;
        return N > 0 ? quickMul(x, N) : 1.0/quickMul(x,N);
    }

    public double quickMul(double x, long N) {
        if (N == 0) {
            return 1;
        }
        double y = quickMul(x, N/2);
        return N%2== 0 ? y*y : y*y*x;
    }

    int MODE = 1337;

    public int pow(double a, int n) {
        int res  = 1;
        while (n!= 0) {
            if (n%2!=0) {
                res= (int) ((long) res * a % MODE);
            }
            a = (int) ((long) a * a %MODE);
            n/=2;
        }
        return res;
    }

    public int maxSubArray(int[] nums) {
        int max = 0, sum = 0;
        for (int a : nums) {
            if (sum > 0) {
                sum += a;
            } else {
                sum = a;
            }
            if (max < sum) {
                max = sum;
            }
        }
        return max;
    }

    /**
     * 顺时针螺旋顺序
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        List<Integer> res = new ArrayList<>();
        int count = m * n;
        int c = Math.min(m, n) / 2;
        int c1 = 0;
        while (c1 < c && count > 0) {
            for (int i = c1; i < n - c1 && count > 0; i++) {
                res.add(matrix[c1][i]);
                count--;
            }
            for (int i = c1 + 1; i < m - c1 && count > 0; i++) {
                res.add(matrix[i][n - 1 - c1]);
                count--;
            }

            for (int i = n - 2 -c1; i > c1 && count > 0; i--) {
                res.add(matrix[m-1-c1][i]);
                count--;
            }
            for (int i = m - 2 -c1; i > c1 && count > 0; i--) {
               res.add(matrix[i][c1]);
               count --;
            }
            c1 ++;
        }
        return res;
    }

    public String decodeMessage(String key, String message) {
        Map<Character, Character> decodeMap = new HashMap<>();
        char index = 'a';
        for(char ch : key.toCharArray()) {
            if (ch == ' ' || decodeMap.containsKey(ch)) {
                continue;
            }
            decodeMap.put(ch, index);
            index++;
        }
        decodeMap.put(' ',' ');
        StringBuilder sb = new StringBuilder();
        for (char ch : message.toCharArray()) {
            sb.append(decodeMap.get(ch));
        }
        return sb.toString();
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return res;
        }
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            //去重
            if (i > 0 && nums[i-1] == nums[i]) {
                continue;
            }
            //没有满足的
            if (nums[i] + nums[i+1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            //nums[i]不够大
            if (nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target){
                continue;
            }
            for (int j = i + 1; j < length - 2; j++) {
                //做个去重
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                if (nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) {
                    continue;
                }
                int left = j + 1, right = length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left+1]) {
                            left++;
                        }
                        left++;
                        while (left < right && nums[right] == nums[right -1]) {
                            right --;
                        }
                        right--;
                    } else if (sum < target) {
                        left ++;
                    } else {
                        right --;
                    }
                }
            }
        }
    return res;
    }

    /**
     *
     * @param n
     * @param redEdges
     * @param blueEdges
     * @return
     */
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        return null;
    }

    /**
     * 1.cho
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int j = n - 1;
        for (int i = n - 1; i > 0; i--) {
            j = i - 1;
            if (nums[i-1] < nums[i]) {
                break;
            }
        }
        if (j == 0) {
            Arrays.sort(nums);
            return;
        }
        int temp = nums[j];
        nums[j] = nums[n - 1];
        nums[n - 1] = temp;
        int right = n-1, left = j + 1;
        while (left < right) {
            int t = nums[left];
            nums[left] = nums[right];
            nums[right] = t;
            left ++;
            right --;
        }
    }
    public void nextPermutation2(int[] nums) {
        int len = nums.length;
        if(len<=1) return;

        for(int i=len-1;i>=1;i--){
            if(nums[i]>nums[i-1]){            //找到相邻升序
                for(int j=len-1;j>=i;j--){
                    if(nums[j]>nums[i-1]){    //找到最右边大于nums[i-1]的数，并交换
                        int tmp = nums[i-1];
                        nums[i-1] = nums[j];
                        nums[j] = tmp;
                        break;
                    }
                }
                Arrays.sort(nums,i,len);      //将后面降序变为升序
                return;
            }
        }
        Arrays.sort(nums);
    }
    @Test
    public void test(){
        int[] nums = new int[]{1,2,3};
        nextPermutation(nums);
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() +1];
        HashSet<String> wordSet = new HashSet<>(wordDict);
        for (int i = 1;i<= s.length();i++) {
            for (int j =0;j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j,i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    /**
     * 后序遍历
     * @param root
     * @return
     */
    public boolean evaluateTree(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root.val != 0;
        }
        boolean leftVal = evaluateTree(root.left);
        boolean rightVal = evaluateTree(root.right);
        int val = root.val;
        if (val == 2) {
            return leftVal || rightVal;
        } else {
            return leftVal && rightVal;
        }
    }

    /**
     * 需要写个乘法， 再写个加法
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if (Objects.equals(num1, "0") || Objects.equals(num2, "0")) {
            return "0";
        }
        String res = "";
        int n = num1.length(), m = num2.length(), add = 0;
        for (int i = n - 1; i >=0; i --) {
            StringBuilder sb = new StringBuilder();
            for (int j = n - 1;j > i; j --) {
                sb.append(0);
            }
            int a = num1.charAt(i) - '0';
            for (int j = m - 1;j >= 0; j --) {
                int b = num2.charAt(j) - '0';
                int mul = a * b + add;
                add = mul / 10;
                mul = mul % 10;
                sb.append(mul);
            }
            if (add != 0) {
                sb.append(add);
            }
            res = addTwoNum(sb.reverse().toString(), res);
        }
        return res;
    }

    private String addTwoNum(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int n = num1.length() - 1, m = num2.length() - 1, add = 0;
        while (n >= 0 || m >= 0 || add > 0) {
            int a = n >= 0 ? num1.charAt(n) : 0;
            int b = m >= 0 ? num2.charAt(m) : 0;
            int sum = a + b + add;
            sb.append(sum % 10);
            add = sum / 10;
            n--;
            m--;
        }
        return sb.reverse().toString();
    }

    @Test
    public void test2(){
        String a = "12", b = "2";
        System.out.println(multiply(a,b));
    }

    public List<String> alertNames(String[] keyName, String[] keyTime) {
        Map<String, List<Integer>> times = new HashMap<>();
        for (int i = 0; i < keyName.length; i++) {
            times.computeIfAbsent(keyName[i], v -> new ArrayList<>()).add(transToM(keyTime[i]));
        }
        List<String> ans = new ArrayList<>();
        for (String key : times.keySet()) {
            List<Integer> time = times.get(key);

            if (time.size() < 3) {
                continue;
            }
            for (int i = 0;i< time.size() -2;i++) {
                if (time.get(i + 2) - time.get(i) >= 60) {
                    ans.add(key);
                }
            }
        }
        Collections.sort(ans);
        return ans;
    }

    public int transToM(String time) {
            String[] data = time.split(":");
            int h = Integer.valueOf(data[0]);
            int m = Integer.valueOf(data[1]);
            return h * 60 + m;
    }

    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder);
        List<String> ans = new ArrayList<>();
        ans.add(folder[0]);
        for(int i = 1; i < folder.length; i++) {
            String pre = ans.get(ans.size() - 1);
            if (!(pre.length() < folder[i].length() && pre.equals(folder[i].substring(0, pre.length())) && folder[i].charAt(pre.length()) == '/')){
                ans.add(folder[i]);
            }
        }
        return ans;
    }

    public boolean isChild(String p, String c) {
        if (c.length() < p.length()) {
            return false;
        }
        int n = p.length();
        for (int i = 0;i < p.length(); i++) {
            if (p.charAt(i) != c.charAt(i)) {
                return false;
            }
        }
        return true;
     }

    @Test
    public void test3() {
        int[][] aa = new int[][]{{1,1},{2,2},{3,3}};
        System.out.println(aa.length);
    }

    public int compare (String version1, String version2) {
        // write code here
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        System.out.println(v1);
        int i = 0, v2Len = 0;
        while(i < v1.length && i < v2.length) {
            if(!v1[i].equals(v2[i])) {
                int a = Integer.valueOf(v1[i]);
                int b= Integer.valueOf(v2[i]);
                if(a>b) {
                    return 1;
                } else {
                    return -1;
                }
            }
            i++;
        }
        if(i==v1.length && i==v2.length) {
            return 0;
        }
        return i==v1.length ? -1:1;
    }
    @Test
    public void test4(){
        String a = "1.1", b="2.1";
        compare(a,b);
    }

    public int[] preorderTraversal (TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        List<Integer> ans = new ArrayList<>();
        while (stack.size() > 0) {
            root = stack.pop();
            ans.add(root.val);
            if(root.right !=null) {
                stack.push(root.right);
            }
            if (root.left!=null) {
                stack.push(root.left);
            }
        }
        return null;
    }

    public String transChar(String s) {
        StringBuilder sb = new StringBuilder();
        for(char ch : s.toCharArray()) {
            if(ch<'a' && ch>='A') {
                sb.append((char)((int)ch + 32));
            } else {
                sb.append((char)((int)ch - 32));
            }
        }
        return sb.toString();
    }
    @Test
    public void tes4(){
        System.out.println((int)'a');
        System.out.println((int)'A');
        System.out.println(transChar("aaAA"));
    }


    public ArrayList<ArrayList<Integer>> levelOrder (TreeNode root) {
        // write code here
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> res= new ArrayList<>();
            for (int i = 0;i<size;i++) {
                root = queue.poll();
                if (root.left != null) {
                    queue.add(root.left);
                }
                if (root.right!=null) {
                    queue.add(root.right);
                }
                res.add(root.val);
            }
            ans.add(res);
        }
        return ans;
    }

    public int minmumNumberOfHost (int n, int[][] startEnd) {
        // write code here
        Arrays.sort(startEnd, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            } else {
                return o1[0] - o2[0];
            }
        });

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int[] da : startEnd) {
            //如果最早结束的时间小于等于之后最早开始的时间，就不需要新主持人了。
            if (!queue.isEmpty() && queue.peek() <= da[0]) {
                queue.poll();
            }
            //结束时间
            queue.offer(da[1]);
        }
        return queue.size();
    }
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.add(node);
        while(stack1.size() >0 && stack2.size() == 0) {
            stack2.add(stack1.pop());
        }
    }

    public int pop() {
        while(stack2.size() == 0 && stack1.size()>0){
            stack2.add(stack1.pop());
        }
        return stack2.pop();
    }


}
