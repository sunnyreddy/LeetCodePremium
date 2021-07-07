/*

There are n cities numbered from 1 to n.

You are given connections, where each connections[i] = [city1, city2, cost] represents the cost to connect city1 
and city2 together.  (A connection is bidirectional: connecting city1 and city2 is the same as connecting city2 
and city1.)

Return the minimum cost so that for every pair of cities, there exists a path of connections (possibly of length 1)
 that connects those two cities together.  The cost is the sum of the connection costs used. If the task is 
 impossible, return -1.

*/
class DisjointSet {
    private int[] weights; // Used to store weights of each nodes 
    private int[] parents;

    public void Union(int a, int b) {
        int rootA = Find(a);
        int rootB = Find(b);
        // If both a and b have same root, i.e. they both belong to the same set, hence we don't need to take union
        if (rootA == rootB) return;

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

    public int Find(int a) {
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
        return Find(a) == Find(b);
    }

    // Initialize weight for each node to be 1
    public DisjointSet(int N) {
        this.parents = new int[N + 1];
        this.weights = new int[N + 1];
        // Set the initial parent node to itself
        for (int i = 1; i <= N; ++i) {
            this.parents[i] = i;
            this.weights[i] = 1;
        }
    }
}

class Solution {
    public int minimumCost(int N, int[][] connections) {
        DisjointSet disjointset = new DisjointSet(N);
        // Sort connections based on their weights (in increasing order)
        Arrays.sort(connections, (a, b) -> a[2] - b[2]);
        // Keep track of total edges added in the MST
        int total = 0;
        // Keep track of the total cost of adding all those edges
        int cost = 0;
        for (int i = 0; i < connections.length; ++i) {
            int a = connections[i][0];
            int b = connections[i][1];
            // Do not add the edge from a to b if it is already connected
            if (disjointset.isInSameGroup(a, b)) continue;
            // If a and b are not connected, take union
            disjointset.Union(a, b);
            // increment cost
            cost += connections[i][2];
            // increment number of edges added in the MST
            total++;
        }
        // If all N nodes are connected, the MST will have a total of N - 1 edges
        if (total == N - 1) {
            return cost;
        } else {
            return -1;
        }
    }
}