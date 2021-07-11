/*
Given an integer array nums and an integer k, you are asked to construct the array ans of size n-k+1 
where ans[i] is the number of distinct numbers in the subarray nums[i:i+k-1] = [nums[i], nums[i+1], ..., 
nums[i+k-1]].

Return the array ans.

*/
class Solution {
    public int[] distinctNumbers(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        int left = 0, idx = 0;
        Map<Integer, Integer> counts = new HashMap<>();
        
        for (int right = 0; right < nums.length; right++) {
            counts.put(nums[right], counts.getOrDefault(nums[right], 0) + 1);
            // when you get to a window size
            if (right - left + 1 == k) {
                ans[idx++] = counts.size();
                counts.put(nums[left], counts.get(nums[left]) - 1);
                if (counts.get(nums[left]) == 0) {
                    counts.remove(nums[left]);
                }
                left++;
            }
        }
        return ans;
    }
}