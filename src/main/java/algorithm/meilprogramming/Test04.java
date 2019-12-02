package algorithm.meilprogramming;

import java.util.ArrayList;
import java.util.List;

/**
 * 정수(int)가 주어지면, 팰린드롬(palindrome)인지 알아내시오. 
 * 팰린드롬이란, 앞에서부터 읽으나 뒤에서부터 읽으나 같은 단어를 말합니다. 단, 정수를 문자열로 바꾸면 안됩니다.
 * Given an integer, check if it is a palindrome.

예제)
Input: 12345
Output: False

Input: -101
Output: False

Input: 11111
Output: True

Input: 12421
﻿Output: True
 * @author yuhnam
 *
 */
public class Test04 {
	public static void main(String[] args) {
		int input1 = 12345;
		int input2 = -101;
		int input3 = 11111;
		int input4 = 12421;
		
		solve(input1);
		solve(input2);
		solve(input3);
		solve(input4);
	}

	private static void solve(int n) {
		char[] chars = String.valueOf(n).toCharArray();
		boolean res = true;
		for (int i = 0 ; i < chars.length/2; i++) {
			if (chars[i] != chars[chars.length-1-i]) {
				res = false;
				break;
			}
		}
		System.out.println(res);
	}
	
	private static void solve1(int n) {
		if (n < 0) {
			System.out.println(false);
			return;
		}
		
		if (n == 0) {
			System.out.println(true);
		}
		
		List<Integer> list = new ArrayList<>();
		for(int r = 0; n > 0;) {
			r = n % 10;
			list.add(r);
			n = n / 10;
		}
		for (int i = 0; i < list.size() / 2; i++) {
			if (list.get(i) != list.get(list.size()-1-i)) {
				System.out.println(false);
				return;
			}
		}
		System.out.println(true);
	}
}
