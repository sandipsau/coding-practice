**Problem**
```text
Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.

A subarray is a contiguous non-empty sequence of elements within an array.

 

Example 1:

Input: nums = [1,1,1], k = 2
Output: 2
Example 2:

Input: nums = [1,2,3], k = 3
Output: 2
 

Constraints:

1 <= nums.length <= 2 * 104
-1000 <= nums[i] <= 1000
-107 <= k <= 107

Concept:

Step 1: Define Prefix Sum Clearly

We define: prefix(i)=nums[0]+nums[1]+...+nums[i]
So it is the sum from the beginning up to index i (inclusive).

Step 2: Write Two Prefix Sums
Let’s write two prefix sums:
- prefix(i)=nums[0]+nums[1]+...+nums[j]+nums[j+1]+...+nums[i]
- prefix(j)=nums[0]+nums[1]+...+nums[j]
Everything in prefix(j) is also inside prefix(i)

Step 3: Subtract Them
prefix(i)−prefix(j)

Substitute definitions:
(nums[0]+...+nums[j]+nums[j+1]+...+nums[i])−(nums[0]+...+nums[j])
The first parts cancel out:
nums[j+1]+nums[j+2]+...+nums[i]
And that is exactly the subarray from: (j+1..i)

So The Identity Is Proven: prefix(i)−prefix(j)=sum(j+1..i)

We want: sum(j+1..i)=k
Using the identity: prefix(i)−prefix(j)=k
=> prefix(j)=prefix(i)−k
=> So at index i, if we know how many previous prefix sums equal: prefix(i)−k
Then we know how many subarrays ending at i sum to k

```

**Solution**
```java
import java.util.*;

class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        freq.put(0, 1); // prefix sum 0 occurs once (empty prefix)

        int count = 0;
        int prefix = 0;

        for (int x : nums) {
            prefix += x;

            // number of previous prefixes that make current subarray sum to k
            count += freq.getOrDefault(prefix - k, 0);

            // record this prefix sum
            freq.put(prefix, freq.getOrDefault(prefix, 0) + 1);
        }

        return count;
    }
}
```