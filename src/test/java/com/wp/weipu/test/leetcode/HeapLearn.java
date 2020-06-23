package com.wp.weipu.test.leetcode;

/**
 * 堆的相关
 */
public class HeapLearn {

    /**
     * 调换次序
     *
     * @param arr
     * @param low
     * @param high
     */
    public void shift(int[] arr, int low, int high) {
        //确定父子
        int i = low, j = 2 * low;
        //最后归位
        int tmp = arr[i];
        while (j <= high) {
//            int tmp = arr[i];
            //找到较大的孩子做交换
            if (j + 1 <= high && arr[j] < arr[j + 1]) {
                j++;
            }
            if (arr[i] < arr[j]) {
                arr[i] = arr[j];
//                arr[j] = tmp;
                i = j;
                j = i * 2;
            }
        }
        //最后归位
        arr[i] = tmp;
    }

    public void heapSort(int[] arr, int len) {
        //计算方便都从1开始
        for (int i = len / 2; i >= 1; i--) {
            shift(arr, i, len);
        }
        for (int i = len; i >= 2; i--) {
            int tmp = arr[1];
            arr[1] = arr[i];
            //在这里排序
            arr[i] = tmp;
            shift(arr, 1, i - 1);
        }
    }
}
