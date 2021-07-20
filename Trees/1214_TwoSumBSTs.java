/**
 * Given the roots of two binary search trees, root1 and root2, return true if and only if there is a node in the 
 * first tree and a node in the second tree whose values sum up to a given integer target.
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
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        while (root1 != null && root2 != null) {
            if (root1.val + root2.val == target) {
                return true;
            } else if (root1.val + root2.val > target) {
                return twoSumBSTs(root1.left, root2, target) || twoSumBSTs(root1, root2.left, target);
            } else {
                return twoSumBSTs(root1.right, root2, target) || twoSumBSTs(root1, root2.right, target);
            }
        }
        return false;
    }
}