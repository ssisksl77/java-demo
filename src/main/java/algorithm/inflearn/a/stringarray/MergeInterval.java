package algorithm.inflearn.a.stringarray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeInterval {
	public static void main(String[] args) {
		int[][] input = {{15,18}, {1,3}, {8,10}, {2,6}};
		// output = [[1,6],[8,10],[15,18]]
		Interval[] intervals = new Interval[input.length];
		for (int i = 0; i < input.length; i++) {
			intervals[i] = new Interval(input[i][0], input[i][1]);
		}
		
		solve(intervals);
		
	}
	
	private static void solve(Interval[] intervals) {
		Arrays.sort(intervals);
		
		List<Interval> list = new ArrayList<>();
		
		Interval merged = intervals[0];
		for (int i = 1; i < intervals.length; i++) {
			if (merged.b >= intervals[i].a) {
				merged.b = intervals[i].b;
			} else {
				list.add(merged);
				merged = intervals[i];
			}
		}
		
		list.add(merged);
		for (Interval i : list) {
			System.out.println(i);
		}
	}

	private static class Interval implements Comparable<Interval>{
		int a;
		int b;
		public Interval(int a, int b) {
			this.a = a;
			this.b = b;
		}
		
		@Override
		public int compareTo(Interval o) {
			return this.a - o.a;
		}

		@Override
		public String toString() {
			return "Interval [a=" + a + ", b=" + b + "]";
		}
		
	}
}
