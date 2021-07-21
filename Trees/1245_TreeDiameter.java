/*
Given an undirected tree, return its diameter: the number of edges in a longest path in that tree.

The tree is given as an array of edges where edges[i] = [u, v] is a bidirectional edge between nodes u and v.  
Each node has labels in the set {0, 1, ..., edges.length}.
*/
class Solution {
    Map<Integer, List<Integer>> graph;
    int maxLen = 0;
    public int treeDiameter(int[][] edges) {
        // create a graph
        graph = new HashMap<>();
        for (int i = 0; i <= edges.length; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        // do dfs
        dfs(0, new HashSet<>());
        return maxLen;
    }
    
    public int dfs(int node, Set<Integer> seen) {
        // base case if node already seen return
        if (seen.contains(node)) {
            return -1;
        }
        
        seen.add(node);
        int topdistance1 = 0, topdistance2 = 0;
        // iterate all children
        for (int child : graph.get(node)) {
            int len = 1 + dfs(child, seen);
            // update top 2 values
            if (len > topdistance1) {
                topdistance2 = topdistance1;
                topdistance1 = len;
            } else if (len > topdistance2) {
                topdistance2 = len;
            }
        }
        maxLen = Math.max(maxLen, topdistance1 + topdistance2);
        return topdistance1;
    }
}