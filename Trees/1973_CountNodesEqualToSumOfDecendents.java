/**
 * 
 * Given the root of a binary tree, return the number of nodes where the value of the node is equal to the sum of 
 * the values of its descendants.

A descendant of a node x is any node that is on the path from node x to some leaf node. The sum is considered to 
be 0 if the node has no descendants.

 
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
    int ans = 0;
    public int equalToDescendants(TreeNode root) {
        if (root == null)
            return 0;
        
        dfs(root);
        return ans;
    }
    
    public int dfs(TreeNode root) {
        // base case
        if (root == null) {
            return 0;
        }
        // init decendent val to 0
        int val = 0;
        // find decendents values
        val += dfs(root.left);
        val += dfs(root.right);
        // when we find a node that has val = all of it's decendents
        if (val == root.val) {
            ans++;
        }
        // return decendents + root val to parent
        return root.val + val;
    }
}