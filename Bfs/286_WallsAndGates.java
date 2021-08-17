/*
You are given an m x n grid rooms initialized with these three possible values.

-1 A wall or an obstacle.
0 A gate.
INF Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the 
distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled 
with INF.
*/
class Solution {
    public void wallsAndGates(int[][] rooms) {
        int rows = rooms.length;
        int cols = rooms[0].length;
        
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        // add all sources i.e gates to queue for bfs
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (rooms[i][j] == 0) {
                    // Gate
                    queue.offer(new Pair(i, j));
                }
            }
        }
        
        int[][] dirs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        // do a bfs
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Pair<Integer, Integer> pair = queue.poll();
                int row = pair.getKey();
                int col = pair.getValue();
                
                for (int[] dir : dirs) {
                    int nextRow = row + dir[0];
                    int nextCol = col + dir[1];
                    // if index out of bounds continue
                    if (nextRow < 0 || nextCol < 0 || nextRow >= rows || nextCol >= cols || rooms[nextRow][nextCol] == -1) {
                        continue;
                    }
                    if (rooms[nextRow][nextCol] == Integer.MAX_VALUE) {
                        rooms[nextRow][nextCol] = 1 + rooms[row][col];
                        queue.offer(new Pair(nextRow, nextCol));
                    }
                }
            }
        }
    }
}
