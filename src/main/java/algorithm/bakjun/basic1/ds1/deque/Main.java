package algorithm.bakjun.basic1.ds1.deque;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;

/**
 * https://www.acmicpc.net/problem/10866
 * @author tom
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringBuilder sb = new StringBuilder();
		int n = Integer.valueOf(br.readLine());
		
		Deque<String> d = new LinkedList<>();
		String[] command;
		for (int i = 0; i < n; i++) {
			command = br.readLine().split(" ");
			switch(command[0]) {
			case "push_back" : 
				d.addLast(command[1]);
				break;
			case "push_front" : 
				d.addFirst(command[1]);
				break;
			case "pop_front" : 
				sb.append(d.peekFirst() != null ? d.pollFirst() : "-1").append("\n");
				break;
			case "pop_back" : 
				sb.append(d.peekLast() != null ? d.pollLast() : "-1").append("\n");
				break;
			case "size" : 
				sb.append(d.size()).append("\n");
				break;
			case "empty" : 
				sb.append(d.isEmpty() ? "1" : "0").append("\n");
				break;
			case "front" : 
				sb.append(d.peekFirst() != null ? d.peekFirst() : "-1").append("\n");
				break;
			case "back" : 
				sb.append(d.peekLast() != null ? d.peekLast() : "-1").append("\n");
				break;
			}
		}
		
		bw.write(sb.toString());
		br.close();bw.close();
	}
}
