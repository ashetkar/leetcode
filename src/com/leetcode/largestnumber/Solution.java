package com.leetcode.largestnumber;

import java.util.ArrayList;

/**
 * Given a list of non negative integers, arrange them such that they form the
 * largest number.
 * 
 * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 * 
 * Note: The result may be very large, so you need to return a string instead of
 * an integer.
 * 
 * @author ashetkar
 * 
 */
public class Solution {

  /**
   * Handles all integer values. Integer.MAX_VALUE is 2.147483647 * 10^9. That's
   * why value 9 below.
   */
  private static final int MAX_POWER = 9;

  private boolean illustrate = true;

  private ArrayList<Integer>[] solutionList = new ArrayList[10];

  public Solution() {
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    Solution ln = new Solution();
    System.out.println("Largest number: "
        + ln.largestNumber(new int[] { 0, 0 }));
  }

  public String largestNumber(int[] num) {
    boolean isNonZero = false;
    for (int i = 0; i < num.length; i++) {
      for (int j = 0; j <= MAX_POWER; j++) {
        int divider = (int) Math.pow(10, j);
        if (num[i] != 0) {
          isNonZero = true;
        }
        if (num[i] < (divider * 10)) {
          int idx = num[i]/divider;
          
          if (this.solutionList[idx] == null) {
            this.solutionList[idx] = new ArrayList<Integer>();
          }
          updateList(num[i], this.solutionList[idx]);
          break;
        }
      }
    }
    return isNonZero ? constructSolution() : "0";
  }

  private void updateList(int num, ArrayList<Integer> list) {
    if (list.isEmpty()) {
      list.add(num);
    } else {
      boolean done = false;
      for (int i = 0; i < list.size(); i++) {
        if (compare(num, list.get(i))) {
          list.add(i, num);
          done = true;
          break;
        }
      }
      if (!done) {
        list.add(num);
      }
    }
  }

  /**
   * Return true iff onetwo is greater than or equal to twoone.
   * 
   * @param one
   * @param two
   * @return
   */
  private boolean compare(int one, int two) {
    StringBuilder onetwo = new StringBuilder().append(one).append(two);
    StringBuilder twoone = new StringBuilder().append(two).append(one);
    return onetwo.toString().compareTo(twoone.toString()) >= 0;
  }
  
  private String constructSolution() {
    StringBuilder sb = new StringBuilder("");
    boolean first = true;
    for (int i = 9; i >= 0; i--) {
      if (solutionList[i] != null) {
        for (int j : solutionList[i]) {
          if (illustrate) {
            if (!first) {
              sb.append(",");
            }
            first = false;
          }
          sb.append(String.valueOf(j));
        }
      }
    }
    return sb.toString();
  }
}
