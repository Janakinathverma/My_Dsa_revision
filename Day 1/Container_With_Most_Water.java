/**
 * Problem: LeetCode 11 - Container With Most Water
 * Concept (Two-Pointer Technique):
 * - The area between two lines is limited by the shorter line:
 *   Area = min(height[left], height[right]) * (right - left)
 * - To maximize area, we start with the widest container (left = 0, right = n - 1).
 * - Moving the taller line inward would only decrease width without increasing height.
 * - Therefore, we greedily move the shorter line inward in hopes of finding a taller line
 *   that compensates for the reduced width.
 * Time Complexity:  O(N) - Single pass with two pointers.
 * Space Complexity: O(1) - Constant extra space used.
 */
class Solution {
    public int maxArea(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            // Calculate current container dimensions
            int currentHeight = Math.min(height[left], height[right]);
            int currentWidth = right - left;
            int currentArea = currentHeight * currentWidth;

            // Track maximum area found so far
            maxArea = Math.max(maxArea, currentArea);

            // Move the pointer pointing to the shorter line
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }
}