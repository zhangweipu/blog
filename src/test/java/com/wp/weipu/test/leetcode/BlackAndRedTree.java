package com.wp.weipu.test.leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 红黑树的实现
 */

public class BlackAndRedTree<k, v> {
    BlackAndRedTree<k, v> parent;
    BlackAndRedTree<k, v> left;
    BlackAndRedTree<k, v> right;
    //这个变量我没理解
    BlackAndRedTree<k, v> prev;
    boolean red;

    /**
     * 遍历树
     * @return
     */
    BlackAndRedTree<k, v> root() {
        // p就直接生命变量了
        for (BlackAndRedTree<k, v> r = this, p; ; ) {
            if ((p = r.parent) == null) {
                return r;
            }
            r = p;
        }
    }
}
