package com.leetcode.twosum;

import java.util.HashMap;

/**
 * Given an array of integers, find two numbers such that they add up to a
 * specific target number.
 * 
 * The function twoSum should return indices of the two numbers such that they
 * add up to the target, where index1 must be less than index2. Please note that
 * your returned answers (both index1 and index2) are not zero-based.
 * 
 * You may assume that each input would have exactly one solution.
 * 
 * Input: numbers={2, 7, 11, 15}, target=9 Output: index1=1, index2=2
 * 
 * @author ashetkar
 * 
 */
public class Solution {

  public Solution() {
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    int[] ans = new Solution().twoSum(new int[]{22, -5, 2, 7, 14, 2, 7, 11, 15}, 9);
    System.out.println("Solution: " + ans[0] + ", " + ans[1]);
  }

  public int[] twoSum(int[] numbers, int target) {
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    map.put(numbers[0], 0);
    for (int i = 1; i < numbers.length; ++i) {
      Integer integer = map.get(target - numbers[i]);
      if (integer != null) {
        return new int[] {integer+1, i+1};
      } else {
        map.put(numbers[i], i);
      }
    }
    return null;
  }
}
