## Two Sum
```text
Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

 

Example 1:

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
Example 2:

Input: nums = [3,2,4], target = 6
Output: [1,2]
Example 3:

Input: nums = [3,3], target = 6
Output: [0,1]
 

Constraints:

2 <= nums.length <= 104
-109 <= nums[i] <= 109
-109 <= target <= 109
Only one valid answer exists.
 

Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?
```
**Solution**
```java
import java.util.*;
class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> seen = new HashMap<>();

        for (int i = 0; i < nums.length ; i++) {
            int need = target - nums[i];
            if (seen.containsKey(need)) {
                return new int[] {seen.get(need), i};
            }
            seen.put(nums[i], i);
        }
        return new int[]{-1,-1};
    }
}
```
**Time Complexities**
- Loop
    - It runs n times so baseline cost is O(n)
- Inside the loop
    - containsKey() → O(1) average
    - put() → O(1) average
    - get() → O(1) average
    - So for iteration we get O(1) + O(1) + O(1) = 3 X O(1) = O(1)
- Total = n X O(1) = O(n)

**Space Complexities**
- What extra space we are using
    - HashMap<> map = new HashMap<>();
    - In worst case we don't find a match so all n elements are added in the map
    - which makes the space complexities to O(n)

## Longest Substring Without Repeating Characters

**Problem**
```text
Given a string s, find the length of the longest substring without duplicate characters.

 

Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3. Note that "bca" and "cab" are also correct answers.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 

Constraints:

0 <= s.length <= 5 * 104
s consists of English letters, digits, symbols and spaces.
```

**Solution**
```java
// Solution using sliding window

import java.util.*;
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int left = 0, maxlength = 0;
        HashMap<Character, Integer> lastSeen = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (lastSeen.containsKey(c)){
                left = Math.max(left, lastSeen.get(c) + 1);
            }
            lastSeen.put(c, right);
            maxlength = Math.max(maxlength, right - left +1);
        }
        return maxlength;
    }
}
```
**Time Complexities**
- Loop will run for max n numbers so O(n)
- HashMap operation O(1)
- Math operation is constant O(1)
- Hence O(n)

**Space Complexities**
- HashMap can be max of n size if there are no repeated chars so O(n)

## Product of Array Except Self
**Problem**
```text
Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in O(n) time and without using the division operation.

 

Example 1:

Input: nums = [1,2,3,4]
Output: [24,12,8,6]
Example 2:

Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]
 

Constraints:

2 <= nums.length <= 105
-30 <= nums[i] <= 30
The input is generated such that answer[i] is guaranteed to fit in a 32-bit integer.
 

Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)
```
**Solution**
```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int[] answer = new int[length];
        int prefix = 1;
        int suffix = 1;
        
        // answer[i] holds product of all elements to the left of i
        for (int i = 0; i < length; i++){
            answer[i] = prefix;
            prefix *= nums[i];
        }

        // suffix holds product of all elements to the right of i
        for (int i = length -1; i >=0; i--) {
            answer[i] = answer[i] * suffix;
            suffix = suffix * nums[i]; 

        }
        return answer;
    }
}
```
**Time Complexities**
- First loop n times = O(n)
- Second loop n times = O(n)
- Total = 2 * O(n) = O(n)

**Space Complexities**
- O(1) for 2 variables if int[] is out of calculation
- if we had defined 2 arrays like int[] left and int[] right, then extra space would be O(n)

## Group Anagrams

```text
Given an array of strings strs, group the anagrams together. You can return the answer in any order.

 

Example 1:

Input: strs = ["eat","tea","tan","ate","nat","bat"]

Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

Explanation:

There is no string in strs that can be rearranged to form "bat".
The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.
Example 2:

Input: strs = [""]

Output: [[""]]

Example 3:

Input: strs = ["a"]

Output: [["a"]]

 

Constraints:

1 <= strs.length <= 104
0 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.

```

**Solution**
```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for(String s: strs) {
            char[] inputString = s.toCharArray();
            Arrays.sort(inputString);
            String key = new String(inputString);
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
```
**Time Complexities**
- Sorting array of size k = O(k log k) as 
    - comparison-based sorting requires: log k levels
    - at each level k operations
- This sorting happens for n numbers of string so
    - Total = O(n * k log k)  
- Java uses
    - Dual-pivot quicksort for primitives
    - TimSort for objects

 **Space Complexities**
 - Extra spaces for hashmap storing n keys and each key can be of k length = O(n * k)
 - temp char array of k size = O(k)
 - “Space is O(nk) for the map keys (sorted strings) plus O(k) temporary for sorting each string.”

**Faster Solution - This will work if the string consist of lower case. We are avoiding sorting**
```java
import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            int[] count = new int[26];

            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }

            String key = Arrays.toString(count);

            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(s);
        }

        return new ArrayList<>(map.values());
    }
}
```
**Time complexities**
- Adding chars to count array O(k) where k is number of char in each string
- This happens for number of strings in the array i.e. n
- Total = O(n * k) = O(nk) -> This is slightly faster than O(nk log k)
**Space complexities**
- O(n) for hashmap since keys are of constant size
- temp count O(1)
- “Space is O(n) for the hash map since the key is constant sized (26 counts). Temporary space is O(1).”

## Container With Most Water (2 pointer solution)

**Problem**

