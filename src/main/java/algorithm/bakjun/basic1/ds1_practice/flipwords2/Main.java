package algorithm.bakjun.basic1.ds1_practice.flipwords2;

import java.util.*;
import java.io.*;
/**
 * https://www.acmicpc.net/problem/17413
 * @author yuhnam
 *
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		Stack s = new Stack();
		
		
		String in = br.readLine();
		
		for (int i = 0; i < in.length(); i++) {
			if (' ' == in.charAt(i)) {
				while(!s.empty()) {
					sb.append(s.pop());
				}
				sb.append(' ');
			} else if ('<' == in.charAt(i)) {
				while(!s.empty()) {
					sb.append(s.pop());
				}
				
				while ('>' != in.charAt(i)) {
					sb.append(in.charAt(i++));
				} 
				sb.append('>');
			} else {
				s.push(in.charAt(i));
			}
		}
		
		while(!s.empty()) {
			sb.append(s.pop());
		}
		
		bw.write(sb.toString());
		bw.close(); br.close();
	}
}
