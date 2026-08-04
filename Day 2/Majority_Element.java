import java.util.*;
class Majority_Element{
        public int majorityElement(int[] nums) {
            HashMap<Integer,Integer> m=new HashMap<>();
            int n=nums.length;
            for(int i=0;i<n;i++){
                if(m.containsKey(nums[i])) m.put(nums[i],m.get(nums[i])+1);
                else m.put(nums[i],1);
            }
            for(int key: m.keySet()){
                if(m.get(key)>(n/2)) return(key);
            }
            return -1;
        }
    }