```text
You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).

Find two lines that together with the x-axis form a container, such that the container contains the most water.

Return the maximum amount of water a container can store.

Notice that you may not slant the container.
```

**Solution**
```java
class Solution {
    public int maxArea(int[] height) {
        int leftIndex = 0, rightIndex = height.length - 1;
        int max = 0;

        while (leftIndex < rightIndex) {
            int h = Math.min(height[leftIndex], height[rightIndex]);
            int w = rightIndex - leftIndex;
            max = Math.max(max, h * w);
            
            if (height[leftIndex] < height[rightIndex])
                leftIndex++;
            else
                rightIndex--;     

        }
        return max;
    }
}
```
**Time complexities**
- The while (left < right) loop runs while the pointers move inward.
- In each iteration, either left++ or right-- happens.
- Each pointer can move at most n−1 times total.
- So total iterations ≤ n−1, making runtime O(n).

**Space complexities**
- O(1) - Since there is no extra usage of space.

## 3Sum

**Problem**

```text
Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.

 

Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation: 
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].
Notice that the order of the output and the order of the triplets does not matter.
Example 2:

Input: nums = [0,1,1]
Output: []
Explanation: The only possible triplet does not sum up to 0.
Example 3:

Input: nums = [0,0,0]
Output: [[0,0,0]]
Explanation: The only possible triplet sums up to 0.
 

Constraints:

3 <= nums.length <= 3000
-105 <= nums[i] <= 105
```

**Solution**

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n-2; i++) {
            //Skipping duplicates
            if (i > 0 && nums[i] == nums[i-1]) continue;

            if (nums[i] > 0) break;
            
            // Optional optimization: if nums[i] > 0, remaining are >= nums[i], sum can't be 0
            int left = i+1, right = n-1; // To have triplet i, left, right
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right]; 
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    // 2) Skip duplicate second elements
                    while (left < right && nums[left] == nums[left - 1]) left++;

                    // 3) Skip duplicate third elements
                    while (left < right && nums[right] == nums[right + 1]) right--;
                } 
                else if (sum < 0) 
                    left++;
                else
                    right--;
            } 
        }
        return res;
    }
}
```
**Time complexities**
- Sorting O(nlogn)
- Outer Loop O(n)
- Inner 2 pointer scan n times O(n) = O(n^2)
- O(n log n) + O(n²) = O(n²) since n² grows faster than n log n

**Space Complexities**
- O(1) extra (ignoring output)
Sorting in Java uses some stack/implementation overhead, but interview answer is typically O(1) extra besides output.

## Remove Duplicates from Sorted Array

**Problem**
```text
Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same.

Consider the number of unique elements in nums to be k​​​​​​​​​​​​​​. After removing duplicates, return the number of unique elements k.

The first k elements of nums should contain the unique numbers in sorted order. The remaining elements beyond index k - 1 can be ignored.

Custom Judge:

The judge will test your solution with the following code:

int[] nums = [...]; // Input array
int[] expectedNums = [...]; // The expected answer with correct length

int k = removeDuplicates(nums); // Calls your implementation

assert k == expectedNums.length;
for (int i = 0; i < k; i++) {
    assert nums[i] == expectedNums[i];
}
If all assertions pass, then your solution will be accepted.
```

**Solution**
```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int len = nums.length; 
        int k = 1;
        for (int i = 1; i < len; i++) {
            if (nums[i] != nums[i-1]) {
                nums[k] = nums[i];
                k++;
            }
            
        }
        return k;
    }
}
```
**Time Complexitiies**
- O(n) since the iteration runs for max n-1 times
**Space Complexities**
- O(1)

## Minimum Size Subarray Sum

**Problem**
```text
Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.

 

Example 1:

Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem constraint.
Example 2:

Input: target = 4, nums = [1,4,4]
Output: 1
Example 3:

Input: target = 11, nums = [1,1,1,1,1,1,1,1]
Output: 0
 

Constraints:

1 <= target <= 109
1 <= nums.length <= 105
1 <= nums[i] <= 104
 

**Follow up: If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log(n)).**
```

**Solution 1 - Time Complexities = O(n)**
```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int minLen = Integer.MAX_VALUE;
        int n = nums.length;
        int left = 0;
        int sum = 0;

        for (int right = 0; right < n; right++) {
            sum = sum + nums[right];

            while(sum >= target){
                minLen = Math.min(minLen, right-left+1);
                sum = sum - nums[left];
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
```
**Time Complexities**
- O(n)
**Space Complexities**
- O(1) no extra space being used

**Solution 2 - Time complexities = O(n log n)**

```java
import java.util.*;

class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;

        // prefix sums: prefix[0] = 0, prefix[i] = sum of nums[0..i-1]
        int[] prefix = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + nums[i - 1];
        }

        int minLen = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int needed = target + prefix[i];

            int j = lowerBound(prefix, needed); // first index with prefix[j] >= needed
            if (j != -1) {
                minLen = Math.min(minLen, j - i);
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    // lower bound: first index where arr[idx] >= target
    private int lowerBound(int[] arr, int target) {
        int lo = 0, hi = arr.length - 1;
        int ans = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] >= target) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return ans;
    }
}
```
**Time complexities**
- Building prefix sums: O(n)
- For each i (n times), binary search prefix array: O(log n)
- Total: O(n log n)
**Space complexities**
- Space: O(n) for prefix array