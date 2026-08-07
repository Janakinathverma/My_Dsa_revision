class kConcatenation_Max_Sum {
    public int kConcatenationMaxSum(int[] arr, int k) {
        long MOD = 1_000_000_007; // Modulo constant required to prevent integer overflow in final result
        
        // Step 1: Calculate the total sum of a single original array
        long totalSum = 0;
        for (int num : arr) {
            totalSum += num;
        }

        // Step 2: Base case for k = 1 (no concatenation needed)
        // Simply run Kadane's algorithm on a single array
        if (k == 1) {
            return (int) (kadane(arr, 1) % MOD);
        }

        // Step 3: Run Kadane's algorithm on 2 concatenated arrays (arr repeated twice)
        // This covers maximum subarrays that cross the boundary between two adjacent array copies
        long max2ArraySum = kadane(arr, 2);

        // Step 4: Determine the maximum subarray sum based on totalSum
        if (totalSum > 0) {
            // If totalSum is positive, the middle (k - 2) full arrays will strictly increase the sum.
            // Apply modulo to each term during multiplication to prevent 64-bit long overflow.
            long middleSum = ((k - 2) % MOD * (totalSum % MOD)) % MOD;
            
            // Add boundary max sum (max2ArraySum) with middle full arrays sum
            return (int) ((max2ArraySum + middleSum) % MOD);
        } else {
            // If totalSum <= 0, including middle full arrays will only decrease or keep the sum same.
            // The optimal subarray sum can at most span across 2 adjacent array copies.
            return (int) (max2ArraySum % MOD);
        }
    }

    /**
     * Standard Kadane's Algorithm extended to run over 'repeat' copies of 'arr'.
     * @param arr The base input array
     * @param repeat Number of times to virtually repeat the array (1 or 2)
     * @return The maximum subarray sum found (minimum 0 for empty subarray)
     */
    private long kadane(int[] arr, int repeat) {
        long maxSum = 0;     // Tracks global maximum subarray sum (0 accounts for empty subarray)
        long currentSum = 0; // Tracks running subarray sum
        
        // Virtual loop repeating the array 'repeat' times without extra memory allocation
        for (int r = 0; r < repeat; r++) {
            for (int num : arr) {
                currentSum += num; // Include current element in current subarray
                
                maxSum = Math.max(maxSum, currentSum); // Update max sum found so far
                
                // If running sum becomes negative, reset to 0 (start a new subarray)
                if (currentSum < 0) {
                    currentSum = 0;
                }
            }
        }
        return maxSum;
    }
}