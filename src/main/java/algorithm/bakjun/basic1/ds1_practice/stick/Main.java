package algorithm.bakjun.basic1.ds1_practice.stick;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * https://www.acmicpc.net/problem/10799
 * @author yuhnam
 *	쇠막대기
 *  여러 개의 쇠막대기를 레이저로 절단하려고 한다.
 *  효율적인 작업을 위해서 쇠막대기를 아래에서 위로 겹쳐 놓고, 레이저를 위에서 수직으로 발사하여 쇠막대기들을 자른다.
 *  쇠막대기와 레이저의 배치는 다음 조건을 만족한다.
 *  각 쇠막대기는 자신보다 긴 쇠막대기 위에만 놓일 수 있다. (끝점 겹침X)
 *  각 쇠막대기를 자르는 레이저는 적어도 하나 존재한다.
 *  레이저는 어떤 쇠막대기의 양 끝점과도 겹치지 않는다.
 *  
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String parens = br.readLine().trim();
		int totalCnt  = 0;
		Stack<Character> stack = new Stack<>();
		
		for (int i = 0; i < parens.length(); i++) {
			char arr = parens.charAt(i);
			
			if (arr == '(') {
				stack.push(arr);
			} else if (arr == ')') {
				if (stack.peek() == '(') {
					stack.pop();
					totalCnt += stack.size();
				} else {
					stack.pop();  
					totalCnt++;  // 상자 끝이니까 하나 추가
				}
			}
		}
		System.out.println(totalCnt);
		br.close();
	}
}
