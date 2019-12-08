package algorithm.inflearn.a.stack_queue;

import java.util.Stack;

public class BaseballGame {
    public static void main(String[] args) {
        String[] strs = {"5","-2","4","C","D","9","+","+"};
        solve(strs);
    }

    private static void solve(String[] strArr) {
        Stack<Integer> stack = new Stack<>();
        for (String str:
             strArr) {
            switch (str) {
                case "+":
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(b);
                    stack.push(a);
                    stack.push(a + b);
                    break;
                case "D":
                    stack.push(stack.peek()*2);
                    break;
                case "C":
                    stack.pop();
                    break;
                default:
                    stack.push(Integer.valueOf(str));
            }
        }
        int sum = 0;
        while(!stack.isEmpty())
            sum += stack.pop();
        System.out.println(sum);
    }
}
