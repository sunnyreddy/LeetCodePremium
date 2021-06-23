/*

Given the root of a binary tree and a node u in the tree, return the nearest node on the same level 
that is to the right of u, or return null if u is the rightmost node in its level.

*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    public TreeNode findNearestRightNode(TreeNode root, TreeNode u) {
        if (root == null || u == null)
            return null;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeNode prev = null;
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (curr.val == u.val)
                    return prev;
                else
                    prev = curr;
                if (curr.right != null)
                    queue.offer(curr.right);
                if (curr.left != null)
                    queue.offer(curr.left);
            }
        }
        return null;
    }
}
