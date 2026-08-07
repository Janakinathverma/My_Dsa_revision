class Circular_kadane {
    /*
     * APPROACH EXPLANATION (Circular Array Max Subarray Sum):
     * 
     * In a circular array of size N, the maximum sum subarray can exist in 2 cases:
     * 
     * Case 1: Non-wrapping (Standard Subarray)
     * - The maximum sum subarray lies entirely inside the array bounds (does not wrap around).
     * - Handled directly using Standard Kadane's Algorithm -> 'maxSoFar'.
     * 
     * Case 2: Wrapping (Circular Subarray)
     * - The maximum sum subarray wraps around the boundary (takes elements from start & end).
     * - KEY TRICK: Finding the maximum wrapped sum is equivalent to taking the Total Array Sum 
     *   and subtracting the MINIMUM non-wrapping subarray sum from it.
     * - Wrapped Max Sum = totalSum - minSoFar
     * 
     * FINAL RESULT: 
     * - Max(Case 1, Case 2) -> Math.max(maxSoFar, totalSum - minSoFar)
     * 
     * EDGE CASE (All Negative Numbers):
     * - If all elements are negative (e.g., [-3, -2, -1]), 'maxSoFar' will be the largest single negative element (-1).
     * - 'totalSum - minSoFar' would equal 0 (an empty subarray sum), which is invalid since 
     *   the problem requires a non-empty subarray.
     * - Handled by checking if 'maxSoFar < 0'; if so, we simply return 'maxSoFar'.
     */
    public int maxSubarraySumCircular(int[] nums) {
        int totalSum = 0;
        
        // Trackers for Standard Kadane (Maximum Subarray Sum)
        int maxSoFar = nums[0];
        int currentMax = 0;
        
        // Trackers for Inverted Kadane (Minimum Subarray Sum)
        int minSoFar = nums[0];
        int currentMin = 0;
        
        for (int num : nums) {
            totalSum += num; // Calculate total sum of array
            
            // 1. Standard Kadane's Algorithm for Maximum Subarray
            currentMax += num;
            maxSoFar = Math.max(maxSoFar, currentMax);
            if (currentMax < 0) {
                currentMax = 0; // Reset running sum if negative
            }
            
            // 2. Inverted Kadane's Algorithm for Minimum Subarray
            currentMin += num;
            minSoFar = Math.min(minSoFar, currentMin);
            if (currentMin > 0) {
                currentMin = 0; // Reset running sum if positive
            }
        }
        
        // Edge Case: If all elements are negative, return max single element
        if (maxSoFar < 0) {
            return maxSoFar;
        }
        
        // Return max between non-wrapping sum (maxSoFar) and wrapping sum (totalSum - minSoFar)
        return Math.max(maxSoFar, totalSum - minSoFar);
    }
}