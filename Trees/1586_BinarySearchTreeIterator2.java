/**
 * Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree 
 * (BST):

BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as part of 
the constructor. The pointer should be initialized to a non-existent number smaller than any element in the BST.
boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise 
returns false.
int next() Moves the pointer to the right, then returns the number at the pointer.
boolean hasPrev() Returns true if there exists a number in the traversal to the left of the pointer, otherwise 
returns false.
int prev() Moves the pointer to the left, then returns the number at the pointer.
Notice that by initializing the pointer to a non-existent smallest number, the first call to next() will return 
the smallest element in the BST.

You may assume that next() and prev() calls will always be valid. That is, there will be at least a next/previous 
number in the in-order traversal when next()/prev() is called.

Follow up: Could you solve the problem without precalculating the values of the tree?

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
class BSTIterator {
    List<TreeNode> list = new ArrayList<>();
    int curr = 0;
    public BSTIterator(TreeNode root) {
        helper(root, list);
    }
    
    public void helper(TreeNode node, List<TreeNode> list) {
        if (node == null)
            return;
        helper(node.left, list);
        list.add(node);
        helper(node.right, list);
    }
    
    public boolean hasNext() {
        return curr < list.size();
    }
    
    public int next() {
        return list.get(curr++).val;
    }
    
    public boolean hasPrev() {
        return curr - 2 >= 0;
    }
    
    public int prev() {
        curr--;
        return list.get(curr - 1).val;
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * boolean param_1 = obj.hasNext();
 * int param_2 = obj.next();
 * boolean param_3 = obj.hasPrev();
 * int param_4 = obj.prev();
 */
