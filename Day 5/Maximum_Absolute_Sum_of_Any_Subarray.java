class Maximum_Absolute_Sum_of_Any_Subarray {
    /*
     * APPROACH 1 (Kadane's Algorithm):
     * 1. Track the maximum subarray sum (maxSum).
     * 2. Track the minimum subarray sum (minSum).
     * 3. The absolute maximum sum will be Math.max(maxSum, Math.abs(minSum)).
     */
    public int maxAbsoluteSum(int[] nums) {
        int maxSum = 0, currentMax = 0;
        int minSum = 0, currentMin = 0;

        for (int num : nums) {
            // Standard Kadane for Max Sum
            currentMax += num;
            maxSum = Math.max(maxSum, currentMax);
            if (currentMax < 0) currentMax = 0;

            // Inverted Kadane for Min Sum
            currentMin += num;
            minSum = Math.min(minSum, currentMin);
            if (currentMin > 0) currentMin = 0;
        }

        return Math.max(maxSum, Math.abs(minSum));
    }
}