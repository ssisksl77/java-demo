package algorithm.bakjun.basic1.ds1.editor;

import java.io.*;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/1406
 * @author tom
 * 시간초과...
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		char[] chars= br.readLine().toCharArray();
		
		List<Character> list = new LinkedList<>();
		for(char c : chars) {
			list.add(c);
		}
		int cursor = chars.length;
		
		int num = Integer.valueOf(br.readLine());
		int rightLimit = chars.length-1;
		int leftLimit = 0;
		String[] command;
		for (int i = 0; i < num; i++) {
			command = br.readLine().split(" ");
			
			switch(command[0]) {
			case "L":
				if (cursor > leftLimit)	cursor--; 
				break;
			case "D":
				if (cursor < rightLimit) cursor++;
				break;
			case "B":
				if (cursor > 0) list.remove(cursor-1);
				break;
			case "P": 
				list.add(cursor, command[1].charAt(0));
				break;
			}
		}
		for(Character c : list) {
			sb.append(c);
		}
		bw.write(sb.toString());
		bw.close(); br.close();
	}
}	
