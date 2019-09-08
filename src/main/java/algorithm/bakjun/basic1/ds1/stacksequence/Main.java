package algorithm.bakjun.basic1.ds1.stacksequence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * https://www.acmicpc.net/problem/1874
 * @author tom
 * 1부터 n까지의 수를 스택에 넣었다가 뽑아 늘어놓음으로써, 하나의 수열을 만들 수 있다. 
 * 이때, 스택에 push하는 순서는 반드시 오름차순을 지키도록 한다고 하자. 
 * 임의의 수열이 주어졌을 때 스택을 이용해 그 수열을 만들 수 있는지 없는지, 
 * 있다면 어떤 순서로 push와 pop 연산을 수행해야 하는지를 알아낼 수 있다. 
 * 이를 계산하는 프로그램을 작성하라.
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		
		int n = Integer.valueOf(br.readLine())
		,   num
		,	max = 0; // 현재 최대 스택에 넣었던 수.
		int stack[] = new int[n];
		int stackIndx = 0;
		StringBuilder sb = new StringBuilder();
		while(n-- > 0) {
			num = Integer.valueOf(br.readLine());
			if(num > max) {
				for (int i = max; i < num; i++) {
					stack[stackIndx++] = i+1;
					sb.append("+\n");
				}
			} else { // 숫자가 작거나 같으면 pop으로 빼내야 함.
				if (stack[stackIndx-1] != num) { // 지금 빼낼값이 다르면 어떻게 해야 하는
					System.out.println("NO");
					return;
				}
			}
			// 도달했으니 빼낸다.
			sb.append("-\n");
			stackIndx--;
			if (num > max) max = num;  // max 현재 최대값. num이 최대값보다 크면 num으로 바꾼다.
		}
		
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}
