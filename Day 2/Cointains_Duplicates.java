import java.util.*;
class Cointains_Duplicates {
    public boolean hasDuplicate(int[] nums) {
         HashMap<Integer,Integer> m=new HashMap<>();
            int n=nums.length;
            for(int i=0;i<n;i++){
                if(m.containsKey(nums[i])) return true;
                else m.put(nums[i],1);
            }
            return false;
    }
}