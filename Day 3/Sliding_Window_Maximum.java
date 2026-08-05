/*
 * ============================================================================
 * PROBLEM STATEMENT: Sliding Window Maximum (LeetCode 239)
 * ============================================================================
 * You are given an array of integers 'nums' and an integer 'k'. There is a 
 * sliding window of size 'k' moving from the far left of the array to the far right. 
 * You can only see the 'k' numbers in the window. Each time the sliding window 
 * moves right by one position, return the maximum element in the window.
 * 
 * Example 1:
 *   Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 *   Output: [3,3,5,5,6,7]
 *   Explanation: 
 *   Window position                Max
 *   ---------------               -----
 *   [1  3  -1] -3  5  3  6  7       3
 *    1 [3  -1  -3] 5  3  6  7       3
 *    1  3 [-1  -3  5] 3  6  7       5
 *    1  3  -1 [-3  5  3] 6  7       5
 *    1  3  -1  -3 [5  3  6] 7       6
 *    1  3  -1  -3  5 [3  6  7]      7
 *
 * Example 2:
 *   Input: nums = [1], k = 1
 *   Output: [1]
 * 
 * ============================================================================
 * LOGICAL ERRORS IN YOUR ORIGINAL CODE:
 * ============================================================================
 * 1. WRONG WINDOW BOUNDS (r = k):
 *    - A window of size k starting at index 0 ends at index (k - 1), not k. 
 *    - Initializing r = k makes your first window (0 to k) contain (k + 1) elements.
 *
 * 2. OVERWRITING THE RESULT ARRAY (j = 0 inside loop):
 *    - Declaring 'int j = 0;' inside the 'while' loop resets 'j' to 0 on every shift.
 *    - 'maxi[j++] = m' constantly overwrote index 0 instead of filling indices 0, 1, 2, ...
 *
 * 3. INEFFICIENT TIME COMPLEXITY O(N * K) -> TLE:
 *    - Running a nested loop over all 'k' elements for every shift leads to O(N * K) time.
 *    - For large arrays (N = 10^5, K = 50,000), this takes billions of operations and times out.
 * ============================================================================
 */

import java.util.ArrayDeque;
import java.util.Deque;

class Sliding_Window_Maximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        
        // Result array size formula: (n - k + 1)
        int[] result = new int[n - k + 1];
        
        // Deque stores indices of array elements in DECREASING order of their values.
        // The index of the maximum element for the current window will always be at the front.
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            
            // Step 1: Remove indices that are out of the current window boundary.
            // A window ending at index 'i' starts at (i - k + 1). Any index < (i - k + 1) is invalid.
            if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // Step 2: Maintain monotonic decreasing order in deque.
            // Remove smaller or equal elements from the back because they can never be 
            // the maximum as long as the current element nums[i] is in the window.
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }

            // Step 3: Add current element's index to the back of the deque.
            deque.offerLast(i);

            // Step 4: Record maximum element once the first window of size 'k' is formed (i >= k - 1).
            // The maximum element for window ending at 'i' is stored at the front of the deque.
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }

        return result;
    }
}