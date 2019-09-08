package algorithm.bakjun.basic1.ds1.flipword;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

/**
 * 
 * @author tom
 * 스택을 이용해야 하는 문제로 보여서 스택으로 풀어봄.
 */
public class Main2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int cnt = Integer.valueOf(br.readLine());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < cnt; i++) {
			String str = br.readLine();
			String[] words = str.split(" ");
			
			for (int j = 0; j < words.length; j++) {
				words[j] = reverseStr(words[j]);
			}
			sb.append(String.join(" ", words)).append("\n");
		}
		bw.write(sb.toString());
		
		bw.close();
		br.close();
	}

	private static String reverseStr(String string) {
		Stack stack = new Stack();
		stack.push(string);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < string.length(); i++) {
			stack.push(string.charAt(i));
		}
		for (int i = 0; i < string.length(); i++) {
			sb.append(stack.pop());
		}
		return sb.toString();
	}
}
