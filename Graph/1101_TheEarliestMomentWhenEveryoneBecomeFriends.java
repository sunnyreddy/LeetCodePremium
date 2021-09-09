/*
There are n people in a social group labeled from 0 to n - 1. You are given an array logs where logs[i] = 
[timestampi, xi, yi] indicates that xi and yi will be friends at the time timestampi.

Friendship is symmetric. That means if a is friends with b, then b is friends with a. Also, person a is acquainted
 with a person b if a is friends with b, or a is a friend of someone acquainted with b.

Return the earliest time for which every person became acquainted with every other person. If there is no such
 earliest time, return -1.

*/

class Solution {
    public int earliestAcq(int[][] logs, int n) {
        // sort the logs with timestamp
        Arrays.sort(logs, (a, b) -> a[0] - b[0]);
        // create a disjoint set
        DisjointSet ds = new DisjointSet(n);
        int time = 0;
        for (int[] log : logs) {
            if (ds.components == 1)
                return time;
            ds.union(log[1], log[2]);
            time = log[0];
        }
        return ds.components == 1 ? time : -1;
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