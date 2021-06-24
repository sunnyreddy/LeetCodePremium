/*

A binary tree is given such that each node contains an additional 
random pointer which could point to any node in the tree or null.

Return a deep copy of the tree.

The tree is represented in the same input/output way as normal binary trees 
where each node is represented as a pair of [val, random_index] where:

val: an integer representing Node.val
random_index: the index of the node (in the input) where the random pointer points to, 
or null if it does not point to any node.
You will be given the tree in class Node and you should return the cloned tree in 
class NodeCopy. NodeCopy class is just a clone of Node class with the same attributes and constructors.

*/

/**
 * Definition for Node.
 * public class Node {
 *     int val;
 *     Node left;
 *     Node right;
 *     Node random;
 *     Node() {}
 *     Node(int val) { this.val = val; }
 *     Node(int val, Node left, Node right, Node random) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *         this.random = random;
 *     }
 * }
 */

class Solution {
    public NodeCopy copyRandomBinaryTree(Node root) {
        Map<Node, NodeCopy> map = new HashMap<>();
        return dfs(root, map);
    }
    
    public NodeCopy dfs(Node root, Map<Node, NodeCopy>  map) {
        if (root == null)
            return null;
        
        if (map.containsKey(root)) {
            return map.get(root);
        }
        NodeCopy newRoot = new NodeCopy(root.val);
        map.put(root, newRoot);
        newRoot.left = dfs(root.left, map);
        newRoot.right = dfs(root.right, map);
        newRoot.random = dfs(root.random, map);
        return newRoot;
    }
}