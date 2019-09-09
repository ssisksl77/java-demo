package algorithm.bakjun.basic1.ds1.josephus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://www.acmicpc.net/problem/1158
 * @author tom
 * input 7 3 
 * output <3 6 2 7 5 1 4>
 */
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		Queue<Integer> q = new LinkedList<>();
		
		String[] str = br.readLine().split(" ");
		IntStream.range(1, Integer.valueOf(str[0])+1).forEach((e) -> {
			q.add(e);
		});
		int jump = Integer.valueOf(str[1]);
		
		ArrayList<Integer> arr = new ArrayList<>();
		while (!q.isEmpty()) {
			for (int i = 0; i < jump-1; i++) {
				q.add(q.poll());
			}
			arr.add(q.poll());
		}
		bw.write(arr.stream().map(String::valueOf).collect(Collectors.joining(", ", "<", ">")));  //  
		bw.close();br.close();
	}
}
