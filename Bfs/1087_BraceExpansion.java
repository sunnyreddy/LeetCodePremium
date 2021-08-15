/*
You are given a string s representing a list of words. Each letter in the word has one or more options.

If there is one option, the letter is represented as is.
If there is more than one option, then curly braces delimit the options. For example, "{a,b,c}" represents options 
["a", "b", "c"].
For example, if s = "a{b,c}", the first character is always 'a', but the second character can be 'b' or 'c'. The 
original list is ["ab", "ac"].

Return all words that can be formed in this manner, sorted in lexicographical order.
*/
class Solution {
    Map<Integer, List<Character>> map;
    public String[] expand(String s) {
        map = new HashMap<>();
        int i = 0, mapIdx = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == '{') {
                map.put(mapIdx, new ArrayList<>());
                int endIndex = s.indexOf("}", i);
                for (int start = i + 1; start < endIndex; start++) {
                    if (s.charAt(start) != ',') {
                        map.get(mapIdx).add(s.charAt(start));
                    }
                }
                i = endIndex + 1;
                mapIdx++;
            } else {
                map.put(mapIdx, new ArrayList<>());
                map.get(mapIdx).add(c);
                mapIdx++;
                i++;
            }
        }
        List<String> ans = new ArrayList<>();
        backtrack(new StringBuilder(), ans, 0);
        // converting list to array
        String[] res = new String[ans.size()];
        Collections.sort(ans);
        int idx = 0;
        for (String str : ans) {
            res[idx++] = str;
        }
        return res;
    }
    
    public void backtrack(StringBuilder sb, List<String> ans, int index) {
        // base case
        if (index == map.size()) {
            ans.add(sb.toString());
            return;
        }
        
        List<Character> mapping = map.get(index);
        for (int i = 0; i < mapping.size(); i++) {
            sb.append(mapping.get(i));
            backtrack(sb, ans, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}