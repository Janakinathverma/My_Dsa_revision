import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class Two_sum {

    /*
     * =========================================================================
     * PATTERN 1: Two Sum II (Sorted Array)
     * =========================================================================
     * Approach: Two Pointers
     * Time Complexity: O(N)  -> Array ko ek hi pass mein scan karte hain.
     * Space Complexity: O(1) -> Extra space allocate nahi hoti.
     *
     * CONCEPT EXPLANATION:
     * 1. Kyunki array already SORTED hai, hum minimum value (left = 0) aur
     *    maximum value (right = n-1) se start karte hain.
     * 2. Calculate karte hain current sum = nums[left] + nums[right].
     * 3. Three Cases:
     *    a) sum == target: Answer mil gaya! (1-indexed return karne ke liye +1 add karte hain).
     *    b) sum < target: Sum chhota hai, isko bada karne ke liye left pointer ko aage badhate hain (left++).
     *    c) sum > target: Sum bada hai, isko chhota karne ke liye right pointer ko pichhe khisakate hain (right--).
     * 4. Yeh search space ko binary search jaisa efficiently reduce karta hai.
     */
    public int[] twoSumSortedTwoPointers(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        // Loop runs strictly while left < right (since index1 != index2)
        while (left < right) {
            int currentSum = nums[left] + nums[right];

            if (currentSum == target) {
                // 1-based indexing as required by LeetCode 167
                return new int[]{left + 1, right + 1};
            } else if (currentSum < target) {
                left++;  // Needs a larger sum
            } else {
                right--; // Needs a smaller sum
            }
        }

        return new int[]{-1, -1};
    }


    /*
     * =========================================================================
     * PATTERN 2: Two Sum I (Unsorted Array)
     * =========================================================================
     * Approach: HashMap (One-Pass Lookup)
     * Time Complexity: O(N)  -> Array ke elements ko ek baar iterate karte hain.
     * Space Complexity: O(N) -> HashMap mein elements store karne ke liye.
     *
     * CONCEPT EXPLANATION:
     * 1. Jab array SORTED NAHI hota, toh Two-Pointer technique kaam nahi karti.
     * 2. Har element `x` ke liye, hum check karte hain ki kya `target - x`
     *    (yani required complement) humne pehle dekha hai ya nahi.
     * 3. Hum ek HashMap maintain karte hain: Key = Array Element, Value = Index.
     * 4. Steps:
     *    a) Loop chalate hain index `i` par jahan current element = `nums[i]`.
     *    b) `complement = target - nums[i]` calculate karte hain.
     *    c) Agar complement map mein mil jata hai -> Answer mil gaya: [map.get(complement), i].
     *    d) Agar nahi milta -> Current element aur uska index map mein daal dete hain.
     */
    public int[] twoSumUnsortedHashMap(int[] nums, int target) {
        // Map to store value -> index mapping
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            // Check if complement is already stored in map
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            // Put current element into map
            map.put(nums[i], i);
        }

        return new int[]{-1, -1};
    }

    // Driver Code for Testing & Verification
    public static void main(String[] args) {
        Two_sum solver = new Two_sum();

        // Testing Two Sum II (Sorted Array)
        int[] sortedNums = {2, 7, 11, 15};
        int target1 = 9;
        int[] result1 = solver.twoSumSortedTwoPointers(sortedNums, target1);
        System.out.println("Two Sum II (Sorted, 1-indexed): " + Arrays.toString(result1));
        // Output: [1, 2]

        // Testing Two Sum I (Unsorted Array)
        int[] unsortedNums = {3, 2, 4};
        int target2 = 6;
        int[] result2 = solver.twoSumUnsortedHashMap(unsortedNums, target2);
        System.out.println("Two Sum I (Unsorted, 0-indexed): " + Arrays.toString(result2));
        // Output: [1, 2]
    }
}