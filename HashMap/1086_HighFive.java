/*

Given a list of the scores of different students, items, where items[i] = [IDi, scorei] 
represents one score from a student with IDi, calculate each student's top five average.

Return the answer as an array of pairs result, where result[j] = [IDj, topFiveAveragej] 
represents the student with IDj and their top five average. Sort result by IDj in increasing order.

A student's top five average is calculated by taking the sum of their top five scores and 
dividing it by 5 using integer division.

*/

class Solution {
    public int[][] highFive(int[][] items) {
        Map<Integer, PriorityQueue<Integer>> students = new TreeMap<>();
        
        for (int i = 0; i < items.length; i++) {
            if (students.containsKey(items[i][0])) {
                students.get(items[i][0]).add(items[i][1]);
                if (students.get(items[i][0]).size() > 5) {
                    students.get(items[i][0]).remove();
                }
            } else {
                PriorityQueue<Integer> minHeap = new PriorityQueue<>();
                minHeap.add(items[i][1]);
                students.put(items[i][0], minHeap);
            }
        }
        
        int[][] ans = new int[students.size()][2];
        
        int idx = 0;
        for (Map.Entry<Integer, PriorityQueue<Integer>> e : students.entrySet()) {
            int sum = 0;
            while (! e.getValue().isEmpty()) {
                sum += e.getValue().remove();
            }
            ans[idx][0] = e.getKey();
            ans[idx][1] = sum / 5;
            idx++;
        }
        return ans;
    }
}