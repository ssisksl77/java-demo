package algorithm.sort;

/**
 * 퀵소트 연습
 */
public class QuickSort3 {
    public static void main(String[] args) {
        int[] ints = {3,4,2,1,1,1,2,3,4,5,6,88,9,0,4,5,9};

        solve(ints);
        for (int i : ints) {
            System.out.print(i + " ");
        }
    }

    private static void solve(int[] ints) {
        quickSort(ints, 0, ints.length-1);
    }

    private static void quickSort(int[] ints, int left, int right) {
        if (left < right) {
            int pivot = partition(ints, left, right);
            quickSort(ints, left, pivot-1);
            quickSort(ints, pivot+1, right);
        }

    }

    private static int partition(int[] ints, int left, int right) {
        int pivotIndex = right;
        int newPivot = left;
        for (int i = left; i < right; i++) {
            if (ints[i] < ints[pivotIndex]) {
                int temp = ints[i];
                ints[i] = ints[newPivot];
                ints[newPivot] = temp;
                newPivot++;
            }
        }

        int temp = ints[pivotIndex];
        ints[pivotIndex] = ints[newPivot];
        ints[newPivot] = temp;

        return newPivot;
    }
}
