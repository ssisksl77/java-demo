package algorithm.bakjun.basic1.ds1.editor;

import java.util.*;
import java.io.*;
/**
 * 
 * @author tom
 *	품... 오래도 걸렸다.
 */
public class Main2 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		Stack<Character> stack1 = new Stack<>();
		Stack<Character> stack2 = new Stack<>();
		
		String str = br.readLine();;
		for (int i = 0; i < str.length(); i++) {
			stack1.push(str.charAt(i));
		}
		int n = Integer.valueOf(br.readLine());
		String command;
		for (int i = 0 ; i < n; i++) {
			command = br.readLine();
			switch(command.charAt(0)) {
			case 'L' :
				if (!stack1.isEmpty()) {
					stack2.size();
					stack2.push(stack1.pop());
					stack2.size();
				}
				break;
			case 'D' :
				if (!stack2.isEmpty()) {
					stack1.size();
					stack1.push(stack2.pop());
					stack1.size();
				}
				break;
			case 'B' :
				if (!stack1.isEmpty()) {
					stack1.pop();
				}
				break;
			case 'P' : 
				stack1.push(command.charAt(2));
				break;
			}
		}
		StringBuffer sb = new StringBuffer();

		n = 0;
		while(n < stack1.size()) {
			sb.append(stack1.elementAt(n++));
		}
		while(!stack2.isEmpty()) {
			sb.append(stack2.pop());
		}
		
		bw.write(sb.toString());
		bw.close();br.close();
	}
}
