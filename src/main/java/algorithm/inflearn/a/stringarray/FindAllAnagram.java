package algorithm.inflearn.a.stringarray;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s and non-empty string p, find all start indices of p's anagrams in s.
 *
 * Strings consist of lowercase English letters only and the length of both s and p
 * will not be larger than 20,100.
 *
 * The order of output does not matter.
 *
 * ex1)
 * s: "cbaebabacd" p: "abc"
 * output [0,6]
 */
public class FindAllAnagram {
    public static void main(String[] args) {
        String txt = "cbaebabacd";
        String p = "abc";
        List<Integer> res = solve(txt, p);
        System.out.println(res);
    }

    private static List<Integer> solve(String txt, String pat) {
        List<Integer> result = new ArrayList<>();
        if(txt==null||txt.length()==0||pat==null||pat.length()==0||txt.length()<pat.length())
            return result;

        int[] patArr = new int[256];
        for (int i = 0; i < pat.length(); i++) {
            patArr[pat.charAt(i)]++;  // 여러개 일 수 있으니까.
        }

        for (int i = 0; i < txt.length() - pat.length()+1; i++) {
            int[] txtArr = new int[256];
            for (int j = 0; j < pat.length(); j++) {
                txtArr[txt.charAt(i+j)]++;
            }
            if (isAnagram(patArr, txtArr)) {
                result.add(i);
            }
        }
        return result;
    }

    private static boolean isAnagram(int[] patArr, int[] txtArr) {
        for (int i = 0; i < patArr.length; i++) {
            if(patArr[i] != txtArr[i]) return false;
        }
        return true;
    }


}
