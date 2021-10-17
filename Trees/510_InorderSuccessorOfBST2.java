/*
// Definition for a Node.

Given a node in a binary search tree, return the in-order successor of that node in the BST. If that node has no in-order successor, return null.

The successor of a node is the node with the smallest key greater than node.val.

You will have direct access to the node but not to the root of the tree. Each node will have a reference to its parent node. Below is the definition for Node:
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};
*/

class Solution {
    public Node inorderSuccessor(Node node) {
        // if given node has right child
        if (node.right != null) {
            Node curr = node.right;
            while (curr.left != null) {
                curr = curr.left;
            }
            return curr;
        } else { // no right child
            // go top until you find node which is left child of parent
            while (node.parent != null && node.parent.left != node) {
                node = node.parent;
            }
            return node.parent;
        }
    }
}
