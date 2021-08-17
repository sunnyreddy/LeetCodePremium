/**
 * 
 * You have a binary tree with a small defect. There is exactly one invalid node where its right child incorrectly 
 * points to another node at the same depth but to the invalid node's right.

Given the root of the binary tree with this defect, root, return the root of the binary tree after removing this 
invalid node and every node underneath it (minus the node it incorrectly points to).

Custom testing:

The test input is read as 3 lines:

TreeNode root
int fromNode (not available to correctBinaryTree)
int toNode (not available to correctBinaryTree)
After the binary tree rooted at root is parsed, the TreeNode with value of fromNode will have its right child 
pointer pointing to the TreeNode with a value of toNode. Then, root is passed to correctBinaryTree.
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
    Set<TreeNode> visited = new HashSet<>();
    public TreeNode correctBinaryTree(TreeNode root) {
        if (root == null)
            return null;
        // do a bfs and look at visited nodes to get error node
        TreeNode removeNode = null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        visited.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (curr.left != null) {
                    visited.add(curr.left);
                    queue.offer(curr.left);
                }
                if (curr.right != null && !visited.contains(curr.right)) {
                    visited.add(curr.right);
                    queue.offer(curr.right);
                } else if (visited.contains(curr.right)) {
                    removeNode = curr;
                    break;
                }
            }
        }
        // delete the error node, it's decendents from tree
        removeNode(root, null, 0, removeNode);
        return root;
    }
    
    public void removeNode(TreeNode node, TreeNode parent, int dir, TreeNode removeNode) {
        if (node == null)
            return;
        
        if (node == removeNode) {
            if (dir == -1) {
                parent.left = null;
            } else if (dir == 1) {
                parent.right = null;
            }
            return;
        }
        removeNode(node.left, node, -1, removeNode);
        removeNode(node.right, node, 1, removeNode);
    }
}