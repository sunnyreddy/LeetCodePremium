/*
Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).

Each node will have a reference to its parent node. The definition for Node is below:

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
}
According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p 
and q in a tree T is the lowest node that has both p and q as descendants (where we allow a 
node to be a descendant of itself)."
*/

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};
*/
// 4, 2, 5, 3
// 5, 3
class Solution {
    public Node lowestCommonAncestor(Node p, Node q) {
        List<Node> pathToP = new ArrayList<>();
        List<Node> pathToQ = new ArrayList<>();
        
        while (p != null) {
            pathToP.add(p);
            p = p.parent;
        }
        while (q != null) {
            pathToQ.add(q);
            q = q.parent;
        }
        
        int i = pathToP.size() - 1;
        int j = pathToQ.size() - 1;
        Node bestPossible = null;
        
        while (i >= 0 && j >= 0) {
            if (pathToP.get(i) == pathToQ.get(j)) {
                bestPossible = pathToP.get(i);
            } else {
                break;
            }
            i--;
            j--;
        }
        return bestPossible;
    }
}