package algorithm.meilprogramming;

import java.util.ArrayList;
import java.util.List;

/**
String이 주어지면, 중복된 char가 없는 가장 긴 서브스트링 (substring)의 길이를 찾으시오. 
Given a string, find the longest substring that does not have duplicate characters.

예제)
Input: “aabcbcbc”
Output: 3 // “abc”

Input: “aaaaaaaa”
Output: 1 // “a”

Input: “abbbcedd”
﻿Output: 4 // “bced”
 */
public class Test09 {
	public static void main(String[] args) {
		String input1 = "aabcbcbc";
		solve(input1);
	}

	// 같으면 하나뺀다. 같은거 나올 때까지 뺀다. 중복으로 있을 리가 없다. 그건 이전 인풋에서 처리해야함.
	private static void solve(String input) {
		char[] c = input.toCharArray();
		int res = Integer.MIN_VALUE;
		List<Character> list = new ArrayList<>();
		
		for (int i = 0; i < c.length; i++) {
			if(list.contains(c[i])) {
				list = list.subList(c[i], list.size());
				System.out.println(list);
				list.add(c[i]);
			}
		}
	}
}
