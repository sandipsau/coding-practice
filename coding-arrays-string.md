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