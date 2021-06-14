/**
 * Problem statement
 * In a binary tree, a lonely node is a node that is the only child of its parent node. 
 * The root of the tree is not lonely because it does not have a parent node.
 * Given the root of a binary tree, return an array containing the values of all lonely nodes in the tree. 
 * Return the list in any order.
 * 
    Input: root = [1,2,3,null,4]
    Output: [4]
    Explanation: Light blue node is the only lonely node.
    Node 1 is the root and is not lonely.
    Nodes 2 and 3 have the same parent and are not lonely.
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
    List<Integer> lonely;
    public List<Integer> getLonelyNodes(TreeNode root) {
        lonely = new ArrayList<>();
        getLonely(root);
        return lonely;
    }
    
    public void getLonely(TreeNode root) {
        if(root == null)
            return;
        
        if(root.left == null && root.right != null) {
            lonely.add(root.right.val);
        }
        if(root.left != null && root.right == null) {
            lonely.add(root.left.val); 
        } 
        getLonely(root.left);
        getLonely(root.right);
    }
}