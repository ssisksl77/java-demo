package algorithm.bakjun.basic1.ds1_practice.right_biggest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * https://www.acmicpc.net/problem/17298
 * @author tom
 * 오큰수, 일반 브루트포스로 풀기 -> 시간초과.
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.valueOf(br.readLine());
		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
		int[] res = new int[N];
		Arrays.fill(res, -1);
		
		for (int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) {
				if (arr[i] < arr[j]) {
					res[i] = arr[j];
					break;
				}
			}
		}
		
		bw.write(Arrays.stream(res).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
		br.close();
		bw.close();
	}
}
