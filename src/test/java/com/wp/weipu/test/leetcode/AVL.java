package com.wp.weipu.test.leetcode;

/**
 * AVL平衡二叉树
 */
public class AVL {
    public Node insert(Node root, int data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }
        if (root.data == data) {
            root.count++;
            return root;
        }
        if (root.data >= data) {
            root.left = insert(root.left, data);
        } else {
            root.right = insert(root.right, data);
        }
        //更新root节点的高度
        root.height = Math.max(getHeight(root.right), getHeight(root.left)) + 1;
        //获取平衡因子
        int balance = getBalance(root);
        //更具平衡因子，调整参数
        if (balance > 1 && data < root.left.data) {
            //LL形
            return rightRotate(root);
        } else if (balance < -1 && data > root.right.data) {
            //RR形
            return leftRotate(root);
        }
        //LR形
        if (balance > 1 && data > root.left.data) {
            //先左旋
            root.left = leftRotate(root.left);
            //在右旋
            return rightRotate(root);
        } else if (balance < -1 && data < root.right.data) {
            //RL
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        return root;  
    }

    /**
     * 左旋转
     *
     * @param root
     * @return
     */
    public Node leftRotate(Node root) {
        Node tmp = root;
        root = root.right;
        tmp.right = root.left;
        root.left = tmp;
        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        tmp.height = Math.max(getHeight(tmp.right), getHeight(tmp.left)) + 1;
        return root;
    }

    /**
     * 右旋转
     *
     * @param root
     * @return
     */
    public Node rightRotate(Node root) {
        Node tmp = root;
        root = root.left;
        tmp.left = root.right;
        root.right = tmp;
        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        tmp.height = Math.max(getHeight(tmp.left), getHeight(tmp.right)) + 1;
        return root;
    }

    /**
     * 获取平衡因子，平衡因子通过计算得到比较好
     *
     * @param root
     * @return
     */
    public int getBalance(Node root) {
        if (root == null) {
            return 0;
        }
        return getHeight(root.left) - getHeight(root.right);
    }

    /**
     * 获取子树的高度
     *
     * @param root
     * @return
     */
    public int getHeight(Node root) {
        if (root == null) {
            return 0;
        } else {
            return root.height;
        }
    }
//    public int getHeight(Node root) {
//        if (root.left == null && root.right == null) {
//            return 1;
//        }
//        int left = 0, right = 0;
//        if (root.left != null) {
//            left += getHeight(root.left);
//        }
//        if (root.right != null) {
//            right += getHeight(root.right);
//        }
//        return Math.max(left, right);
//    }
}

/**
 * AVL树的数据结构
 */
class Node {
    int data;
    Node left, right;
    //平衡因子是哪个？
    int height, count;

    public Node(int data) {
        this.data = data;
        this.left = this.right = null;
        this.height = this.count = 1;
    }
}
