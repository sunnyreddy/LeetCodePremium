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

// Solution Using DisjointSet
class Solution {
    public boolean validTree(int n, int[][] edges) {
        DisjointSet ds = new DisjointSet(n);
        for (int[] edge : edges) {
            if (ds.isInSameGroup(edge[0], edge[1]))
                return false;
            ds.union(edge[0], edge[1]);
        }
        return ds.components == 1 ? true : false;
    }
    
    class DisjointSet {
        private int[] weights; // Used to store weights of each nodes 
        private int[] parents;
        int components;

        public void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            // If both a and b have same root, i.e. they both belong to the same set, hence we don't need to take union
            if (rootA == rootB) return;
            
            this.components--;
            // Weighted union
            if (this.weights[rootA] > this.weights[rootB]) {
                // In case rootA is having more weight
                // 1. Make rootA as the parent of rootB
                // 2. Increment the weight of rootA by rootB's weight
                this.parents[rootB] = rootA;
                this.weights[rootA] += this.weights[rootB];
            } else {
                // Otherwise
                // 1. Make rootB as the parent of rootA
                // 2. Increment the weight of rootB by rootA's weight
                this.parents[rootA] = rootB;
                this.weights[rootB] += this.weights[rootA];
            }
        }

        public int find(int a) {
            // Traverse all the way to the top (root) going through the parent nodes
            while (a != this.parents[a]) {
                // Path compression
                // a's grandparent is now a's parent
                this.parents[a] = this.parents[parents[a]];
                a = this.parents[a];
            }
            return a;
        }

        public boolean isInSameGroup(int a, int b) {
            // Return true if both a and b belong to the same set, otherwise return false
            return find(a) == find(b);
        }

        // Initialize weight for each node to be 1
        public DisjointSet(int N) {
            this.parents = new int[N + 1];
            this.weights = new int[N + 1];
            this.components = N;
            // Set the initial parent node to itself
            for (int i = 1; i <= N; ++i) {
                this.parents[i] = i;
                this.weights[i] = 1;
            }
        }
    }
}
