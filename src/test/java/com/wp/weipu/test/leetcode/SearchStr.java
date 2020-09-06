package com.wp.weipu.test.leetcode;

/**
 * 搜索树相关,前缀树
 */
public class SearchStr {

    TreeNode root;

    public SearchStr() {
        root = new TreeNode(new TreeNode[26], 0, '-');
    }

    public TreeNode insert(String word) {
        TreeNode rootTmp = root;
        word = word.toLowerCase();
        char[] words = word.toCharArray();
        for (char w : words) {
            int index = w - 'a';
            if (rootTmp.children[index] == null) {
                rootTmp.children[index] = createNode(w);
            } else {
                rootTmp.children[index].num++;
            }
            rootTmp = rootTmp.children[index];
        }
        return root;
    }

    public String SearchCommonPre(String word) {
        char[] words = word.toLowerCase().toCharArray();
        StringBuilder sb = new StringBuilder();
        TreeNode tmp = root;
        for (char w : words) {
            int index = w - 'a';
            if (tmp.children[index] != null && tmp.children[index].num > 1) {
                sb.append(w);
                tmp = tmp.children[index];
            } else if (tmp.children[index] != null && tmp.children[index].num == 1) {
                sb.append(w);
                break;
            } else {
                break;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SearchStr searchStr = new SearchStr();
        String str = "goodmorning";
        String str2 = "goopmorning";
        searchStr.insert(str);
        searchStr.insert(str2);
//        System.out.println(searchStr.SearchCommonPre(str2));

        System.out.println((char) 145);
    }

    public TreeNode createNode(char word) {
        return new TreeNode(new TreeNode[26], 1, word);
    }

    class TreeNode {
        TreeNode[] children;
        int num;
        char word;

        public TreeNode(TreeNode[] children, int num, char word) {
            this.children = children;
            this.num = num;
            this.word = word;
        }

        public void setNumPlus() {
            this.num++;
        }
    }
}
