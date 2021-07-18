import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    public static int minSlidingWindow(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        // indexes monotonically increasing
        // first - small, last - big
        Deque<Integer> deque = new ArrayDeque<Integer>();
        // sliding window
        for (int i = 0; i < nums.length; i++) {
            // clear elements from deque which are out of window
            if (!deque.isEmpty() && i - deque.getFirst() == k) {
                deque.removeFirst();
            }
            // maintain monotonically increasing sequence
            while (!deque.isEmpty() && nums[i] < nums[deque.getLast()]) {
                deque.removeLast();
            }
            // add curr index to deque
            deque.addLast(i);
            // update once you reach window update maxGlobal of the min value in window
            if (i >= k - 1) {
                max = Math.max(max, nums[deque.getFirst()]);
            }
        }
        return max;
    }
    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        System.out.println(minSlidingWindow(nums, 1));
    }
}

