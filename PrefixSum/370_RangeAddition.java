/*
You are given an integer length and an array updates where updates[i] = [startIdxi, endIdxi, inci].

You have an array arr of length length with all zeros, and you have some operation to apply on arr. In the 
ith operation, you should increment all the elements arr[startIdxi], arr[startIdxi + 1], ..., arr[endIdxi] by 
inci.

Return arr after applying all the updates.
*/
class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] ans = new int[length];
        // for each update, update only start, end points
        for (int i = 0; i < updates.length; i++) {
            ans[updates[i][0]] += updates[i][2];
            // [start, end] need to be +val and [end + 1, length] -val 
            if (updates[i][1] + 1 < length) {
                ans[updates[i][1] + 1] -= updates[i][2];
            }
        }
        
        for (int i = 1; i < length; i++) {
            ans[i] += ans[i - 1];
        }
        return ans;
    }
}
