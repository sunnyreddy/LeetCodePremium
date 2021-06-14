/*

You are given an image that is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel.

The black pixels are connected (i.e., there is only one black region). Pixels are connected horizontally and vertically.

Given two integers x and y that represent the location of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

*/
class Solution {
    private int top = 0, bottom = 0, left = 0, right = 0;
    public int minArea(char[][] image, int x, int y) {
        top = bottom = x;
        left = right = y;
        dfs(image, x, y);
        return (bottom - top + 1) * (right - left + 1);
    }
    
    public void dfs(char[][] image, int x, int y) {
        // base case out of bounds
        if (x < 0 || y < 0 || x >= image.length || y >= image[x].length || image[x][y] == '0')
            return;
        
        // update coordinates
        top = Math.min(top, x);
        bottom = Math.max(bottom, x);
        left = Math.min(left, y);
        right = Math.max(right, y);
        
        image[x][y] = '0';
        // recurse through paths
        dfs(image, x, y + 1);
        dfs(image, x, y - 1);
        dfs(image, x + 1, y);
        dfs(image, x - 1, y);
    }
}
