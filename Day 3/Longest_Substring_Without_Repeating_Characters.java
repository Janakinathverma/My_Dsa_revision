import java.util.HashSet;
class Longest_Substring_Without_Repeating_Characters {
    public int lengthOfLongestSubstring(String s) {
        int l = 0;
        int maxLength = 0;
        HashSet<Character> set = new HashSet<>();

        for (int r = 0; r < s.length(); r++) {
            char currentChar = s.charAt(r);
            while (set.contains(currentChar)) {
                set.remove(s.charAt(l));
                l++;
            }
            set.add(currentChar);
            maxLength = Math.max(maxLength, r - l + 1);
        }
        return maxLength;
    }
}
