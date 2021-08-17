/*
A tree rooted at node 0 is given as follows:

The number of nodes is nodes;
The value of the i-th node is value[i];
The parent of the i-th node is parent[i].
Remove every subtree whose sum of values of nodes is zero.

After doing so, return the number of nodes remaining in the tree.
*/
class Solution {
    class Node {
        int val;
        List<Node> children;
        
        public Node(int val) {
            this.val = val;
            this.children = new ArrayList<Node>();
        }
    }
    
    Map<Integer, Node> map = new HashMap<>();
    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
        // build the tree
        for (int i = 0; i < nodes; i++) {
            Node node = new Node(value[i]);
            map.put(i, node);
        }
        for (int i = 1; i < parent.length; i++) {
            map.get(parent[i]).children.add(map.get(i));
        }
        // do dfs and get sum of subtrees
        dfs(map.get(0));
        return countNodes(map.get(0));
    }
    
    public int dfs(Node node) {
        if (node == null)
            return 0;
        
        int value = node.val;
        for (Node child : node.children) {
            value += dfs(child);
        }
        node.val = value;
        return value;
    }
    
    public int countNodes(Node node) {
        if (node == null || node.val == 0) {
            return 0;
        }
        
        int count = 1;
        for (Node child : node.children) {
            count += countNodes(child);
        }
        return count;
    }
}