/*
You are given some lists of regions where the first region of each list includes all other regions in that list.

Naturally, if a region x contains another region y then x is bigger than y. Also, by definition, a region x 
contains itself.

Given two regions: region1 and region2, return the smallest region that contains both of them.

If you are given regions r1, r2, and r3 such that r1 includes r3, it is guaranteed there is no r2 such that r2 
includes r3.

It is guaranteed the smallest region exists.
*/
class Solution {
    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        // build a map<child, parent> regions
        Map<String, String> map = new HashMap<>();
        for (List<String> list : regions) {
            String parent = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                map.put(list.get(i), parent);
            }
        }
        // iterate to parent and store path 1
        List<String> path1 = new ArrayList<>();
        while (map.containsKey(region1)) {
            path1.add(region1);
            region1 = map.get(region1);
        }
        path1.add(region1);
        // iterate to parent and store path 2
        List<String> path2 = new ArrayList<>();
        while (map.containsKey(region2)) {
            path2.add(region2);
            region2 = map.get(region2);
        }
        path2.add(region2);
        
        // iterate paths from backwards and find lca i.e answer
        int i = path1.size() - 1;
        int j = path2.size() - 1;
        String bestPossible = "";
        while (i >= 0 && j >= 0) {
            if (path1.get(i).equals(path2.get(j))) {
                bestPossible = path1.get(i);
            } else {
                break;
            }
            i--;
            j--;
        }
        return bestPossible;
     }
}
