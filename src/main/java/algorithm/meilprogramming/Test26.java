package algorithm.meilprogramming;

import java.util.HashSet;
import java.util.Set;

/**
 * 1~N까지 있는 정수 배열에 원소 하나가 없어졌습니다. 없어진 원소의 값을 구하시오.
 * Given an integer array of 1~N except one number, find the missing integer.
 * @author yuhnam
 *
 */
public class Test26 {
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5,6,7,8,9,11};
		solve(arr);
	}

	private static void solve(int[] arr) {
		Set<Integer> set = new HashSet<>();
		for(int n : arr) {
			set.add(n);
		}
		for (int i = 1; i < arr.length+2; i++) {
			if (!set.contains(i)) {
				System.out.println(i);
				return;
			}
		}
	}
}
