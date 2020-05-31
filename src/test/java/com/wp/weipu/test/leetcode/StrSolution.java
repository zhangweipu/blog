package com.wp.weipu.test.leetcode;

import org.junit.Test;

import java.util.*;

public class StrSolution {

    public int strStr(String haystack, String needle) {
//        haystack = haystack.trim();
        if (needle.length() == 0) {
            return 0;
        }
//        haystack.indexOf(needle);
        int len = haystack.length();
        int n = 0, nLen = needle.length();
        for (int i = 0; i < len; i++) {
            if (n < nLen) {
                if (haystack.charAt(i) == needle.charAt(n)) {
                    n++;
                } else {
                    i = i - n;
                    n = 0;
                }
                if (n == nLen) {
                    return i - n + 1;
                }
            }
        }
        return -1;
    }

    @Test
    public void test() {
        String haystack = "mississippi";
        String needle = "issip";
        int i = strStr(haystack, needle);
        System.out.println(i);
    }

    public List<Integer> findSubstring(String s, String[] words) {
        int size = words.length;
        int step = words[0].length();
        if (s.length() < size * step) {
            return new ArrayList<>();
        }

        HashMap<String, Integer> wordsMap = new HashMap<>();
        //计数
        int num = size;

        for (String word : words) {
            wordsMap.merge(word, 1, Integer::sum);
        }
        int wordLen = s.length();
        //什么时候使用这深度复制的值
        //存放起始位置
        List<Integer> res = new ArrayList<>();
        HashMap<String, Integer> wordsMapTmp = (HashMap<String, Integer>) wordsMap.clone();
        for (int i = 0; i < wordLen; i++) {
            if (i + step > wordLen - 1) {
                break;
            }
            String sub = s.substring(i, i + step);

            if (wordsMapTmp.get(sub) != null) {
                if (wordsMapTmp.get(sub) > 1) {
                    wordsMapTmp.merge(sub, -1, Integer::sum);
                    i += (step - 1);
                    num -= 1;
                } else if (wordsMapTmp.get(sub) == 1) {
                    wordsMapTmp.remove(sub);
                    i += (step - 1);
                    num -= 1;
                } else {
                    i += 1;
                    wordsMapTmp = (HashMap<String, Integer>) wordsMap.clone();
                    num = size;
                }
                if (wordsMapTmp.size() == 0) {
                    int begin = i - size * step + 1;
                    res.add(begin);
                    // 大于就继续
                    if (wordLen - begin > size * step) {
                        i = begin + 1;
                    }
                    wordsMapTmp = (HashMap<String, Integer>) wordsMap.clone();
                    num = size;
                }
            } else if (num < size) {
                int start = size - num;
                i = i - start * step + 1;
            }
        }
        return res;
    }

    @Test
    public void test1() {
        String s = "wordgoodgoodgoodbestword";
        String[] words = {"word", "good", "best", "good"};
        findSubstring(s, words);
    }

    public boolean canJump(int[] nums) {
        int len = nums.length;
        int max = 0;
        for (int i = 0; i < len - 1; i++) {
            if (i <= max) {
                max = Math.max(max, i + nums[i]);
                if (max >= len - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 在s1中删除一些字符，
     * 这个题我还不会来着
     *
     * @param s1
     * @param n1
     * @param s2
     * @param n2
     * @return
     */
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        return 1;
    }

    @Test
    public void repeatTest() {
        String pat = "adbbaanncc";
        String p = "ab";
        int index = pat.indexOf("b");
        System.out.print(index);
    }

    public int numberOfSubarrays(int[] nums, int k) {
        int length = nums.length;
        int index = 0, size = 0, number = 0;
        for (int i = 0; i < length; i++) {
            if (isOdd(i)) {
                if (size == 0) {
                    index = i;
                }
                size++;
                if (size == k) {
                    int tmpIndex = index;
                    int num1 = 0;
                    int num2 = 0;
                    //向前收缩
                    while (index >= 0) {
                        //碰到奇数就停止
                        if (isOdd(nums[i])) {
                            break;
                        }
                        num1++;
                        index--;
                    }
                    while (i < length) {
                        if (isOdd(nums[i])) {
                            break;
                        }
                        num2++;
                        i++;
                    }
                    if (num1 != 0 && num2 != 0) {
                        number += num1 * num2;
                    } else {
                        number = number + num1 + num2;
                    }
                    i = tmpIndex;
                    size = 0;
                }
            }
        }

        return number;
    }

    public boolean isOdd(int num) {
        if (num / 2 == 0) {
            return true;
        }
        return false;
    }

    @Test
    public void oddTest() {
        if (isOdd(2)) {
            System.out.println("odd");
        }
    }

    public int waysToChange(int n) {
        //就像是找零钱
        int[] number = {25, 10, 5, 1};
        //还要减去重复的。。。。
        return 0;
    }

    public void wayToChange(int n, int count, int[] number) {
        if (n == 0) {
            count++;
        }

        for (int i = 0; i < number.length; i++) {
            if (n % number[i] == 0) {
                count++;
            }
            int left = n - number[i];
            wayToChange(left, count, number);
        }
    }

    public void mergeSort(int[] nums){

    }
}

