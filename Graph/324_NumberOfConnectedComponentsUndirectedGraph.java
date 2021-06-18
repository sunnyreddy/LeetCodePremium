/*
You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi] 
indicates that there is an edge between ai and bi in the graph.

Return the number of connected components in the graph.
*/

class Solution {
    public int countComponents(int n, int[][] edges) {
        // create adjacency lists
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }
        // undirected graph creation
        for (int i = 0; i < edges.length; i++) {
            map.get(edges[i][0]).add(edges[i][1]);
            map.get(edges[i][1]).add(edges[i][0]);
        }
        
        int count = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                count++;
                dfs(visited, map, i);
            }
        }
        return count;
    }
    
    public void dfs(boolean[] visited, Map<Integer, List<Integer>> map, int node) {
        if (visited[node]) {
            return;
        }
        
        visited[node] = true;
        for (int adjNode : map.get(node)) {
            dfs(visited, map, adjNode);
        }
    } 
}