import java.util.*;
class Prime_Frequency {
    boolean checkPrime(int n){
        if(n==0 || n==1) return false;
        int c=0;
       for(int i=2;i<=(Math.sqrt(n));i++){
        if(n%i==0) c++;
       }
       return(c==0);
    }
    public boolean checkPrimeFrequency(int[] nums) {
         HashMap<Integer,Integer> m=new HashMap<>();
            int n=nums.length;
            for(int i=0;i<n;i++){
                if(m.containsKey(nums[i])) m.put(nums[i],m.get(nums[i])+1);
                else m.put(nums[i],1);
            }
            for (int key : m.keySet()) {
               if (checkPrime(m.get(key)))  return true; 
}
return false;
    }
}