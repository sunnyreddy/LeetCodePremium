/**
 * Given the root of a binary tree, return the length of the longest consecutive path in the tree.

A consecutive path is a path where the values of the consecutive nodes in the path differ by one. This path can 
be either increasing or decreasing.

For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid.
On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.
 * 
 * 
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
    private int ans;
    public int longestConsecutive(TreeNode root) {
        ans = 0;
        if (root == null)
            return ans;
        longestPath(root);
        return ans;
    }
    
    // incCount, decCount
    public int[] longestPath(TreeNode node) {
        // base case
        if (node == null)
            return new int[2];
        
        int[] nodeInfo = new int[]{1, 1};
        if (node.left != null) {
            int[] leftInfo = longestPath(node.left);
            // means inc
            if (node.left.val == node.val + 1) {
                nodeInfo[0] = leftInfo[0] + 1;
            } // means dec 
            else if (node.left.val == node.val - 1) {
                nodeInfo[1] = leftInfo[1] + 1;
            }
        }
        if (node.right != null) {
            int[] rightInfo = longestPath(node.right);
            // means inc
            if (node.right.val == node.val + 1) {
                nodeInfo[0] = Math.max(nodeInfo[0], rightInfo[0] + 1);
            } // means dec 
            else if (node.right.val == node.val - 1) {
                nodeInfo[1] = Math.max(nodeInfo[1], rightInfo[1] + 1);
            }
        }
        
        // ans = Math.max(ans, nodeInfo[1]);
        // ans = Math.max(ans, nodeInfo[0]);
        ans = Math.max(ans, nodeInfo[1] + nodeInfo[0] - 1);
        return nodeInfo;
    }
}
