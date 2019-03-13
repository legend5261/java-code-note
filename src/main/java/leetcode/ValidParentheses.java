package leetcode;

import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 *  1.Open brackets must be closed by the same type of brackets.
 *  2.Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 *
 * https://leetcode.com/problems/valid-parentheses/
 */
public class ValidParentheses {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty())
                    return false;
                char pre = stack.pop();
                if ((c == ')' && pre != '(') || (c == ']' && pre != '[') || (c == '}' && pre != '{'))
                    return false;

            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new ValidParentheses().isValid("("));
    }
}
