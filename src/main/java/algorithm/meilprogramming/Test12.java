package algorithm.meilprogramming;

/**
 문자열 배열(string array)이 주어지면, 제일 긴 공통된 접두사(prefix)의 길이를 찾으시오.
 Given an array of strings, find the longest common prefix of all strings.

 예제)
 Input: [“apple”, “apps”, “ape”]
 Output: 2 // “ap”

 Input: [“hawaii”, “happy”]
 Output: 2 // “ha”

 Input: [“dog”, “dogs”, “doge”]
 Output: 3 // “dog”
 */
public class Test12 {
    public static void main(String[] args) {
        String[] strs1 = { "apple", "apps", "ape"};
        String[] strs2 = { "hawaii", "happy"};
        String[] strs3 = { "dog", "dogs", "doge"};

        solve(strs1);
        solve(strs2);
        solve(strs3);
    }

    private static void solve(String[] strs) {
        StringBuilder sb = new StringBuilder();
        int len = Integer.MAX_VALUE;
        for(String str : strs) {
            if (len > str.length()) {
                len = str.length();
            }
        }


        for (int i = 0; i < len; i++) {
            if (isEquals(strs, i)) {
                sb.append(strs[0].charAt(i));
            }
        }

        System.out.println(sb.toString());
    }

    private static boolean isEquals(String[] strs, int i) {
        char c = strs[0].charAt(i);
        for (String str : strs) {
            if (c != str.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}
