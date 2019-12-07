package algorithm.inflearn.a.stringarray;

import java.util.*;

/**
 * Givan an arrya
 */
public class GroupAnagram {
    public static void main(String[] args) {
        String[] arr = {"eat", "tea", "tan", "ate", "nat", "bat"};
        // output [["ate", "eat", "tea"]
        //         ["nat", "tan"]
        //         ["bat"]]
        System.out.println(solve(arr));
    }

    private static List<List<String>> solve(String[] arr) {
        if (arr == null || arr.length == 0) return new ArrayList<>();

        Map<String, List<String>> map = new HashMap<>();

        for (String str : arr) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            List<String> strList = map.getOrDefault(String.valueOf(chars), new ArrayList<>());
            strList.add(str);
            map.put(String.valueOf(chars), strList);
        }

        List<List<String>> res = new ArrayList<>();
        res.addAll(map.values());
        return res;
    }
}
