package algorithm.meilprogramming;

/**
 안녕하세요, 매일프로그래밍 이번주 문제입니다.
 주어진 정수가 4의 거듭제곱인지 확인하시오.
 Given an integer, check if it is a power of 4.
 */
public class Test24 {
    public static void main(String[] args) {
    	solve(100);
        
    }

	private static boolean solve(int n) {
		
		if (n == 0) return false;
		while(n != 1) {
			if (n % 4 != 0) return false;
			n = n / 4;
		}
		
		return true;
		
	}
	
	/**
	 * 1) 이진법으로 나타냈을때 1이 하나여야 합니다.
	 * 2) 1 뒤로 0의 갯수가 짝수여야 합니다.
	 * 10 = 2 = false
	 * 100 = 4 = true
	 * 1000 = 8 = false
	 * 10000 = 16 = true
	 * @param n
	 * @return
	 */

}
