package algorithm.meilprogramming;

/**
정렬(sort)된 정수 배열과 정수 n이 주어지면, 배열안에 n이 있는지 체크하시오. 시간복잡도를 최대한 최적화하시오.
Given a sorted integer array and an integer N, check if N exists in the array.

예제)
Input: [1, 2, 3, 7, 10], 7
Output: true

Input: [-5, -3, 0, 1], 0
Output: true

Input: [1, 4, 5, 6, 8, 9], 10
Output: false
 */
public class Test20 {
	public static void main(String[] args) {
		int[] arr1 = {1,2,3,7,10};
		int target1 = 7;
		
		System.out.println(solve(arr1, target1));
	}

	private static boolean solve(int[] arr, int target) {
		if (arr == null || arr.length == 0) return false;
		return bSearch(arr, target, arr.length / 2);
	}

	private static boolean bSearch(int[] arr, int target, int half) {
		if (arr[half] == target) {
			return true;
		}

		if (arr[half] > target) {
			if (half == 0) return false;
			half = (half + 0) / 2;
		} else {
			if (half > arr.length) return false;
			half = (half + arr.length) / 2;
		}

		return bSearch(arr, target, half);
	}
	
	// 풀이
	private static boolean binarySearch(int[] input, int n) {
		int start = 0;
		int end = input.length - 1;
		
		while(end >= start) {
			int middle = (start + end) / 2;
			if (input[middle] == 2) {
				return true;
			}
			
			if (input[middle] < n) {
				start = middle + 1;
			}
			
			if (input[middle] > n) {
				end = middle - 1;
			}
		}
		return false;
	}
}
