//Question:986
//You are given two lists of closed intervals, firstList and secondList, where firstList[i] = [starti, endi] and secondList[j] = [startj, endj]. Each list of intervals is pairwise disjoint and in sorted order.
//
//Return the intersection of these two interval lists.
//
//A closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
//
//The intersection of two closed intervals is a set of real numbers that are either empty or represented as a closed interval. For example, the intersection of [1, 3] and [2, 4] is [2, 3].
//
// 
//
//Example 1:
//
//
//Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
//Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
//Example 2:
//
//Input: firstList = [[1,3],[5,9]], secondList = []
//Output: []
		
package Intervals;

import java.util.ArrayList;
import java.util.List;

public class IntervalListIntersections {
	public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
		if (firstList.length == 0 || secondList.length == 0) return new int[0][];
		
		int i = 0;
		int j = 0;
		List<int[]> list = new ArrayList<>();
		
		while (i < firstList.length && j < secondList.length) {
			int s1 = firstList[i][0];
			int e1 = firstList[i][1];
			int s2 = secondList[j][0];
			int e2 = secondList[j][1];
			
			if ((s1 <= s2 && s2 <= e1) || (s2 <= s1 && s1 <= e2)) {
				int s = Math.max(s1, s2);
				int e = Math.min(e1, e2);
				list.add(new int[] {s, e});
			}
			if (e2 > e1) i++;
			else j++;
		}
		return list.toArray(new int[list.size()][2]);
	}
}
