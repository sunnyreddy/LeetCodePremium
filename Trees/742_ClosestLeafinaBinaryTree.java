/**
 * Given the root of a binary tree where every node has a unique value and a target integer k, return the value of 
 * the nearest leaf node to the target k in the tree.

Nearest to a leaf means the least number of edges traveled on the binary tree to reach any leaf of the tree. Also, 
a node is called a leaf if it has no children.
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
    Map<TreeNode, TreeNode> parents = new HashMap<>();
    int min = Integer.MAX_VALUE, ans = -1;
    public int findClosestLeaf(TreeNode root, int k) {
        buildParents(root, null);
        TreeNode target = search(root, k);
        
        dfs(target, 0, new HashSet<>());
        return ans;
    }
    
    public void dfs(TreeNode node, int distance, Set<TreeNode> seen) {
        if (node == null)
            return;
        
        if (seen.contains(node))
            return;
        
        seen.add(node);
        if (node.left == null && node.right == null) {
            if (distance < min) {
                min = distance;
                ans = node.val;
            }
        }
        
        dfs(parents.get(node), distance + 1, seen);
        dfs(node.left, distance + 1, seen);
        dfs(node.right, distance + 1, seen);
    }
    
    public TreeNode search(TreeNode root, int k) {
        if (root == null)
            return null;
        
        if (root.val == k)
            return root;
        
        TreeNode foundLeft = search(root.left, k);
        TreeNode foundRight = search(root.right, k);
        
        return foundLeft == null ? foundRight : foundLeft;
    }
    
    public void buildParents(TreeNode root, TreeNode parent) {
        if (root == null)
            return;
        
        parents.put(root, parent);
        buildParents(root.left, root);
        buildParents(root.right, root);
    }
}
