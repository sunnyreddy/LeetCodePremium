/**
 * Given the root of a binary tree, collect a tree's nodes as if you were doing this:

Collect all the leaf nodes.
Remove all the leaf nodes.
Repeat until the tree is empty
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
    List<List<Integer>> ans;
    public List<List<Integer>> findLeaves(TreeNode root) {
        this.ans = new ArrayList<>();
        getHeight(root);
        return ans;
    }
    
    public int getHeight(TreeNode root) {
        if (root == null)
            return 0;
        
        int leftH = getHeight(root.left);
        int rightH = getHeight(root.right);
        int currHeight = 1 + Math.max(leftH, rightH);
        if (currHeight - 1 == this.ans.size()) {
            this.ans.add(new ArrayList<>());
        }
        this.ans.get(currHeight - 1).add(root.val);
        return currHeight;
    }
}