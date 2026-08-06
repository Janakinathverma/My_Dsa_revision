/**
 * Problem: Best Time to Buy and Sell Stock (LeetCode 121)
 * Approach: Single Pass / One Pass (Greedy Algorithm)
 *
 * Time Complexity:  O(N) - Traversing the prices array exactly once.
 * Space Complexity: O(1) - Only using two variables (minPrice and maxProfit).
 */
public class Best_time_to_Sell {

    /**
     * Calculates the maximum profit that can be achieved from a single buy and sell transaction.
     *
     * @param prices Array of stock prices where prices[i] is the price on the i-th day.
     * @return The maximum profit possible, or 0 if no profit can be made.
     */
    public static int maxProfit(int[] prices) {
        // Guard clause for edge cases (null or fewer than 2 days)
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            // Update the minimum price seen so far
            if (price < minPrice) {
                minPrice = price;
            }
            // Calculate profit if sold today and update maxProfit if it's higher
            else if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        // Test Case 1: Standard case with profit
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        System.out.println("Max Profit (Test 1): " + maxProfit(prices1)); // Output: 5 (Buy at 1, Sell at 6)

        // Test Case 2: Decreasing prices (No profit possible)
        int[] prices2 = {7, 6, 4, 3, 1};
        System.out.println("Max Profit (Test 2): " + maxProfit(prices2)); // Output: 0
    }
}