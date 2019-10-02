package algorithm.sort;

import java.util.StringJoiner;

public class QuickSort {
	int[] arr;

	public QuickSort(int[] arr) {
		this.arr = arr;
	}
	
	public void sort() {
		sort(0, arr.length-1);
	}
	
	private void sort(int left, int right) {
		if (left < right) {
			int pi = partition(arr, left, right);
			
			sort(left, pi-1);
			sort(pi+1, right);
		}
	}
	
	private int partition(int arr[], int left, int right) {
		int pivot = arr[right];
		int i = (left - 1); // i는 pivot보다 작은 숫자의 카운트.
		// if current element is smaller than the pivot
		for (int j = left; j < right; j++) {
			System.out.println(j);
			if (arr[j] < pivot) {
				i++;
				int temp = arr[i];  // i는 큰값, j는 현재위치
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		
		// swap arr[i+1] and arr[right] (or pivot)
		int temp = arr[i+1];
		arr[i+1] = arr[right];
		arr[right] = temp;
		
		return i+1;
	}
	
	public void display() {
		StringJoiner joiner = new StringJoiner(",");
		for (int e : arr) {
			joiner.add(String.valueOf(e));
		}
		
		System.out.println(joiner.toString());
		
		
	}
	
	public static void main(String[] args) {
		int[] arr = new int[] {1,5,3,4,6,3,4,2,1,3,4,5,3,2,9,8,11,34,25,346,2,1,1};
		QuickSort qs = new QuickSort(arr);
		qs.sort();
		
		//qs.display();
	}
}
