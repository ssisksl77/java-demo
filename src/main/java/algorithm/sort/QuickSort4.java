package algorithm.sort;

public class QuickSort4 {
	public static void main(String[] args) {
		int[] in = {3,4,5,76,7,2,23,4,234,1,2,2,1,1,4,6,7,8,9};
		solve(in);
		for (int i : in) {
            System.out.print(i + " ");
        }
	}

	private static void solve(int[] in) {
		quickSort(in, 0, in.length-1);
	}

	private static void quickSort(int[] in, int left, int right) {
		if(left < right) {
			int pivot = partition(in, left, right);
			quickSort(in, left, pivot-1);
			quickSort(in, pivot+1, right);
		}
	}

	private static int partition(int[] in, int left, int right) {
		int pivotValue = in[right];
		int newPivot = left;
		
		for (int i = left; i < right; i++) {
			if (pivotValue > in[i]) {
				int tmp = in[i];
				in[i] = in[newPivot];
				in[newPivot++] = tmp;
			}
		}
		
		int tmp = in[newPivot];
		in[newPivot] = in[right];
		in[right] = tmp;
		
		
		return newPivot;
	}
}
