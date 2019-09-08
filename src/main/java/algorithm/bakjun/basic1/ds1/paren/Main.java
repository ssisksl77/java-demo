package algorithm.bakjun.basic1.ds1.paren;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

/**
 * 
 * @author tom
 * https://www.acmicpc.net/problem/9012
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int cnt = Integer.valueOf(br.readLine());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < cnt; i++) {
			String str = br.readLine();
			sb.append((isParen(str) ? "YES" : "NO" ) + "\n");
			
		}
		bw.write(sb.toString());
		
		bw.close();
		br.close();
		
	}
	
	static boolean  isParen(String parens) {
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < parens.length(); i++) {
			switch(parens.charAt(i)) {
			case '(' :
				stack.push('(');
				break;
			case ')' :
				if(stack.isEmpty()) {
					return false;
				} else {
					stack.pop();
				}
				break;
			}
		}
		
		if (!stack.isEmpty()) return false;
		
		return true;
	}
}
