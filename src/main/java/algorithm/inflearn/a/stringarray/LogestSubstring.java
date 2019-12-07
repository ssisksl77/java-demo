package algorithm.inflearn.a.stringarray;

import java.util.HashMap;
import java.util.Map;

public class LogestSubstring {
    // 문자 두개 사이 길이
    public static void main(String[] args) {
        String str = "ccaabbb";
        // output 5
        solve(str);
    }

    private static void solve(String str) {
        int start = 0, end = 0, length = 0, counter = 0;
        Map<Character, Integer> map = new HashMap<>();

    }
}
