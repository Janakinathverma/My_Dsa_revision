class Maximum_Product_Subarray {
    /*
     * APPROACH EXPLANATION (Maximum Product Subarray):
     * 
     * 1. Multiplication by negative numbers flips signs (positive becomes negative and vice versa).
     * 2. Therefore, at each index, we must keep track of BOTH:
     *    - 'maxProd': Largest positive product up to current element.
     *    - 'minProd': Smallest negative product up to current element (which can become huge positive if multiplied by another negative).
     * 
     * 3. When encountering a negative number, 'maxProd' and 'minProd' swap their roles.
     */
    public int maxProduct(int[] nums) {
        int result = nums[0];
        int maxProd = nums[0];
        int minProd = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];

            // If current number is negative, max and min products swap
            if (num < 0) {
                int temp = maxProd;
                maxProd = minProd;
                minProd = temp;
            }

            // Either start a new subarray at 'num' or extend previous product
            maxProd = Math.max(num, maxProd * num);
            minProd = Math.min(num, minProd * num);

            // Keep track of overall max product seen so far
            result = Math.max(result, maxProd);
        }

        return result;
    }
}