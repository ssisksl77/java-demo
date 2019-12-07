package algorithm.inflearn.a.stringarray;

import java.util.*;

public class MeetingRoom2 {
    public static void main(String[] args) {
        int[][] input1 = {{0,30},{5,10},{15,20}};
//        solve(input1);
        // output : 2
        int[][] input2 = {{7,10},{2,4}};
//        solve(input2);
        // output : 1

        int[][] input3 = {{1,5}, {5,6}, {5,8}};
        solve(input3);
    }

    private static void solve(int[][] input) {
        Interval[] schedules = new Interval[input.length];
        for (int i = 0; i < input.length; i++) {
            schedules[i] = new Interval(input[i][0], input[i][1]);
        }
        Arrays.sort(schedules);

        List<Interval> mergedSchedules = new ArrayList<>();
        Queue<Interval> heap = new PriorityQueue<>(schedules.length, (o1, o2) -> o1.b - o2.b);

        heap.offer(schedules[0]);
        for (int i = 1; i < schedules.length; i++) {
            Interval interval = heap.poll();
            System.out.println(interval);

            // start가 end보다 같거나 뒤에 있으면 합친다. ( 그 사이 시간은 아무도 못들어온다. 왜냐하면 start로 정렬된 포문으로 돌고 있기 때문)
            if (schedules[i].a >= interval.b) {
                interval.b = schedules[i].b;
            } else {
                heap.offer(schedules[i]);
            }

//            if(schedules[i].a < interval.b) {
//                heap.offer(schedules[i]);
//            }
            heap.offer(interval);
        }
        System.out.println(heap.size());

    }

    private static class Interval implements Comparable<Interval>{
        int a;
        int b;

        public Interval(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Interval o) {
            return a- o.a;
        }

        @Override
        public String toString() {
            return "Interval{" +
                    "a=" + a +
                    ", b=" + b +
                    '}';
        }
    }
}
