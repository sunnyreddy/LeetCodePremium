/**
 * Given the root of a binary search tree and a node p in it, return the in-order successor of that node in the BST. If the given node has no in-order successor in the tree, return null.

The successor of a node p is the node with the smallest key greater than p.val.
 * 
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // do a binary search and keep the best possible ans
        // i.e smallest number greater than target
        TreeNode bestPossible = null;
        while (root != null) {
            if (root.val <= p.val) {
                root = root.right;
            } else {
                bestPossible = root;
                root = root.left;
            }
        }
        return bestPossible;
    }
}

// Method 2: Generic works for BT as well
class Solution {
    TreeNode inOrderSuccessorNode = null;
    TreeNode previous = null;
    TreeNode target;
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // base case
        if (root == null || p == null) {
            return null;
        }
        // if right is not null go as left as possible for that node and return
        if (p.right != null) {
            TreeNode curr = p.right;
            while (curr.left != null) {
                curr = curr.left;
            }
            return curr;
        }
        // do inOrder and keep track of prev Node if prev node is out target 
        // then our curr node will be the successor of target
        target = p;
        helper(root);
        return inOrderSuccessorNode;
    }
    
    public void helper(TreeNode root) {
        // base case
        if (root == null)
            return;
        
        helper(root.left);
        if (previous == target && inOrderSuccessorNode == null) {
            inOrderSuccessorNode = root;
            return;
        }
        previous = root;
        helper(root.right);
    }
}