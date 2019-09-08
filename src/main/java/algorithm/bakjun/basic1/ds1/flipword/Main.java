package algorithm.bakjun.basic1.ds1.flipword;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 
 * @author tom
 * https://www.acmicpc.net/problem/9093
 */
public class Main {
	// public static Stack stack = new Stack();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int cnt = Integer.valueOf(br.readLine());
		String input = "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < cnt; i++) {
			input = br.readLine();
			String[] words = input.split(" ");
			
			for (int j = 0; j < words.length; j++) {
				words[j] = reverseStr(words[j]);
			}

			sb.append(String.join(" ", words));
			sb.append("\n");
		}
		wr.write(sb.toString());
		
		br.close();
		wr.close();
	}
	
	static String reverseStr(String str) {
		return new StringBuffer(str).reverse().toString();
	}
}
