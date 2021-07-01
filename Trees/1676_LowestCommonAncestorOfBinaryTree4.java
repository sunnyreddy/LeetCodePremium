/**
 * Given the root of a binary tree and an array of TreeNode objects nodes, 
 * return the lowest common ancestor (LCA) of all the nodes in nodes. All the
 * nodes will exist in the tree, and all values of the tree's nodes are unique.

Extending the definition of LCA on Wikipedia: "The lowest common ancestor of n nodes 
p1, p2, ..., pn in a binary tree T is the lowest node that has every pi as a descendant 
(where we allow a node to be a descendant of itself) for every valid i". A descendant of 
a node x is a node y that is on the path from node x to some leaf node.Given the root of 
a binary tree and an array of TreeNode objects nodes, return the lowest common ancestor 
(LCA) of all the nodes in nodes. All the nodes will exist in the tree, and all values of 
the tree's nodes are unique.

Extending the definition of LCA on Wikipedia: "The lowest common ancestor of n nodes p1, p2, 
..., pn in a binary tree T is the lowest node that has every pi as a descendant (where we allow 
a node to be a descendant of itself) for every valid i". A descendant of a node x is a node y that
is on the path from node x to some leaf node.
 * 
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }=
 */

 // this ran of time complexity for large test cases
class Solution {
    List<List<TreeNode>> paths;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        paths = new ArrayList<>();
        for (int i = 0; i < nodes.length; i++) {
            dfs(root, nodes[i], new ArrayList());
        }
        TreeNode bestPossible = root;
        int j = 0, minLen = Integer.MAX_VALUE;
        for (int i = 0; i < paths.size(); i++) {
            minLen = Math.min(minLen, paths.get(i).size());
        }
        
        if (paths.size() == 1)
            return paths.get(0).get(minLen - 1);
        
        while (j < minLen) {
            int count = 1;
            for (int i = 1; i < paths.size(); i++) {
                if (paths.get(0).get(j) == paths.get(i).get(j)) {
                    count++;
                }
            }
            if (count == paths.size()) {
                bestPossible = paths.get(0).get(j);
            }
            j++;
        }
        return bestPossible;
    }
    
    public void dfs(TreeNode root, TreeNode target, List<TreeNode> path) {
        if (root == target) {
            path.add(target);
            paths.add(new ArrayList<>(path));
        }
        
        if (root == null)
            return;
        
        path.add(root);
        dfs(root.left, target, path);
        dfs(root.right, target, path);
        path.remove(path.size() - 1);
    }
}

/*
This is the perfect solution it follows the same concept of finding lca for 2 nodes
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        Set<TreeNode> set = new HashSet<>();
        for (TreeNode node : nodes) {
            set.add(node);
        }
        
        return lca(root, set);
    }
    
    public TreeNode lca(TreeNode root, Set<TreeNode> set) {
        if (root == null)
            return null;
        
        if (set.contains(root)) {
            return root;
        }
        
        TreeNode foundLeft = lca(root.left, set);
        TreeNode foundRight = lca(root.right, set);
        
        if (foundLeft != null && foundRight != null)
            return root;
        
        return foundLeft == null ? foundRight : foundLeft;
    }
}