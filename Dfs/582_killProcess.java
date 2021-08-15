/*
You have n processes forming a rooted tree structure. You are given two integer arrays pid and ppid, where pid[i] 
is the ID of the ith process and ppid[i] is the ID of the ith process's parent process.

Each process has only one parent process but may have multiple children processes. Only one process has ppid[i] = 0,
 which means this process has no parent process (the root of the tree).

When a process is killed, all of its children processes will also be killed.

Given an integer kill representing the ID of a process you want to kill, return a list of the IDs of the processes 
that will be killed. You may return the answer in any order.
*/
class Solution {
    // parent, children
    Map<Integer, List<Integer>> tree = new HashMap<>();
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        // build tree
        for (int i = 0; i < ppid.size(); i++) {
            if (!tree.containsKey(ppid.get(i))) {
                tree.put(ppid.get(i), new ArrayList<>());
            } 
            tree.get(ppid.get(i)).add(pid.get(i));
        }
        // do dfs
        List<Integer> ans = new ArrayList<>();
        dfs(kill, ans);
        return ans;
    }
    
    public void dfs(int parent, List<Integer> ans) {
        ans.add(parent);
        if (tree.containsKey(parent)) {
            for (int child : tree.get(parent)) {
                dfs(child, ans);
            }   
        }
    }
}
