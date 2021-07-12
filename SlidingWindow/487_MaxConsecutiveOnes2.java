/*
Given a binary array nums, return the maximum number of consecutive 1's in the array if you can flip at most one 0.
*/
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int left = 0,max = 0, count = 0, used = 0;
        
        for (int right = 0; right < nums.length; right++) {
            count++;
            if (nums[right] == 0) {
                used++;
            }
            // when you hit conditio i.e don't have anymore chances
            while (used > 1) {
                count--;
                if (nums[left++] == 0) {
                    used--;
                }
            }
            max = Math.max(max, count);
        }
        return max;
    }
}