/**
 * A binary expression tree is a kind of binary tree used to represent arithmetic expressions. Each node of a 
 * binary expression tree has either zero or two children. Leaf nodes (nodes with 0 children) correspond to operands
 *  (variables), and internal nodes (nodes with two children) correspond to the operators. In this problem, we only 
 * consider the '+' operator (i.e. addition).

You are given the roots of two binary expression trees, root1 and root2. Return true if the two binary expression 
trees are equivalent. Otherwise, return false.

Two binary expression trees are equivalent if they evaluate to the same value regardless of what the variables are 
set to.

Follow up: What will you change in your solution if the tree also supports the '-' operator (i.e. subtraction)?
 * Definition for a binary tree node.
 * class Node {
 *     char val;
 *     Node left;
 *     Node right;
 *     Node() {this.val = ' ';}
 *     Node(char val) { this.val = val; }
 *     Node(char val, Node left, Node right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean checkEquivalence(Node root1, Node root2) {
        char[] first = new char[26];
        char[] second = new char[26];
        dfs(root1, first);
        dfs(root2, second);
        if (first.length != second.length)
            return false;
        for (int i = 0; i < first.length; i++) {
            if (first[i] != second[i])
                return false;
        }
        return true;
    }
    
    public void dfs(Node node, char[] arr) {
        if (node == null)
            return;
        
        if (node.val != '+') {
            arr[node.val - 'a']++;
        }
        dfs(node.left, arr);
        dfs(node.right, arr);
    }
}