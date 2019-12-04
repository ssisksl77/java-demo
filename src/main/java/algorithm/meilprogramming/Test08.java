package algorithm.meilprogramming;

import java.util.Arrays;
import java.util.List;

import asm_test.agent.C;

/**
 * 
정수 배열(int array)이 주어지면 두번째로 큰 값을 프린트하시오.
Given an integer array, find the second largest element.

예제)
Input: [10, 5, 4, 3, -1]
Output: 5

Input: [3, 3, 3]
Output: Does not exist.
 * 
 * @author yuhnam
 *
 */
public class Test08 {
	
	public static void main(String[] args) {
		int[] input1 = {10, 5, 4, 3, -1};
		int[] input2 = {3,3,3};
		solve(input1);
		solve(input2);
	}

	
	private static void solve(int[] input) {
		if (input.length < 2) printDoesNotexist();
		Arrays.sort(input);
		for (int i = input.length-1; i >= 1; i--) {
			if (input[i] != input[i-1]) {
				System.out.println(input[i-1]);
				return;
			}
		}
		
		printDoesNotexist();
	}


	private static void printDoesNotexist() {
		System.out.println("Does not exist.");
	}
}
