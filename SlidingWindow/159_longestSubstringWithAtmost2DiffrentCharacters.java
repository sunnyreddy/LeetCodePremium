class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0, maxLen = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char curr = s.charAt(right);
            map.put(curr, map.getOrDefault(curr, 0) + 1);
            
            while (map.size() > 2) {
                char removal = s.charAt(left++);
                map.put(removal, map.get(removal) - 1);
                if (map.get(removal) == 0)
                    map.remove(removal);
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}