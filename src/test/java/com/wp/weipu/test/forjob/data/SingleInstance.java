package com.wp.weipu.test.forjob.data;

import org.junit.Test;

import java.util.*;

public class SingleInstance {

    private static volatile SingleInstance sInstance;
    private static Object object = new Object();
    private SingleInstance(){}

    public static SingleInstance getInstance() {
        if (sInstance == null) {
            synchronized (SingleInstance.class) {
                if (sInstance == null) {
                    sInstance = new SingleInstance();
                }
            }
        }
        return sInstance;
    }
    public ListNode reverseList(ListNode root) {
        ListNode head = new ListNode(-1);
        ListNode pre = head.next;
        while (root != null) {
            ListNode tmp = root.next;
            root.next = pre;
            pre = root;
            root = tmp;
        }
        return head.next;
    }

    public void quickSort(int[] num, int left, int right){
        if (left >= right) {
            return;
        }
        int l = left, r = right;
        int mid = num[left];
        while (left < right) {
            while (left < right && mid <= num[right]) {
                right --;
            }
            while (left < right && mid >= num[left]) {
                left ++;
            }
            if (left < right) {
                int tmp = num[left];
                num[left] = num[right];
                num[right] = tmp;
            }
        }
        num[l] = num[left];
        num[left] = mid;

        quickSort(num, l, mid -1);
        quickSort(num, mid + 1, r);
    }
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if (map.containsKey(nums[i])) {
                return new int[] {map.get(nums[i]), i};
            } else {
                map.put((target - nums[i]), i);
            }
        }
        return new int[2];
    }

    public ListNode sortInList (ListNode head) {
        // write code here
        List<Integer> list = new ArrayList<>();
        ListNode head1= head;
        while(head!=null) {
            list.add(head.val);
            head= head.next;
        }

        Collections.sort(list, (o1, o2) -> o1 - o2);
        ListNode head2= head1;
        for (int ln : list) {
            head1.val = ln;
            head1= head1.next;
        }
        return head2;
    }


    public boolean isPail (ListNode head) {
        // write code here
        StringBuilder sb = new StringBuilder();
        while (head!=null) {
            sb.append(head.val);
            if (head.next != null) {
                sb.append(",");
            }
            head = head.next;
        }
        String[] strs = sb.toString().split(",");
        return isPail(strs);
    }

    private boolean isPail(String[] strs) {
        int i = 0, j = strs.length -1;
        while (i<j) {
            if (!strs[i].equals(strs[j])) {
                return false;
            }
            //这种一定要记得
            i++;
            j--;
        }
        return true;
    }



}
