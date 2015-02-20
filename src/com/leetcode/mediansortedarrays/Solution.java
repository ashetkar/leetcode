package com.leetcode.mediansortedarrays;

import java.util.ArrayList;

/**
 * 
 * There are two sorted arrays A and B of size m and n respectively. Find the
 * median of the two sorted arrays. The overall run time complexity should be
 * O(log (m+n)).
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
  int[][] aoa = new int[][] {
      { 3, 5, 7 }, { 2, 4, 6 },
      { 3, 5, 7 }, { 9, 11, 13},
      { 3, 5, 7 }, { 5, 6, 8, 10 },
      { 7, 5, 3 }, { 2, 4, 6 },
      { 7, 5, 3 }, { 8, 11 },
      { 7, 5, 3 }, { 5, 6, 8, 10 },
      { 7, 5, 3 }, { 6, 4, 2 },
      { 7, 5, 3 }, { 11, 8 },
      { 7, 5, 3 }, { 10, 8, 6, 5 }};

    for (int i = 0; i < aoa.length; i += 2) {
      System.out.println("Median of " + printArray(aoa[i]) + ", "
          + printArray(aoa[i + 1]) + ": "
          + new Solution().findMedianSortedArrays(aoa[i], aoa[i + 1]));
    }
    System.out.println("------");

    for (int i = 0; i < aoa.length; i += 2) {
      System.out.println("Median of " + printArray(aoa[i+1]) + ", "
          + printArray(aoa[i]) + ": "
          + new Solution().findMedianSortedArrays(aoa[i+1], aoa[i]));
    }
  }

  private static String printArray(int[] a) {
    StringBuilder sb = new StringBuilder("{ ");
    boolean first = true;
    for (int e : a) {
      if (!first) {
        sb.append(", ");
      }
      first = false;
      sb.append(e);
    }
    return sb.append(" }").toString();
  }

  public double findMedianSortedArrays(int A[], int B[]) {
    // handle empty array
    if (A.length == 0) {
      if (B.length % 2 == 0) {
        return (B[B.length/2 - 1] + B[B.length/2]) / 2d;
      } else {
        return B[B.length/2];
      }
    } else if (B.length == 0) {
      if (A.length % 2 == 0) {
        return (A[A.length/2 - 1] + A[A.length/2]) / 2d;
      } else {
        return A[A.length/2];
      }
    }

    boolean aAscending = A[0] <= A[A.length - 1];
    boolean bAscending = B[0] <= B[B.length - 1];

    if (aAscending) {
      if (bAscending) {
        if (A[A.length -1] <= B[0]) {
          return handleNoOverlap(A, B);
        } else if (B[B.length - 1] <= A[0]) {
          return handleNoOverlap(B, A);
        }
      }
      return mergeArrays(A, aAscending, B, bAscending);
    } else {
      if (!bAscending) {
        if (A[A.length -1] >= B[0]) {
          return handleNoOverlap(A, B);
        } else if (B[B.length - 1] >= A[0]) {
          return handleNoOverlap(B, A);
        }
      }
      return mergeArrays(A, aAscending, B, bAscending);
    }
  }

  // Both assumed to be either ascending or descending together.
  private double handleNoOverlap(int[] A, int[] B) {
    int total = A.length + B.length;
    int halfMark = total/2;
    if (total % 2 == 0) { // total length is even number
      if (A.length > halfMark) {
        return (A[halfMark - 1] + A[halfMark]) / 2d;
      } else if (A.length == halfMark) {
        return (A[A.length - 1] + B[0]) / 2d;
      } else {
        int idx = halfMark - A.length;
        return (B[idx - 1] + B[idx]) / 2d;
      }
    } else { // total length is odd number
      if (A.length > halfMark) {
        return A[halfMark];
      } else {
        return B[halfMark-A.length];
      }
    }
  }


  private double mergeArrays(int[] A, boolean aAscending, int[] B,
      boolean bAscending) {
    ArrayList<Integer> union = new ArrayList<Integer>();
    int a = aAscending ? 0 : A.length-1;
    int b = bAscending ? 0 : B.length-1;

    for (; ;) {
      if (union.size() > (A.length + B.length)/2) {
        break;
      }
      boolean aDone = (a >= A.length && aAscending) || (a < 0 && !aAscending);
      boolean bDone = (b >= B.length && bAscending) || (b < 0 && !bAscending);

      if (aDone && bDone) {
        break;
      }

      if (bDone || (!aDone && A[a] < B[b])) {
        union.add(A[a]);
        a = aAscending ? a+1 : a-1;
      } else if (!bDone) {
        union.add(B[b]);
        b = bAscending ? b+1 : b-1;
      }
    }
    int s = A.length + B.length; //union.size();
    return s % 2 == 0 ? (union.get((s / 2) - 1) + union.get(s / 2)) / 2d : union.get(s / 2);
  }

}
