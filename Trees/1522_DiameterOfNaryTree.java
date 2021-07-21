/*
Given a root of an N-ary tree, you need to compute the length of the diameter of the tree.

The diameter of an N-ary tree is the length of the longest path between any two nodes in the tree. 
This path may or may not pass through the root.

(Nary-Tree input serialization is represented in their level order traversal, each group of children is 
separated by the null value.)
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    
    public Node() {
        children = new ArrayList<Node>();
    }
    
    public Node(int _val) {
        val = _val;
        children = new ArrayList<Node>();
    }
    
    public Node(int _val,ArrayList<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    int diameter = 0;
    public int diameter(Node root) {
        dfs(root, new HashSet<>());
        return diameter;
    }
    
    public int dfs(Node node, Set<Node> seen) {
        // when you already seen a node it contributes to nothing 
        // so 1+ dfs you did will negate 
        if (seen.contains(node)) {
            return -1;
        }
        
        seen.add(node);
        int topdistance1 = 0, topdistance2 = 0;
        for (Node child : node.children) {
            int len = 1 + dfs(child, seen);
            
            // update top 2 distance values accordingly
            if (len > topdistance1) {
                topdistance2 = topdistance1;
                topdistance1 = len;
            } else if (len > topdistance2) {
                topdistance2 = len;
            }
        }
        // update the max path that can results from childs
        diameter = Math.max(diameter, topdistance1 + topdistance2);
        // return top value to it;s parent for parent to cal it's values
        return topdistance1;
    }
}