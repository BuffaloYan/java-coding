package com.company.tree;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class ConstructTreePreOrder {

      public static class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode() {}
          TreeNode(int val) { this.val = val; }
          TreeNode(int val, TreeNode left, TreeNode right) {
              this.val = val;
              this.left = left;
              this.right = right;
          }
      }
     
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, TreeNode> map = new HashMap<>();
        for(int val: preorder) {
            map.put(val, new TreeNode(val));
        }

        Map<Integer, Integer> mapPreOrder = new HashMap<>();
        for(int i=0; i<preorder.length; i++) {
            mapPreOrder.put(preorder[i], i);
        }

        Map<Integer, Integer> mapInOrder = new HashMap<>();
        for(int i=0; i<inorder.length; i++) {
            mapInOrder.put(inorder[i], i);
        }


        return buildTree(map, mapPreOrder, mapInOrder, preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);

    }

    private TreeNode buildTree(Map<Integer, TreeNode> map, Map<Integer, Integer> mapPreOrder, Map<Integer, Integer> mapInOrder,
                                             int[] preorder, int l1, int r1, int[] inorder, int l2, int r2) {
        if(l1 > r1 || l2 > r2) {
            return null;
        }

        // first preorder element is the root
        TreeNode root = map.get(preorder[l1]);
        if (r1 == l1) {
            return root;
        }

        TreeNode left = buildTree(map, mapPreOrder, mapInOrder, preorder,
                l1+1,
                //preorder right
                l1+1 + mapInOrder.get(root.val)-1 - l2,
                inorder,
                l2,
                mapInOrder.get(root.val)-1
        );
        TreeNode right = buildTree(map, mapPreOrder, mapInOrder, preorder,
                l1+1 + mapInOrder.get(root.val)-1 - l2 +1,
                r1,
                inorder,
                mapInOrder.get(root.val)+1,
                r2
        );

        root.left = left;
        root.right = right;

        return root;

    }

    @Test
    public void testRun1() {
        ConstructTreePreOrder constructTreePreOrder = new ConstructTreePreOrder();
        int[] preorder = new int[]{3,9,20,15,7};
        int[] inorder = new int[]{9,3,15,20,7};

        constructTreePreOrder.buildTree(preorder, inorder);

    }
}
