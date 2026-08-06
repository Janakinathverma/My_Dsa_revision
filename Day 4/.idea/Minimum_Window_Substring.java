import java.util.HashMap;
import java.util.Map;

/**
 * Problem: Minimum Window Substring (LeetCode 76)
 * 
 * Task: 
 * String `s` mein se sabse chhota aisa substring dhoondhna hai jisme 
 * string `t` ke saare characters (with duplicate counts) present ho.
 * 
 * Approach: Variable-size Sliding Window + Two Pointers + Frequency Array
 * 
 * Time Complexity:  O(N + M) - Where N = s.length(), M = t.length()
 * Space Complexity: O(1)     - ASCII character array of size 128 (constant space)
 */
class Minimum_Window_Substring {

    /**
     * OPTIMAL APPROACH: Sliding Window with Frequency Array & Formed Count
     * --------------------------------------------------------------------
     * 1. String `t` ke saare characters ki frequency `tFreq` array mein store karo.
     * 2. Right pointer `r` ko aage badhao aur window mein incoming characters count karo.
     * 3. Jab window VALID ho jaye (yaani `t` ke saare characters required frequency tak mil jaye),
     *    tab Left pointer `l` ko right shift karke window ko SHRINK (shrinkable window) karo 
     *    taaki MINIMUM window length mil sake.
     */
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        // Frequency table for target string `t` (ASCII size 128 to cover uppercase & lowercase)
        int[] tFreq = new int[128];
        for (char c : t.toCharArray()) {
            tFreq[c]++;
        }

        // Total unique characters in `t` that need to be matched with exact frequency
        int required = 0;
        for (int count : tFreq) {
            if (count > 0) required++;
        }

        // Current window frequency tracker
        int[] windowFreq = new int[128];
        
        // Formed tracks how many unique characters match the required frequency in current window
        int formed = 0;

        // Two Pointers
        int l = 0, r = 0;

        // Result variables: {window_length, start_index, end_index}
        int minLen = Integer.MAX_VALUE;
        int minStart = 0;

        while (r < s.length()) {
            // Step 1: Add right character to window
            char rightChar = s.charAt(r);
            windowFreq[rightChar]++;

            // Check if frequency of rightChar in window matches target frequency in `t`
            if (tFreq[rightChar] > 0 && windowFreq[rightChar] == tFreq[rightChar]) {
                formed++;
            }

            // Step 2: Try to shrink the window from left once it becomes VALID
            while (l <= r && formed == required) {
                char leftChar = s.charAt(l);

                // Update minimum length window found so far
                if (r - l + 1 < minLen) {
                    minLen = r - l + 1;
                    minStart = l;
                }

                // Remove left character from window to shrink it
                windowFreq[leftChar]--;
                if (tFreq[leftChar] > 0 && windowFreq[leftChar] < tFreq[leftChar]) {
                    formed--; // Window is no longer valid
                }

                l++; // Shrink window left boundary
            }

            r++; // Expand window right boundary
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Test Case 1
        String s1 = "ADOBECODEBANC", t1 = "ABC";
        System.out.println("Output (Test 1): " + sol.minWindow(s1, t1)); // Output: "BANC"

        // Test Case 2
        String s2 = "a", t2 = "a";
        System.out.println("Output (Test 2): " + sol.minWindow(s2, t2)); // Output: "a"

        // Test Case 3
        String s3 = "a", t3 = "aa";
        System.out.println("Output (Test 3): " + sol.minWindow(s3, t3)); // Output: ""
    }
}