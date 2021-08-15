class Solution {
    int nodesVisited = 0;
    public boolean validTree(int n, int[][] edges) {
        // 0 - never visited, 1 - visted, -1 - visiting in curr cycle
        int[] seen = new int[n];
        // create a graph
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        // iterate and check for cycle
        int components = 0;
        for (int i = 0; i < n; i++) {
            components++;
            if (!dfs(0, graph, seen)) {
                return false;
            }
            if (nodesVisited == graph.size())
                break;
        }
        return components == 1 ? true : false;
    }
    
    public boolean dfs(int node, Map<Integer, Set<Integer>> graph, int[] seen) {
        if (seen[node] == -1) {
            return false;
        }
        
        if (seen[node] == 1)
            return true;
        
        seen[node] = -1;
        for (int adj : graph.get(node)) {
            // as graph is undirected you need to remove the edge such that you 
            // won't go in cycles
            graph.get(adj).remove(node);
            if (!dfs(adj, graph, seen)) {
                return false;
            }
        }
        seen[node] = 1;
        nodesVisited++;
        return true;
    }
}
