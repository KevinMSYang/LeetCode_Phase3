//Question:451
//Given a string s, sort it in decreasing order based on the frequency of the characters. The frequency of a character is the number of times it appears in the string.
//
//Return the sorted string. If there are multiple answers, return any of them.
//
// 
//
//Example 1:
//
//Input: s = "tree"
//Output: "eert"
//Explanation: 'e' appears twice while 'r' and 't' both appear once.
//So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
//Example 2:
//
//Input: s = "cccaaa"
//Output: "aaaccc"
//Explanation: Both 'c' and 'a' appear three times, so both "cccaaa" and "aaaccc" are valid answers.
//Note that "cacaca" is incorrect, as the same characters must be together.
//Example 3:
//
//Input: s = "Aabb"
//Output: "bbAa"
//Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.
//Note that 'A' and 'a' are treated as two different characters.

package TopKElements;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SortCharactersByFrequency {
	public String frequencySort(String s) {
		if (s == null) return null;
		
		Map<Character, Integer> map = new HashMap<>();
		for (char c : s.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		
		Map<Integer, List<Character>> hm = new HashMap<>();
		for (Character cur : map.keySet()) {
			List<Character> list = new LinkedList<>();
			int freq = map.get(cur);
			hm.put(freq, list);
		}
		
		int max = 0;
		for (Character cur : map.keySet()) {
			int freq = map.get(cur);
			hm.get(freq).add(cur);
			max = Math.max(freq, max);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = max; i > 0; i--) {
			if (!hm.containsKey(i)) continue;
			List<Character> list = hm.get(i);
			for (Character cur : list) {
				for (int j = 0; j < i; j++) {
					sb.append(cur);
				}
			}
		}
		return sb.toString();
	}
}
