import java.io.*;
import java.util.*;

/*

Suppose we have some input data describing a graph of relationships between parents and children over multiple generations. The data is formatted as a list of (parent, child) pairs, where each individual is assigned a unique positive integer identifier.

For example, in this diagram, 6 and 8 have common ancestors of 4 and 14.
[2,5, 1,2,4]
             15
             |
         14  13
         |   |
1   2    4   12
 \ /   / | \ /
  3   5  8* 9
   \ / \     \
    6*  7     11

parentChildPairs1 = [
    (1, 3), (2, 3), (3, 6), (5, 6), (5, 7), (4, 5),
    (4, 8), (4, 9), (9, 11), (14, 4), (13, 12),
    (12, 9),(15, 13)
]

Write a function that takes the graph, as well as two of the individuals in our dataset, as its inputs and returns true if and only if they share at least one ancestor.

Sample input and output:

hasCommonAncestor(parentChildPairs1, 3, 8) => false
hasCommonAncestor(parentChildPairs1, 5, 8) => true
hasCommonAncestor(parentChildPairs1, 6, 8) => true
hasCommonAncestor(parentChildPairs1, 6, 9) => true
hasCommonAncestor(parentChildPairs1, 1, 3) => false
hasCommonAncestor(parentChildPairs1, 3, 1) => false
hasCommonAncestor(parentChildPairs1, 7, 11) => true
hasCommonAncestor(parentChildPairs1, 6, 5) => true
hasCommonAncestor(parentChildPairs1, 5, 6) => true

Additional example: In this diagram, 4 and 12 have a common ancestor of 11.

        11
       /  \
      10   12
     /  \
1   2    5
 \ /    / \
  3    6   7
   \        \
    4        8

parentChildPairs2 = [
    (1, 3), (11, 10), (11, 12), (2, 3), (10, 2),
    (10, 5), (3, 4), (5, 6), (5, 7), (7, 8),
]

hasCommonAncestor(parentChildPairs2, 4, 12) => true
hasCommonAncestor(parentChildPairs2, 1, 6) => false
hasCommonAncestor(parentChildPairs2, 1, 12) => false

n: number of pairs in the input

*/

public class Solution {
  public static void main(String[] argv) {
    int[][] parentChildPairs1 = new int[][] {
        {1, 3}, {2, 3}, {3, 6}, {5, 6}, {5, 7}, {4, 5},
        {4, 8}, {4, 9}, {9, 11}, {14, 4}, {13, 12}, {12, 9},
        {15, 13}
    };

    int[][] parentChildPairs2 = new int[][] {
        {1, 3}, {11, 10}, {11, 12}, {2, 3}, {10, 2},
        {10, 5}, {3, 4}, {5, 6}, {5, 7}, {7, 8}
    };

    // 1: []
    // 2: []
    // 3: [1,2]
    // 6: [3]
    
//     List<List<Integer>> res = findPairs(parentChildPairs);
//     for (List<Integer> l : res) {
//       System.out.println(l);
//     }
  }
  
  public static boolean isAnc(int[][] parentChildPairs, int node1, int node2) {
    Map<Integer, List<Integer>> graph = new HashMap<>();
      
      // init all lists
      for (int i = 0; i < parentChildPairs.length; i++) { // n
          int parent = parentChildPairs[i][0];
          int child = parentChildPairs[i][1];
          if (graph.containsKey(child)) {
              graph.get(child).add(parent);
          } else {
              graph.put(child, new ArrayList<>());
              graph.get(child).add(parent);
          }
          if (!graph.containsKey(parent) ) {
              graph.put(parent, new ArrayList<>());
          }
      }
    
      List<Integer> node1Parents = new ArrayList<>();
     List<Integer> node2Parents = new ArrayList<>();  
    dfs(node1, node1Parents, graph);
    
  }
  
  public static void dfs(int node, List<Integer> parents, Map<Integer, List<Integer>> graph) {
      if (graph.get(node).size() == 0) {
        return;
      }
    
      for (int par : graph.get(node)) {
          parents.add(par);
        dfs(par, node1Parents, graph);
      }
  }
  
  public static List<List<Integer>> findPairs(int[][] parentChildPairs) {
      // init res
      List<List<Integer>> res = new ArrayList<>(); // n
      
      Map<Integer, List<Integer>> graph = new HashMap<>();
      
      // init all lists
      for (int i = 0; i < parentChildPairs.length; i++) { // n
          int parent = parentChildPairs[i][0];
          int child = parentChildPairs[i][1];
          if (graph.containsKey(child)) {
              graph.get(child).add(parent);
          } else {
              graph.put(child, new ArrayList<>());
              graph.get(child).add(parent);
          }
          if (!graph.containsKey(parent) ) {
              graph.put(parent, new ArrayList<>());
          }
      }
      // 0 parents
      res.add(new ArrayList<>());
      // 1
      res.add(new ArrayList<>());
      for (Map.Entry<Integer, List<Integer>> e : graph.entrySet()) {
        System.out.println(e.getKey());
        System.out.println(e.getValue());
          if (e.getValue().size() == 0) {
              res.get(0).add(e.getKey());
          } else if (e.getValue().size() == 1) {
            res.get(1).add(e.getKey());
          }
      }
    
      return res;
  }
}
