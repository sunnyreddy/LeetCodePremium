/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int ans = 0;
    public int countUnivalSubtrees(TreeNode root) {
        isUnival(root);
        return ans;
    }
    
    // [isUnival, valueOfSubtree]
    public int[] isUnival(TreeNode root) {
        // null case
        if (root == null) {
            return new int[]{1, -10000};
        }
        int[] leftInfo = isUnival(root.left);
        int[] rightInfo = isUnival(root.right);
        // both subtrees are univals
        if (leftInfo[0] == 1 && rightInfo[0] == 1) {
            // verify if current subtree at this node is unival
            if (leftInfo[1] == rightInfo[1] && (leftInfo[1] == root.val || leftInfo[1] == -10000)) {
                ans++;
                return new int[]{1, root.val};
            } else if (leftInfo[1] == -10000) {
                if (root.val == rightInfo[1]) {
                    ans++;
                    return new int[]{1, root.val};
                }
            } else if (rightInfo[1] == -10000) {
                if (root.val == leftInfo[1]) {
                    ans++;
                    return new int[]{1, root.val};
                }
            }
        }
        return new int[]{0, root.val};
    }
}
