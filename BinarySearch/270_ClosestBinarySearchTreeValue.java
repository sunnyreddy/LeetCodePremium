/*

Given the root of a binary search tree and a target value, return the value in the BST that is closest to the target

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
    public int closestValue(TreeNode root, double target) {
        int closest = -1;
        double minDiff = Double.MAX_VALUE;
        while (root != null) {
            if (Math.abs(root.val - target) < minDiff) {
                minDiff = Math.abs(root.val - target);
                closest = root.val;
            }
            
            if (root.val < target) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return closest;
    }
}