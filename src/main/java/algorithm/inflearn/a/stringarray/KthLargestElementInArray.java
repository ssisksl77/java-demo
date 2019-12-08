package algorithm.inflearn.a.stringarray;

import java.util.Comparator;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;

public class KthLargestElementInArray {
    public static void main(String[] args) {
        int[] nums ={3,2,1,5,6,4};
        int k = 2;

        solve(nums, k);
    }

    private static void solve(int[] nums, int k) {
        Queue<Integer> q = new PriorityQueue<>(Comparator.reverseOrder());
        for (int n :
                nums) {
            q.offer(n);
        }

        Optional<Integer> resOptional = Optional.empty();
        for (int i = 0; i < k; i++) {
             resOptional = Optional.of(q.poll());
        }

        resOptional.ifPresent((i) -> {
            System.out.println(i);
        });
    }
}
