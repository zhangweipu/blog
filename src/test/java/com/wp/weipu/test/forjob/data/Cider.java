package com.wp.weipu.test.forjob.data;

import org.junit.Test;

import java.util.*;

public class Cider {

    //1 2 2 4

    public ListNode removeDupNode(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode pre = new ListNode(-1), preHead = pre;
        pre.next = head;
        ListNode cur = head;
        while (cur!=null) {
            if (cur.next != null && cur.val == cur.next.val) {
                while (cur.next!= null && cur.next.val == cur.val) {
                    cur = cur.next;
                }
                pre.next = cur.next;
                cur = cur.next;
                pre= pre.next;
            } else {
                pre = pre.next;
                cur = cur.next;
            }
        }
        return preHead.next;
    }
    @Test
    public void test(){
        ListNode head1= new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(2);
        ListNode head4 = new ListNode(3);
        ListNode head5 = new ListNode(4);
        head1.next = head2;
        head2.next= head3;
        head3.next = head4;
        head4.next = head5;
        ListNode res = removeDupNode(head1);
        while (res.next!=null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i=0;i<arr1.length;i++) {
            if (map.containsKey(arr1[i])) {
                map.put(arr1[i],map.get(arr1[i]) + 1);
            } else{
                map.put(arr1[i],1);
            }
        }
        int[] res = new int[arr1.length];
        int index =0;
        for (int i=0;i< arr2.length;i++) {
            int j = map.get(arr2[i]);
            for (int l =0;l < j;l++) {
                res[index++] = arr2[i];
            }
            map.remove(arr2[i]);
        }

        for (Integer k:map.keySet()) {
            int j = map.get(k);
            for (int l =0;l < j;l++) {
                res[index++] = k;
            }
        }
        return res;
    }

    @Test
    public void test2(){
        int[] arr1 = new int[]{2,3,1,3,2,4,6,7,9,2,19};
        int[] arr2 = new int[]{2,1,4,3,9,6};
        relativeSortArray(arr1,arr2);
        insertSort(arr2);
        for (int i=0;i<arr2.length;i++) {
            System.out.println(arr2[i]);
        }
    }

    public void insertSort(int[] arr) {
        int n = arr.length;

        for(int i = 1; i<n; i++) {
            int preIndex = i-1;
            int cur = arr[i];
            while (preIndex>=0 && arr[preIndex] > cur) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex+1] = cur;
        }
    }

    public int trap(int[] height) {
        if(height.length <=2) {
            return 0;
        }
        int maxLeft = height[0], maxRight = height[height.length -1];
        int left =1, right = height.length-2;
        int sum =0;
        while(left <= right) {
            while(left < right && maxLeft<=height[left]) {
                maxLeft = height[left];
                left++;
            }
            while(left < right && maxRight <= height[right]) {
                maxRight = height[right];
                right--;
            }
            if(left<=right) {
                if(maxLeft <= maxRight) {
                    sum+=maxLeft - height[left];
                    left++;
                } else {
                    sum+=maxRight-height[right];
                    right--;
                }
            }
        }
        return sum;
    }

    @Test
    public void test4(){
        Integer[] n = new Integer[] {4,2,0,3,2,5};
        Arrays.sort(n, Collections.reverseOrder());
        long l = Arrays.stream(n).count();
        for (int i=0;i<n.length;i++) {
            System.out.print(i +"," + l);
        }
    }

//    public String decodeString(String s) {
//        Stack<Character> stack = new Stack<>();
//        StringBuilder sb = new StringBuilder();
//        for (int i=0;i<s.length();i++) {
//            StringBuilder sb1= new StringBuilder();
//            if(isDigit(s.charAt(i)) || isNum(s.charAt(i)) || s.charAt(i) == '[') {
//                stack.push(s.charAt(i));
//            } else if (s.charAt(i) == ']'){
//                while (isDigit(stack.peek())) {
//                    sb1.append(stack.pop());
//                }
//                int num = 0;
//                while (isNum(stack.peek())) {
//                    num= num * 10 + s.charAt(i) - '0';
//                 }
//
//                    String a = sb1.toString();
//                    for(int j=1;j<num;j++) {
//                        sb1.append(a);
//                    }
//                }
//                sb.append(sb1);
//            }
//        }
//
//    }

    private boolean isDigit(char a) {
        return a >= 'a' && a <='z';
    }

    private boolean isNum(char b) {
        return b>='1' && b<='9';
    }

    class LRUCache {

        int capacity;
        LinkedHashMap<Integer, Integer> map;

        public LRUCache(int capacity) {
            this.map= new LinkedHashMap<>();
            this.capacity = capacity;
        }

        public int get(int key) {
            int res = -1;
            if(map.containsKey(key)) {
                res = map.get(key);
                map.remove(key);
                map.put(res, res);
            }
            return res;
        }

        public void put(int key, int value) {

        }
    }

    public int maxNumByChangeOnce(int num) {

        List<Integer> numList = new ArrayList<>();
        int numTmp = num;
        while(numTmp > 0){
            int curNum = numTmp % 10;
            numList.add(curNum);
            numTmp = numTmp/10;
        }
        Map<Integer, Integer> map = new HashMap<>();
        Integer[] sortNums = new Integer[numList.size()];
        for(int i=numList.size() - 1;i >=0 ;i--){
            map.put(numList.get(i), i);
            sortNums[i] = numList.get(i);
        }
        Arrays.sort(sortNums);
        for (int i = sortNums.length - 1;i >=0; i--) {
            if(sortNums[i] != numList.get(i)) {
                int index = map.get(sortNums[i]);
                int tmp=numList.get(i);
                numList.set(i, sortNums[i]);
                numList.set(index,tmp);
                break;
            }
        }

        int ans = 0;
        for (int j = numList.size()-1; j>=0; j--) {
            ans = ans * 10 + numList.get(j);
        }
        return ans;
    }

    @Test
    public void tes5(){
        System.out.println(maxNumByChangeOnce(991234));

    }

}
