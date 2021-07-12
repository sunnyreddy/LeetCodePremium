/*

Given a binary array data, return the minimum number of swaps required to group all 1’s present in the 
array together in any place in the array.

*/
class Solution {
    public int minSwaps(int[] data) {
        int onesCount = 0;
        for (int i : data) {
            if (i == 1)
                onesCount++;
        }
        
        int left = 0, min = Integer.MAX_VALUE, currOnes = 0;
        for (int right = 0; right < data.length; right++) {
            if (data[right] == 1) {
                currOnes++;
            }
            // when condition is met
            if (right - left + 1 == onesCount) {
                min = Math.min(min, onesCount - currOnes);
                currOnes -= data[left++];
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}