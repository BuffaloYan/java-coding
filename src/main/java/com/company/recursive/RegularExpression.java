package com.company.recursive;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class RegularExpression {
    public boolean isMatch(String s, String p) {
        if (p.length() == 0) {
            return (s.length() == 0);
        }

        if (p.length() == 1) {
            if (p.equals(".")) {
                return s.length() == 1;
            }
            return s.equals(p);
        }

        // matching right side non *
        if (p.charAt(p.length()-1) != '*' && s.length() > 0) {
            if (p.charAt(p.length()-1) != '.') {
                boolean check = s.charAt(s.length()-1) == p.charAt(p.length()-1);
                if (!check) return false;
            }

            return isMatch(s.substring(0, s.length()-1), p.substring(0, p.length()-1));
        }

        if (p.charAt(1) != '*') {
            // ab*
            // advance 1 char
            return  (p.charAt(0) == '.' || s.length() > 0 && p.charAt(0) == s.charAt(0) )
                    && isMatch(s.length()>0?s.substring(1):s, p.substring(1));
        } else {
            if (p.charAt(0) == '.') {
                // .* will match whole s
                return isMatch("", p.substring(2));
            } else {
                // a*b*.
                if (s.length() > 0 && s.charAt(0) == p.charAt(0)) {
                    int index = 0;
                    while (index < s.length() && s.charAt(index) == p.charAt(0)) {
                        index++;
                    }

                    if (index >= s.length()) {
                        s = "";
                    } else {
                        s = s.substring(index);
                    }
                }

                return isMatch(s, p.substring(2));
            }
        }


    }

    @Test
    public void testRun() {
        /**
         * "bbbba"
         * ".*a*a"
         */
        String s = "bbbba";
        String p = ".*a*a";

        RegularExpression regularExpression = new RegularExpression();
        boolean result = regularExpression.isMatch(s, p);
        assertTrue(result);
    }

    @Test
    public void testRun1() {
        /**
         "a"
         ".*..a*"
         */
        String s = "a";
        String p = ".*..a*";

        RegularExpression regularExpression = new RegularExpression();
        boolean result = regularExpression.isMatch(s, p);
        assertFalse(result);
    }

    @Test
    public void testRun2() {
        /**
         "ab"
         ".*..c*"
         */
        String s = "ab";
        String p = ".*..c*";

        RegularExpression regularExpression = new RegularExpression();
        boolean result = regularExpression.isMatch(s, p);
        assertTrue(result);
    }


}
