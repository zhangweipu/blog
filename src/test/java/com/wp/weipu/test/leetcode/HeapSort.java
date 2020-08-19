package com.wp.weipu.test.leetcode;

/**
 * 又见堆排序
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6};
    }

    public static void sort(int[] arr) {
        //构建初始堆,从最小的非叶子节点开始
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);
            adjustHeap(arr, 0, i);
        }
    }

    /**
     * 每次都是从最左节点开始遍历
     *
     * @param arr
     * @param i
     * @param len
     */
    public static void adjustHeap(int[] arr, int i, int len) {
        int tmp = arr[i];
        //都是从最左的节点开始
        for (int k = 2 * i + 1; k < len; k = k * 2 + 1) {
            if (k + 1 < len && arr[k + 1] > arr[k]) {
                //如果右边大，则指向右边
                k++;
            }
            //交换
            if (arr[k] > tmp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = tmp;
    }

    public static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}
