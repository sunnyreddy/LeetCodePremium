/*
We are given some website visits: the user with name username[i] visited the website website[i] at time timestamp[i].

A 3-sequence is a list of websites of length 3 sorted in ascending order by the time of their visits.  
(The websites in a 3-sequence are not necessarily distinct.)

Find the 3-sequence visited by the largest number of users. If there is more than one solution, return the 
lexicographically smallest such 3-sequence.
*/
class Solution {
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        Map<String, Map<Integer,String>> users = new HashMap<>();
        for (int i = 0; i < username.length; i++) {
            String user = username[i];
            int time = timestamp[i];
            String web = website[i];
            // if user is not present in map i.e first time user seen
            if (!users.containsKey(user)) {
                users.put(user, new TreeMap<>());
            }
            // put timestap and website
            users.get(user).put(time, web);
        }
        // System.out.println(users);
        Map<String, Integer> vistedPatterns = new HashMap<>();
        List<String> ans = new ArrayList<>();
        int maxVisit = 0;
        for (String user : users.keySet()) {
            Set<String> seen = new HashSet<>();
            List<String> websitesOrder = new ArrayList<>();
            for (String val : users.get(user).values()) {
                websitesOrder.add(val);
            }
            
            if (websitesOrder.size() >= 3) {
                for (int i = 0; i < websitesOrder.size(); i++) {
                    for (int j = i + 1; j < websitesOrder.size(); j++) {
                        for (int k = j + 1; k < websitesOrder.size(); k++) {
                            StringBuilder sequence = new StringBuilder();
                            sequence.append(websitesOrder.get(i) + "-");
                            sequence.append(websitesOrder.get(j) + "-");
                            sequence.append(websitesOrder.get(k));
                            String seq = sequence.toString();
                            if (!seen.contains(seq)) {
                                vistedPatterns.put(seq, vistedPatterns.getOrDefault(seq, 0) + 1);
                                if (vistedPatterns.get(seq) > maxVisit) {
                                    maxVisit = vistedPatterns.get(seq);
                                    ans = new ArrayList<>();
                                    ans.add(seq);
                                } else if (vistedPatterns.get(seq) == maxVisit) {
                                    ans.add(seq);
                                }
                            }
                            seen.add(seq);
                        }
                    }
                }
            }
        }
        
        Collections.sort(ans);
        List<String> res = new ArrayList<>();
        for (String s : ans.get(0).split("-")) {
            res.add(s);
        }
        return res;
    }
}