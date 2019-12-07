package algorithm.inflearn.a.stringarray;

import java.util.HashSet;
import java.util.Set;

public class UniqueEmailAddress {
    public static void main(String[] args) {
        // @앞의 .은 의미 없으며, +뒤에 단어도 의미 없음.
        String[] arr = {"test.email+james@coding.com","test.e.mail+toto@coding.com", "testemail@cod.ing.com"};

        solve(arr);

    }

    private static void solve(String[] arr) {
        Set<String> set = new HashSet<>();

        for (String str : arr) {
            StringBuilder sb = new StringBuilder();
            char[] chars = str.toCharArray();
            boolean isDomainPart = false;
            boolean isPlus = false;
            for (int i = 0; i < chars.length; i++) {
                if (!isDomainPart) {
                    switch(chars[i]) {
                        case '.':
                            break;
                        case '@':
                            isDomainPart = true;
                            break;
                        case '+':
                            isPlus = true;
                            break;
                        default:
                            if(!isPlus) {
                                sb.append(chars[i]);
                            }
                            break;
                    }
                } else {
                    sb.append(chars[i]);
                }
            }
            set.add(sb.toString());
        }

        for(String str : set) {
            System.out.println(str);
        }

        System.out.println(set.size());
    }
}
