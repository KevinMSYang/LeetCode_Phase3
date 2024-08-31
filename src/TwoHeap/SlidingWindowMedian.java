//Question:480
//The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle values.
//
//For examples, if arr = [2,3,4], the median is 3.
//For examples, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5.
//You are given an integer array nums and an integer k. There is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
//
//Return the median array for each window in the original array. Answers within 10-5 of the actual value will be accepted.
//
// 
//
//Example 1:
//
//Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
//Output: [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
//Explanation: 
//Window position                Median
//---------------                -----
//[1  3  -1] -3  5  3  6  7        1
// 1 [3  -1  -3] 5  3  6  7       -1
// 1  3 [-1  -3  5] 3  6  7       -1
// 1  3  -1 [-3  5  3] 6  7        3
// 1  3  -1  -3 [5  3  6] 7        5
// 1  3  -1  -3  5 [3  6  7]       6
//Example 2:
//
//Input: nums = [1,2,3,4,2,3,1,4,2], k = 3
//Output: [2.00000,3.00000,3.00000,3.00000,2.00000,3.00000,2.00000]

package TwoHeap;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class SlidingWindowMedian {
    Queue<Integer> minHeap;
    Queue<Integer> maxHeap;
    public SlidingWindowMedian() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }

    private void add(int num) {
        if (maxHeap.isEmpty() || num < maxHeap.peek()) {
            maxHeap.add(num);
        }
        else {
            minHeap.add(num);
        }
        balance();
    }
    private void balance() {
        if (minHeap.size() + 1 < maxHeap.size()) {
            Integer element = maxHeap.poll();
            minHeap.add(element);
        }
        else if (minHeap.size() > maxHeap.size()) {
            Integer element = minHeap.poll();
            maxHeap.add(element);
        }
    }

    private void remove(int num) {
        if (num <= maxHeap.peek()) {
            maxHeap.remove(num);
        }
        else {
            minHeap.remove(num);
        }
        balance();
    }
    private double findMedian() {
        if (maxHeap.size() > minHeap.size()) return maxHeap.peek();
        if (minHeap.size() > maxHeap.size()) return minHeap.peek();
        return maxHeap.peek()/2.0 + minHeap.peek()/2.0;
    }
    public double[] medianSlidingWindow(int[] nums, int k) {
        int start = 0;
        double[] res = new double[nums.length - k + 1];

        for (int end = 0; end < nums.length; end++) {
            add(nums[end]);
            int size = end - start + 1;
            if (size == k) {
                res[start] = findMedian();
                remove(nums[start]);
                start++;
            }
        }    
        return res;
    }
}
