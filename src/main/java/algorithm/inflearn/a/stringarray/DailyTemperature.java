package algorithm.inflearn.a.stringarray;

import java.util.Stack;
import java.util.StringJoiner;

/**
 * 
 */
public class DailyTemperature {
	public static void main(String[] args) {
		int[] nums = {73, 74, 75, 71, 69, 72, 76, 73};
		//output 1 1 4 2 1 1 0 0
		
		solve(nums);
		
	}

	private static void solve(int[] nums) {
		Stack<Integer> stack = new Stack<>();
		int[] result = new int[nums.length];
		
		for (int i = 0; i < nums.length; i++) {
			while(!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
				int index = stack.pop();
				result[index] = i - index;
			}
			stack.push(i);
		}
		
		StringJoiner sj = new StringJoiner(" ");
		for (int r : result) {
			sj.add(String.valueOf(r));
		}
		System.out.println(sj.toString());
	}
}
