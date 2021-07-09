/*

You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 
4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

An island is considered to be the same as another if and only if one island can be translated (and not 
rotated or reflected) to equal the other.

Return the number of distinct islands.

*/
class Solution {
    Set<Set<Pair<Integer, Integer>>> set = new HashSet<>();
    int initialx = 0, initialy = 0;
    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;
        
        Set<Pair<Integer, Integer>> currList = new HashSet<>();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == 1) {
                    initialx = row;
                    initialy = col;
                    currList = new HashSet<Pair<Integer, Integer>>();
                    dfs(grid, row, col, currList);
                    set.add(currList);
                }
            }
        }
        return set.size();
    }
    
    public void dfs(int[][] grid, int row, int col, Set<Pair<Integer, Integer>> currList) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[row].length || grid[row][col] == 0)
            return;
        
        // rounding to local coordinates
        grid[row][col] = 0;
        currList.add(new Pair(row - initialx, col - initialy));
        dfs(grid, row + 1, col, currList);
        dfs(grid, row - 1, col, currList);
        dfs(grid, row, col + 1, currList);
        dfs(grid, row, col - 1, currList);
    }
}