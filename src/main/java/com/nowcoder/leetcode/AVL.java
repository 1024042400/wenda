package com.nowcoder.leetcode;

public class AVL {

    class Node {
        boolean isBalance = true;
        TreeNode treeNode;
        int height;
        Node(TreeNode treeNode) {
            this.treeNode = treeNode;
        }
    }

    public boolean IsBalanced_Solution(TreeNode root) {
        if(root == null) {
            return true;
        }
        return getNode(root).isBalance;
    }

    private Node getNode(TreeNode root) {
        if(root == null ){
            return new Node(root);
        }
        Node left  = getNode(root.left);
        Node right = getNode(root.right);
        Node cur = new Node(root);
        cur.isBalance = left.isBalance && right.isBalance && (Math.abs(left.height - right.height) < 2);
        cur.height = Math.max(left.height,right.height) + 1;
        return cur;
    }

}
