/**
 * This is the interface for the expression tree Node.
 * You should not remove it, and you can define some classes to implement it.
 */

abstract class Node {
    public abstract int evaluate();
    // define your fields here
};

class TreeNode extends Node {
    String val;
    TreeNode left;
    TreeNode right;
    
    public TreeNode(String val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
    
    public TreeNode(String val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
    
    @Override
    public int evaluate() {
        if (!this.val.equals("+") && !this.val.equals("-")  && !this.val.equals("*")  && !this.val.equals("/") ) {
            return Integer.parseInt(this.val);
        } else {
            switch(this.val) {
                case "+":
                    return this.left.evaluate() + this.right.evaluate();
                case "-":
                    return this.left.evaluate() - this.right.evaluate();
                case "*":
                    return this.left.evaluate() * this.right.evaluate();
                case "/":
                    return this.left.evaluate() / this.right.evaluate();
                default:
                    return 0;
            }   
        }
    }
}

/**
 * This is the TreeBuilder class.
 * You can treat it as the driver code that takes the postinfix input 
 * and returns the expression tree represnting it as a Node.
 */

class TreeBuilder {
    Node buildTree(String[] postfix) {
        if (postfix == null || postfix.length == 0)
            return null;
        
        Stack<TreeNode> stack = new Stack<>();
        for (int i = 0; i < postfix.length; i++) {
            if (postfix[i].equals("+") 
                || postfix[i].equals("-")  
                || postfix[i].equals("*")  
                || postfix[i].equals("/") 
            ) {
                TreeNode right = stack.pop();
                TreeNode left = stack.pop();
                TreeNode curr = new TreeNode(postfix[i], left, right);
                stack.push(curr);
                
            } else {
                stack.push(new TreeNode(postfix[i]));
            }
        }
        return stack.peek();
    }
};


/**
 * Your TreeBuilder object will be instantiated and called as such:
 * TreeBuilder obj = new TreeBuilder();
 * Node expTree = obj.buildTree(postfix);
 * int ans = expTree.evaluate();
 */