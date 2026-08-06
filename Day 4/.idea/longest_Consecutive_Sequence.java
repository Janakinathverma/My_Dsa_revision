import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Problem: Longest Consecutive Sequence (LeetCode 128)
 * 
 * Task: Unsorted array mein se sabse lambi consecutive numbers ki sequence ki length nikalni hai.
 */
public class longest_Consecutive_Sequence {

    /**
     * APPROACH 1: Sorting + Two Pointers / Counter
     * --------------------------------------------
     * Time Complexity: O(N log N) - Arrays.sort() ki wajah se.
     * Space Complexity: O(1) ya O(log N) - Auxiliary space used by dual-pivot quicksort.
     * 
     * Limitations: 
     * 1. Array ka original order modify ho jata hai.
     * 2. LeetCode 128 ke strict O(N) time constraint ko satisfy NAHI karta.
     */
    public static int longestConsecutiveSorting(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        Arrays.sort(nums);

        int maxLen = 1;
        int currLen = 1;

        for (int i = 1; i < nums.length; i++) {
            // Duplicate numbers ko skip karna padega
            if (nums[i] == nums[i - 1]) {
                continue;
            }

            // Consecutive element mila (difference = 1)
            if (nums[i] - nums[i - 1] == 1) {
                currLen++;
            } else {
                currLen = 1; // Sequence toot gayi, reset counter
            }

            maxLen = Math.max(maxLen, currLen);
        }

        return maxLen;
    }

    /**
     * APPROACH 2: HashSet + Sequence Start Detection (OPTIMAL & BEST)
     * ----------------------------------------------------------------
     * Time Complexity: O(N) - Har element maximum 2 baar process hota hai.
     * Space Complexity: O(N) - HashSet mein unique elements store karne ke liye.
     * 
     * WHY IS THIS HASHING APPROACH BEST? (इंटरव्यू और LeetCode के लिए क्यों बेहतर है?)
     * 
     * 1. STRICT O(N) TIME COMPLEXITY:
     *    Sorting approach O(N log N) leti hai, jabki HashSet mein lookup (contains check) 
     *    O(1) average time mein hota hai. Iss wajah se pura algorithm linear O(N) mein chalta hai.
     * 
     * 2. SMART START-NODE DETECTION (!set.contains(num - 1)):
     *    Ye check guarantee karta hai ki inner 'while' loop SIRF sequence ke PEHLE element se 
     *    chale. For example, [1, 2, 3, 4] mein 2, 3, 4 ke liye while loop chalega hi nahi, 
     *    vo sirf 1 se chalega. Isse redundant operations 0 ho jate hain.
     * 
     * 3. AUTOMATIC DUPLICATE HANDLING:
     *    HashSet naturally saare duplicate elements ko remove kar deta hai, jisse extra 
     *    if-else checks nahi likhne padte.
     * 
     * 4. NO ARRAY MUTATION:
     *    Ye original array ke elements ka order change nahi karta.
     */
    public static int longestConsecutiveHashing(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int maxLen = 0;

        for (int num : set) {
            // Check: Kya 'num' sequence ka STARTING point hai?
            // Agar (num - 1) set mein hai, to 'num' starting point NAHI ho sakta.
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int currLen = 1;

                // Expand sequence only from the start node
                while (set.contains(currentNum + 1)) {
                    currentNum++;
                    currLen++;
                }

                maxLen = Math.max(maxLen, currLen);
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        int[] nums = {100, 4, 200, 1, 3, 2};

        System.out.println("Output via Sorting (O(N log N)): " + longestConsecutiveSorting(nums));
        System.out.println("Output via Hashing (O(N) Optimal): " + longestConsecutiveHashing(nums));
    }
}