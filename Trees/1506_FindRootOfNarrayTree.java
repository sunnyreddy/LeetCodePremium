/*
You are given all the nodes of an N-ary tree as an array of Node objects, where each node has a unique value.
Return the root of the N-ary tree. 
*/

/*
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
// space O(1) solution
class Solution {
    public Node findRoot(List<Node> tree) {
        int valueSum = 0;
        
        for (Node node : tree) {
            valueSum += node.val;
            for (Node child : node.children) {
                valueSum -= child.val;
            }
        }
        
        for (Node node : tree) {
            if (node.val == valueSum)
                return node;
        }
        return null;
    }
}

class Solution2 {
    public Node findRoot(List<Node> tree) {
        Set<Node> allChilds = new HashSet<>();
        Set<Node> allParents = new HashSet<>();
        
        for (Node node : tree) {
            allParents.add(node);
            for (Node child : node.children) {
                allChilds.add(child);
            }
        }
        
        for (Node parent : allParents) {
            if (!allChilds.contains(parent))
                return parent;
        }
        return null;
    }
}











