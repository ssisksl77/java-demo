package algorithm.meilprogramming;
/**
O(n log n)시간 복잡도를 가진 정수 배열 정렬 알고리즘을 구현하시오.
Implement an O(n log n) time complexity sorting algorithm.

예제)
Input: [3, 1, 5, 6]
Output: [1, 3, 5, 6]
 */
public class Test19 {
	public static void main(String[] args) {
		int[] arr = {3,1,5,6};
		solve(arr);
		for(int a : arr) {
			System.out.println(a);
		}
	}

	private static void solve(int[] arr) {
		quickSort(arr, 0, arr.length-1);
	}

	private static void quickSort(int[] arr, int left, int right) {
		if (left < right) {
			int pivot = partition(arr, left, right);
			quickSort(arr, left, pivot-1);
			quickSort(arr, pivot+1, right);
		}
	}

	private static int partition(int[] arr, int left, int right) {
		int pivot = right;
		int newPivot = left;
		
		for (int i = left; i < right; i++) {
			if (arr[pivot] > arr[i]) {
				int tmp = arr[newPivot];
				arr[newPivot] = arr[i];
				arr[i] = tmp;
				newPivot++;
			}
		}
		
		int tmp = arr[pivot];
		arr[pivot] = arr[newPivot];
		arr[newPivot] = tmp;
		
		return newPivot;
	}
}
