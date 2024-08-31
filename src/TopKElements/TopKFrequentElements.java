//Question:347
//Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
//
//		 
//
//Example 1:
//
//Input: nums = [1,1,1,2,2,3], k = 2
//Output: [1,2]
//Example 2:
//
//Input: nums = [1], k = 1
//Output: [1]

package TopKElements;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i : nums) {
        	map.put(i, map.getOrDefault(i, 0) + 1);
        }
        
        Queue<Integer> heap = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b)); 
        
        for (int i : map.keySet()) {
        	heap.add(i);
        	if (heap.size() > k) {
        		heap.poll();
        	}
        }
        
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
        	res[i] = heap.poll();
        }
        return res;
    }
}
