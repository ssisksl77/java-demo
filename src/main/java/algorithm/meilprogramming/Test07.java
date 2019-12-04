package algorithm.meilprogramming;

/**
주어진 string 에 모든 단어를 거꾸로 하시오.
Reverse all the words in the given string.

예제)
Input: “abc 123 apple”
Output: “cba 321 elppa”
 * @author yuhnam
 *
 */
public class Test07 {
	
	public static void main(String[] args) {
		String input = "abc 123 apple";
		
		solve(input);
	}

	private static void solve(String input) {
		String[] strs = input.split(" ");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < strs.length; i ++) {
			for (int j = strs[i].length()-1; j >= 0; j--) {
				sb.append(strs[i].charAt(j));
			}
			sb.append(" ");
		}
		System.out.println(sb.toString());
	}
}
