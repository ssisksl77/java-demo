package algorithm.sort;

public class QuickSort2 {
    public static void main(String[] args) {
        int[] a = {3,4,2,1,3,2,1,9,8,5,3,4};
        quicksort(a);
        for(int i : a) System.out.print(i + " ");
    }

    private static void quicksort(int[] a) {
        quicksortHelper(a, 0, a.length-1);

    }

    private static void quicksortHelper(int[] a, int l, int r) {
        if (l < r) {
            int pivot = partition(a, l, r);
            quicksortHelper(a, l, pivot-1);
            quicksortHelper(a, pivot+1, r);
        }
    }

    // 오른쪽은 pivot으로 본다.
    private static int partition(int[] a, int l, int r) {
        int pivot = a[r];
        int newPivot = l;

        for (int i = l; i < r; i++) {
            if (a[i] < pivot) {
                int tmp = a[i];
                a[i] = a[newPivot];
                a[newPivot] = tmp;
                newPivot++;
            }
        }

        int tmp = a[r];
        a[r] = a[newPivot];
        a[newPivot] = tmp;

        return newPivot;
    }
}
