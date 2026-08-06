import java.util.Arrays;

/**
 * Problem: Permutation in String (LeetCode 567)
 * 
 * Task: 
 * Check karo ki kya string `s2` ke andar `s1` ka koi bhi permutation 
 * as a substring exist karta hai ya nahi.
 * 
 * Approach: Sliding Window + Frequency Array (Fixed Window Size = s1.length())
 * 
 * Time Complexity:  O(N2) - Where N2 is the length of s2.
 * Space Complexity: O(1)  - Constant space for frequency arrays of size 26.
 */
class PermutationInString {

    /**
     * APPROACH 1: Sliding Window with Frequency Array Comparison (Clean & Standard)
     * -----------------------------------------------------------------------------
     * s1 ki length fixed hai (say `k`). Toh hum s2 mein fixed size `k` ki window ko slide karenge.
     * Jab dono window ki character frequency matching ho jayegi, tab permutation mil jayegi.
     */
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();

        // Edge Case: Agar s1 ki length s2 se badi hai, to s2 mein permutation possible hi nahi.
        if (len1 > len2) {
            return false;
        }

        // Frequency arrays for lowercase English letters ('a' to 'z')
        int[] s1Count = new int[26];
        int[] s2Count = new int[26];

        // Step 1: Pehle window (length = len1) ke characters ko count karo
        for (int i = 0; i < len1; i++) {
            s1Count[s1.charAt(i) - 'a']++;
            s2Count[s2.charAt(i) - 'a']++;
        }

        // Check if initial window matches
        if (Arrays.equals(s1Count, s2Count)) {
            return true;
        }

        // Step 2: Sliding Window logic over s2
        // Right pointer add karo, Left pointer remove karo
        for (int i = len1; i < len2; i++) {
            // Naya character window me include karo
            s2Count[s2.charAt(i) - 'a']++;
            
            // Window se bahar nikla naya character remove karo (left character)
            s2Count[s2.charAt(i - len1) - 'a']--;

            // Har slide ke baad frequency match check karo
            if (Arrays.equals(s1Count, s2Count)) {
                return true;
            }
        }

        return false;
    }

    /**
     * APPROACH 2: Sliding Window with Matches Count (Optimized O(N2) with no array comparison)
     * ----------------------------------------------------------------------------------------
     * Arrays.equals(s1Count, s2Count) har bar 26 operations karta hai. 
     * Matches counter (0 to 26) track karke variable speed lookup O(1) kar sakte hain.
     */
    public boolean checkInclusionOptimized(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] s1Count = new int[26];
        int[] s2Count = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            s1Count[s1.charAt(i) - 'a']++;
            s2Count[s2.charAt(i) - 'a']++;
        }

        int matches = 0;
        for (int i = 0; i < 26; i++) {
            if (s1Count[i] == s2Count[i]) {
                matches++;
            }
        }

        int l = 0;
        for (int r = s1.length(); r < s2.length(); r++) {
            if (matches == 26) return true;

            // Add right character to window
            int rightIdx = s2.charAt(r) - 'a';
            s2Count[rightIdx]++;
            if (s1Count[rightIdx] == s2Count[rightIdx]) {
                matches++;
            } else if (s1Count[rightIdx] + 1 == s2Count[rightIdx]) {
                matches--;
            }

            // Remove left character from window
            int leftIdx = s2.charAt(l) - 'a';
            s2Count[leftIdx]--;
            if (s1Count[leftIdx] == s2Count[leftIdx]) {
                matches++;
            } else if (s1Count[leftIdx] - 1 == s2Count[leftIdx]) {
                matches--;
            }

            l++;
        }

        return matches == 26;
    }
}