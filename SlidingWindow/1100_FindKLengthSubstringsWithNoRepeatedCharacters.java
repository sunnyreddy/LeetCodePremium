/*

Given a string s, return the number of substrings of length k with no repeated characters.

*/
class Solution {
    public int numKLenSubstrNoRepeats(String s, int k) {
        int left = 0, ans = 0;
        Map<Character, Integer> counts = new HashMap<>();
        
        for (int right = 0; right < s.length(); right++) {
            char curr = s.charAt(right);
            counts.put(curr, counts.getOrDefault(curr, 0) + 1);
            // once we hit the window length
            if (right - left + 1 == k) {
                // if map has all unique elements
                if (counts.size() == k) {
                    ans++;
                }
                // move left pointer
                char leftMost = s.charAt(left);
                counts.put(leftMost, counts.get(leftMost) - 1);
                if (counts.get(leftMost) == 0) {
                    counts.remove(leftMost);
                }
                left++;
            }
        }
        return ans;
    }
}