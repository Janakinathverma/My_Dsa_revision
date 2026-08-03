import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ThreeSum {
    /*
     * =========================================================================
     * PROBLEM: 3Sum (LeetCode 15)
     * =========================================================================
     * APPROACH: Sorting + Fixed Pointer + Two Pointers
     * TIME COMPLEXITY:  O(N^2) -> Array sort karne me O(N log N) lagta hai,
     *                              aur nested two-pointer loop O(N^2) leta hai.
     * SPACE COMPLEXITY: O(1)   -> Extra space nahi lagti (excluding result list).
     * 
     * CORE STRATEGY:
     * 1. Array ko sort karte hain taaki Two-Pointer technique apply kar sakein.
     * 2. Loop ke zariye 1st element `nums[i]` ko fix karte hain.
     * 3. Baki ke do elements ke liye standard Two-Pointer strategy chalate hain:
     *    nums[i] + nums[left] + nums[right] == 0
     * 4. IMPORTANT: Duplicates ko carefully skip karte hain teeno pointers par
     *    taaki output mein unique triplets hi aayein.
     * =========================================================================
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        // Step 1: Array Ko Sort Karo
        // Sorting zaroori hai duplicates skip karne aur two-pointers shift karne ke liye.
        Arrays.sort(nums);

        // Outer loop runs till (length - 2) because we need at least 3 elements
        for (int i = 0; i < nums.length - 2; i++) {

            // Optimization/Boundary Check:
            // Jab pehla fixed element hi > 0 ho jayega, toh sum kabhi 0 nahi ho sakta
            // kyunki array sorted hai.
            if (nums[i] > 0) break;

            // EDGE CASE 1: Duplicate Fixed Pointers Skip Karo
            // Agar current fixed element pichle element jaisa hi hai, toh skip karo.
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // Step 2: Two Pointers Initialization
            int left = i + 1;                  // Fixed element ke just aage wala index
            int right = nums.length - 1;       // Array ka last index

            // Step 3: Two-Pointer Search Loop
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    // Valid triplet mil gaya!
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // EDGE CASE 2: Duplicate Inner Elements Skip Karo
                    // Duplicate left pointers ko skip karo
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // Duplicate right pointers ko skip karo
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    // Move pointers to search next unique combination
                    left++;
                    right--;

                } else if (sum < 0) {
                    // Sum chhota hai (0 se kam), isko bada karne ke liye left pointer ko aage badhao
                    left++;
                } else {
                    // Sum bada hai (0 se zyada), isko chhota karne ke liye right pointer ko pichhe lao
                    right--;
                }
            }
        }

        return result;
    }
}