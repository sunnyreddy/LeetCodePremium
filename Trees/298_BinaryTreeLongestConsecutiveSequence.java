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
// max = 1
// List [3, ]
// go left go right
// 3
// go left and right
// 
class Solution {
    private int ans = 0;
    public int longestConsecutive(TreeNode root) {
        List<Integer> curr = new ArrayList<>();
        dfs(root, curr);
        return ans;
    }
    
    public void dfs(TreeNode root, List<Integer> curr) {
        if (root == null)
            return;
        
        if (curr.size() == 0 || curr.get(curr.size() - 1) + 1 == root.val) {
            curr.add(root.val);
        } else {
            curr = new ArrayList<>();
            curr.add(root.val);
        }
        ans = Math.max(ans, curr.size());
        dfs(root.left, curr);
        dfs(root.right, curr);
        curr.remove(curr.size() - 1);
    }
}

class Solution {
    int ans = 0;
    public int longestConsecutive(TreeNode root) {
        dfs(root, 1);
        return ans;
    }
    
    public void dfs(TreeNode node, int len) {
        // base case
        if (node == null)
            return;
        ans = Math.max(ans, len);
        
        if (node.left != null && node.left.val == node.val + 1) {
            dfs(node.left, len + 1);
        } else {
            dfs(node.left, 1);
        }
        
        if (node.right != null && node.right.val == node.val + 1) {
            dfs(node.right, len + 1);
        } else {
            dfs(node.right, 1);
        }
    }
}