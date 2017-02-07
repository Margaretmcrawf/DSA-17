package your_code;

import ADTs.StackADT;

import java.util.Stack;

public class PsetProblems {

    public static int longestValidSubstring(String s) {
        char[] charArray = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        int longest = 0;
        int current = 0;
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == ')') {
                if (stack.isEmpty()) {
                    if (current > longest) {
                        longest = current;
                    }
                    current = 0;
                } else  {
                    current+=2;
                }
            } else {
                stack.push(charArray[i]);
            }
        }
        if (current > longest) {
            longest = current;
        }
        return longest;
    }

    public static StackADT<Integer> sortStackLimitedMemory(StackADT<Integer> s) {
        MyStack t = new MyStack();
        int u;
        t.push(s.pop());
        while (!s.isEmpty()) {
            if (s.peek() < t.peek()) {
                t.push(s.pop());
            } else {
                u = s.pop();
                while (!t.isEmpty() && u > t.peek()) {
                    s.push(t.pop());
                }
                t.push(u);
            }
        }
        return t;
    }


}
