package algorithm.bakjun.basic1.ds1.queue;

import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.valueOf(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		Deque<String> q = new LinkedList<>();
		String[] command;
		for (int i = 0; i < n; i++) {
			command = br.readLine().split(" ");
			switch(command[0]) {
			case "push":
				q.add(command[1]);
				break;
			case "pop":
				if (q.isEmpty()) {
					sb.append("-1\n");
				} else {
					sb.append(q.pollFirst() + "\n");
				}
				bw.flush();
				break;
			case "size":
				sb.append(q.size() + "\n");
				break;
			case "empty":
				sb.append(q.isEmpty() ? "1" : "0").append("\n");
				break;
			case "front":
				sb.append(q.peekFirst() != null ? q.peekFirst() : "-1").append("\n");
				break;
			case "back":
				sb.append(q.peekLast() != null ? q.peekLast() : "-1").append("\n");
				break;
			}
		}
		bw.write(sb.toString());
		bw.close();br.close();
	}
}
