/*
 * ============================================================================
 * PROBLEM STATEMENT: Longest Repeating Character Replacement
 * ============================================================================
 * You are given a string 's' consisting of only uppercase English characters 
 * and an integer 'k'. You can choose up to 'k' characters of the string and 
 * replace them with any other uppercase English character.
 * 
 * Return the length of the longest substring containing only one distinct character 
 * after performing at most 'k' replacements.
 * 
 * Example 1:
 *   Input: s = "ABAB", k = 2
 *   Output: 4
 *   Explanation: Replace two 'A's with 'B's (or two 'B's with 'A's) -> "BBBB" / "AAAA".
 * 
 * Example 2:
 *   Input: s = "AABABBA", k = 1
 *   Output: 4
 *   Explanation: Replace the middle 'A' with 'B' -> "AABBBBA". 
 *   The substring "BBBB" has length 4.
 * 
 * ============================================================================
 * APPROACH / LOGIC:
 * ============================================================================
 * Why fixing s[l] fails:
 *   The target character to maximize inside a window isn't necessarily the 
 *   first character s[l]. The most optimal target is always the MOST FREQUENT 
 *   character inside the current window [l...r].
 * 
 * Sliding Window Condition:
 *   - Window Size = (r - l + 1)
 *   - Most Frequent Character Count = maxCount
 *   - Replacements Needed = (r - l + 1) - maxCount
 * 
 * If (Replacements Needed > k), the current window is invalid. We must increment
 * 'l' to shrink the window until the condition ((r - l + 1) - maxCount <= k) holds.
 * 
 * Complexity:
 *   - Time Complexity: O(N) — Every character is visited at most twice (by 'r' and 'l').
 *   - Space Complexity: O(1) — Fixed array size of 26 for uppercase English letters.
 * ============================================================================
 */

class Longest_Repeating_Character_Replacement {
    public int characterReplacement(String s, int k) {
        // Frequency array to store occurrences of each uppercase letter ('A' to 'Z')
        int[] count = new int[26];
        
        int l = 0;             // Left boundary of the sliding window
        int maxCount = 0;      // Max frequency of any single character in the current window
        int maxLength = 0;     // Stores the maximum valid window length found so far

        // Expand the right boundary 'r' across the string
        for (int r = 0; r < s.length(); r++) {
            char currentChar = s.charAt(r);
            
            // Increment the count of the current character
            count[currentChar - 'A']++;
            
            // Track the highest frequency of a single character in the window
            maxCount = Math.max(maxCount, count[currentChar - 'A']);

            // Calculate replacements needed: (window_length - max_frequency)
            // If replacements needed exceed 'k', shrink window from the left
            while ((r - l + 1) - maxCount > k) {
                count[s.charAt(l) - 'A']--;
                l++;
            }

            // Update the maximum length recorded so far
            maxLength = Math.max(maxLength, r - l + 1);
        }

        return maxLength;
    }
}