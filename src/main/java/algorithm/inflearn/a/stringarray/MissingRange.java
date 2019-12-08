package algorithm.inflearn.a.stringarray;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 Given a sorted array nums, where the range of elements are in the inclusive range
 [lower, upper], return its missing ranges.
 */
public class MissingRange {
    public static void main(String[] args) {
        int[] nums = {0, 1, 3, 50, 75};
        solve(nums);
    }

    private static void solve(int[] nums) {
        boolean[] range = new boolean[100];
        range[0] = true;
        range[99] = true;
        for(int i = 0; i < nums.length; i++) {
            range[nums[i]] = true;
        }

        List<String> list = new ArrayList<>();

        // 0 0 이면 첫번째 값만 가지고 있는다. (4번째에서 할 거니 이것도 그냥 넘어간다.)
        // 0 1 이면 0 인 범위를 저장한다.
        // 1 1 이면 계속 넘어간다.
        // 1 0 이면 0의 위치를 저장한다.
        int prevIdx = 0;
        for (int i = 1; i < range.length; i++) {
            if(range[i] == range[i-1]) {
                continue;
            } else {
                if (range[i-1]) {
                    prevIdx = i;
                } else {
                    if (i - prevIdx == 1) {
                        list.add(String.valueOf(i));
                    } else {
                        list.add(String.valueOf(prevIdx + "->" + (i-1)));
                    }
                }
            }
        }
        System.out.println(list);
    }
}
