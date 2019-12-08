package algorithm.inflearn.a.stack_queue;

import java.util.Stack;

/**
 * ()
 */
public class ValidParenthesis {
    public static void main(String[] args) {
        String exp="{()}";
        solve(exp);
    }

    private static void solve(String exp) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            switch(exp.charAt(i)) {
                case ')':
                    if(!stack.empty()&&stack.peek()=='(') stack.pop();
                    break;
                case ']':
                    if(!stack.empty()&&stack.peek()=='[') stack.pop();
                    break;
                case '}':
                    if(!stack.empty()&&stack.peek()=='{') stack.pop();
                    break;
                default:
                    stack.push(exp.charAt(i));
                    break;
            }
        }

        System.out.println(stack.isEmpty());
    }
}
