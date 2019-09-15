package algorithm.bakjun.basic1.ds1_practice.right_big_cnt;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
/**
 * https://www.acmicpc.net/problem/17299
 * @author tom
 * 오등큰
 */
public class Main {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.valueOf(br.readLine());
		int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
		Map<Integer, Long> counts = Arrays.stream(numbers).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));	

		Stack<Integer> stack = new Stack<>();
		for(int i = 0; i < N; i++) {
			stack.push(numbers[i]);
		}
		
		int[] res = new int[N];
		Arrays.fill(res, -1);
		
		int cur;
		for (int i = N-1; i >= 0; i--) {
			cur = numbers[i];
			
			while(!stack.empty() && (stack.peek() == cur || counts.get(stack.peek()) <= counts.get(cur))){
				stack.pop();
			}

			if(stack.empty()) {
				res[i] = -1;
			}
			else {
				res[i] = stack.peek();
			}
			
			stack.push(cur);
		}
		
		bw.write(Arrays.stream(res).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
		bw.close();br.close();
	}
}
