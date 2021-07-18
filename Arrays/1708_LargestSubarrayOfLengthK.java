/*
An array A is larger than some array B if for the first index i where A[i] != B[i], A[i] > B[i].

For example, consider 0-indexing:

[1,3,2,4] > [1,2,2,4], since at index 1, 3 > 2.
[1,4,4,4] < [2,1,1,1], since at index 0, 1 < 2.
A subarray is a contiguous subsequence of the array.

Given an integer array nums of distinct integers, return the largest subarray of nums of length k.
*/
class Solution {
    public int[] largestSubarray(int[] nums, int k) {
        // find greatest value in 0 to n - k + 1
        int max = nums[0], maxIndex = 0;
        for (int i = 0; i <= nums.length - k; i++) {
            if (nums[i] > max) {
                max = nums[i];
                maxIndex = i;
            }
        }
        int[] ans = new int[k];
        int idx = 0;
        for (int i = maxIndex; i < maxIndex + k; i++) {
            ans[idx++] = nums[i];
        }
        return ans;
    }
}