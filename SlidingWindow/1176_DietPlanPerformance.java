/*

A dieter consumes calories[i] calories on the i-th day. 

Given an integer k, for every consecutive sequence of k days (calories[i], calories[i+1], ..., 
calories[i+k-1] for all 0 <= i <= n-k), they look at T, the total calories consumed during that 
sequence of k days (calories[i] + calories[i+1] + ... + calories[i+k-1]):

If T < lower, they performed poorly on their diet and lose 1 point; 
If T > upper, they performed well on their diet and gain 1 point;
Otherwise, they performed normally and there is no change in points.
Initially, the dieter has zero points. Return the total number of points the dieter has after dieting 
for calories.length days.

Note that the total points can be negative.

*/
class Solution {
    public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
        int left = 0, currCalories = 0, points = 0;
        
        for (int right = 0; right < calories.length; right++) {
            currCalories += calories[right];
            // when we reach a window of size k
            if (right - left + 1 == k) {
                if (currCalories < lower) {
                    points--;
                } else if (currCalories > upper) {
                    points++;
                }
                // once our calculation are done move window
                currCalories -= calories[left++];
            }
        }
        return points;
    }
}