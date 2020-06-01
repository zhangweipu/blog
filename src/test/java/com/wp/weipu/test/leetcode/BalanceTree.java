package com.wp.weipu.test.leetcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树相关
 */
public class BalanceTree {


    public boolean res;

    /**
     * 从顶向下
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {

        if (root == null) {
            return true;
        }
        if (Math.abs(height(root.left) - height(root.right)) > 1) {
            return false;
        } else {
            return isBalanced(root.right) && isBalanced(root.left);
        }
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(height(root.left), height(root.right));
    }

    /**
     * 从底向上
     *
     * @param root
     * @return
     */
    public int balanced(TreeNode root) {

        if (root == null) {
            return 0;
        }
        int left = balanced(root.left);
        int right = balanced(root.right);
        if (Math.abs(left - right) > 1) {
            this.res = false;
        }
        return 1 + Math.max(left, right);
    }

    /**
     * 判断二叉树是不是对称的
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return check(root.right, root.left);
    }

    /**
     * 使用镜像的方式
     *
     * @param p
     * @param q
     * @return
     */
    public boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 二叉树先序遍历
     *
     * @param root
     */
    public void preOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode tmp;
        if (root != null) {
            stack.push(root);
            while (!stack.empty()) {
                tmp = stack.pop();
                // 根的时候就打印
                System.out.println(tmp.val);
                while (tmp.right != null) {
                    stack.push(tmp.right);
                }
                while (tmp.left != null) {
                    stack.push(tmp.left);
                }
            }
        }
    }

    /**
     * 中序遍历
     *
     * @param root
     */
    public void inOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode tmp;
        if (root != null) {
            // 这里不先进栈
            tmp = root;
            while (tmp != null || !stack.empty()) {
                while (tmp != null) {
                    stack.push(tmp);
                    tmp = tmp.left;
                }
                //所有的左孩子处理完成
                if (!stack.empty()) {
                    tmp = stack.pop();
                    //处理完所有做孩子之后
                    System.out.println(tmp.val);
                    tmp = tmp.right;
                }
            }
        }
    }

    /**
     * 后序遍历，需要一个标志，表示右孩子访问了
     *
     * @param root
     */
    public void postOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode tmp;
        boolean flag;
        if (root != null) {
            do {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                tmp = null;
                flag = true;
                while (!stack.empty() && flag) {
                    //和lastElement有点不清晰
                    root = stack.firstElement();
                    // 最左的孩子
                    if (root.right == tmp) {
                        // 如果右孩子访问过了，则可以访问根
                        System.out.println(root.val);
                        stack.pop();
                        tmp = root;
                    } else {
                        root = root.right;
                        flag = false;
                    }
                }
            } while (!stack.empty());
        }
    }

    /**
     * 从叶到根的所有路径
     *
     * @param root
     */
    public void allPath(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode tmp;
        if (root != null) {
            do {
                // 左子树进栈
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                tmp = null;
                boolean flag = true;
                while (!stack.empty() && flag) {
                    root = stack.firstElement();
                    if (root.right == tmp) {
                        if (root.left == null && root.right == null) {
                            // 取路径
                            for (int i = stack.size() - 1; i >= 0; i--) {
                                System.out.println(stack.get(i).val);
                            }
                        }
                        stack.pop();
                        //右孩子访问过的标志
                        tmp = root;
                    }else {
                        root=root.right;
                        flag=false;
                    }
                }

            } while (!stack.empty());
        }
    }

    /**
     * 层次遍历。。环形队列
     * @param root
     */
    public void levelOrder(TreeNode root){
        Queue<TreeNode> queue=new LinkedList<>();
        TreeNode head;
        queue.add(root);
        while (!queue.isEmpty()){
            head=queue.poll();
            System.out.println(head.val);
            if (head.left!=null){
                queue.add(head.left);
            }
            if (head.right!=null){
                queue.add(head.right);
            }
        }
    }
}
