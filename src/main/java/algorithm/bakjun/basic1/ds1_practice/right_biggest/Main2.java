package algorithm.bakjun.basic1.ds1_practice.right_biggest;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.valueOf(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.valueOf(st.nextToken());
		}
		Stack<Integer> s = new Stack<>();
		
		
		int[] res = new int[N];
		Arrays.fill(res, -1);
		for (int i = N-1; i >= 0; i--) {
			while(!s.empty() && s.peek() <= arr[i]) {
				s.pop();
			}
			
			if (s.empty()) {
				res[i] = -1;
			} else {  // 큰 녀석을 발견한 경우.
				res[i] = s.peek();
			}
			
			s.push(arr[i]);
		}
		
		bw.write(Arrays.stream(res).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
		bw.close();br.close();
	}
}