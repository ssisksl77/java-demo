package algorithm.meilprogramming;
/**
정수 배열과 정수 k가 주어지면 모든 원소를 k칸씩 앞으로 옮기시오.
Given an array and an integer K, shift all elements in the array K times.

input: [1, 2, 3, 4, 5], k = 2
output: [3, 4, 5, 1, 2]

input: [0, 1, 2, 3, 4], k = 1
output: [1, 2, 3, 4, 0]
 */
public class Test21 {
	
	public static void main(String[] args) {
		int[] arr1 = {1,2,3,4,5};
		int k1 = 2;
		solve(arr1, k1);
		
		for (int i : arr1) {
			System.out.print(i + " ");
		}
		System.out.println();
		
		int[] arr2 = {0,1,2,3,4};
		int k2 = 1;
		solve(arr2, k2);
		for (int i : arr2) {
			System.out.print(i + " ");
		}
	}

	private static void solve(int[] arr, int k) {
		int[] buf = new int[k];
		for (int i = 0; i < k; i++) {
			buf[i] = arr[i];
		}
		for (int i = k; i < arr.length; i++) {
			arr[i-k] = arr[i];  
		}
		for (int i = 0; i < k; i++) {
			arr[arr.length-k+i] = buf[i];
		}
	}
	
}
