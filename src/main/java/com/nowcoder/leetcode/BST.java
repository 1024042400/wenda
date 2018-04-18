package com.nowcoder.leetcode;

public class BST {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    class Node {
        TreeNode treeNode;
        boolean isBST = true;
        int max ; // 最大值
        int min ; // 最小值
        Node(TreeNode t) {
            treeNode = t;
            max = min = t.val;
        }
    }

    public boolean checkBST(TreeNode root) {
        if(root == null)
            return true;
        Node node = getNode(root);
        return node.isBST;
    }

    private Node getNode(TreeNode cur) {
        if(cur == null)
            return null;

        Node left = getNode(cur.left);
        Node right = getNode(cur.right);

        Node node = new Node(cur);
        if(left != null) {
            if(left.isBST == false){
                node.isBST = false;
                return node;
            }

            node.max = Math.max(cur.val,left.treeNode.val);
            node.min = Math.min(cur.val,left.treeNode.val);
        }

        if(right != null) {
            if(right.isBST == false){
                node.isBST = false;
                return node;
            }

            node.max = Math.max(cur.val,right.treeNode.val);
            node.min = Math.min(cur.val,right.treeNode.val);
        }

        if( (left != null &&  node.treeNode.val < left.max) ||
                (right != null && node.treeNode.val > right.min) ) {
            node.isBST = false;
        }
        return node;
    }

}
