/**
 * Given the root of a binary tree and two integers p and q, return the distance between the nodes of value p 
 * and value q in the tree.

The distance between two nodes is the number of edges on the path from one to the other.

 
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
    Map<TreeNode, TreeNode> parents;
    int pathSize = 0;
    TreeNode pNode = null;
    // TreeNode qNode = null;
    public int findDistance(TreeNode root, int p, int q) {
        parents = new HashMap<>();
        Set<TreeNode> seen = new HashSet<>();
        populateParents(root, null, p, q);
        dfs(pNode, q, seen, new ArrayList<>());
        return pathSize;
    }
    
    public void dfs(TreeNode curr, int end, Set<TreeNode> seen, List<TreeNode> path) {
        if (curr == null || seen.contains(curr))
            return;
        
        path.add(curr);
        seen.add(curr);
        if (curr.val == end) {
            pathSize = path.size() - 1;
        }
        dfs(curr.left, end, seen, path);
        dfs(curr.right, end, seen, path);
        dfs(parents.get(curr), end, seen, path);
        path.remove(path.size() - 1);
    }
    
    public void populateParents(TreeNode root, TreeNode parent, int p, int q) {
        if (root == null)
            return;
        
        if (root.val == p) pNode = root;
        // if (root.val == q) qNode = root;
        parents.put(root, parent);
        populateParents(root.left, root, p, q);
        populateParents(root.right, root, p, q);
    }
}