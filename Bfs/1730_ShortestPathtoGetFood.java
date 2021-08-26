/*
You are starving and you want to eat food as quickly as possible. You want to find the shortest path to arrive at any 
food cell.

You are given an m x n character matrix, grid, of these different types of cells:

'*' is your location. There is exactly one '*' cell.
'#' is a food cell. There may be multiple food cells.
'O' is free space, and you can travel through these cells.
'X' is an obstacle, and you cannot travel through these cells.
You can travel to any adjacent cell north, east, south, or west of your current location if there is not an 
obstacle.

Return the length of the shortest path for you to reach any food cell. If there is no path for you to reach food, 
return -1.
*/
class Solution {
    int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int min = Integer.MAX_VALUE;

    public int getFood(char[][] grid) {
        if (grid == null || grid.length == 0)
            return -1;
        // find the start point and do dfs
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == '*') {
                    queue.offer(new Pair(row, col));
                }
            }
        }
        // do a bfs
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                Pair<Integer, Integer> pair = queue.poll();
                for (int[] dir : directions) {
                    int x = pair.getKey() + dir[0];
                    int y = pair.getValue() + dir[1];
                    
                    if (x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] != 'X') {
                        if (grid[x][y] == '#')
                            return level;
                        grid[x][y] = 'X';
                        queue.add(new Pair(x, y));
                    }
                }
            }
        }
        return -1;
    }
}