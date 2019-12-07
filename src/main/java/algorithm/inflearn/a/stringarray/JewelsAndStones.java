package algorithm.inflearn.a.stringarray;

import java.util.HashSet;
import java.util.Set;

public class JewelsAndStones {
    public static void main(String[] args) {
        String J = "aA";
        String S = "aAAbbbb";

        solve(J, S);
    }

    private static void solve(String j, String s) {
        Set<Character> set = new HashSet<>();
        for(char jewel : j.toCharArray()) {
            set.add(jewel);
        }
        int count = 0;
        for (char stone : s.toCharArray()) {
            if (set.contains(stone)) {
                count++;
            }
        }
        System.out.println(count);
    }
}
