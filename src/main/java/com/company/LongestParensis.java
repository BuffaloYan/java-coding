package com.company;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class LongestParensis {
    public int longestValidParentheses(String s) {
        int open = 0;
        int count = 0;
        int maxCount = 0;
        for (char ch: s.toCharArray()) {
            if (ch == '('){
                open++;
                count++;
            } else {
                open--;
                if (open < 0) {
                    open = 0;
                    count = 0;
                } else if (open == 0){
                    count++;
                    maxCount = Math.max(maxCount, count);
                } else {
                    count++;
                }
            }
        }


        if (open > 0) {
            int i = s.length()-1;
            int newCount = 0;
            while (open > 0) {
                if (s.charAt(i) == ')') {
                    open++;
                } else {
                    open--;
                }
                newCount++;
                count--;
                i--;
            }
            newCount--;

            maxCount = Math.max(maxCount, newCount);
        }

        maxCount = Math.max(maxCount, count);

        return maxCount;

    }

    @Test
    public void testRun() {
        String s = ")(((((()())()()))()(()))(";
        LongestParensis parensis = new LongestParensis();
        int ans = parensis.longestValidParentheses(s);

        assertEquals(22, ans);
    }
}
