/*
Given a string s and an integer k, return the length of the longest substring of s that contains at most k 
distinct characters.
*/
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0)
            return 0;
        Map<Character, Integer> counts = new HashMap<>();
        int left = 0, max = 0;
        for (int right = 0; right < s.length(); right++) {
            char curr = s.charAt(right); 
            counts.put(curr, counts.getOrDefault(curr, 0) + 1);
            // if we exceeds the limit
            while (counts.size() > k) {
                char leftMost = s.charAt(left);
                counts.put(leftMost, counts.get(leftMost) - 1);
                if (counts.get(leftMost) == 0) {
                    counts.remove(leftMost);
                }
                left++;
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}
