package com.company;

import org.junit.Test;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class Combination {
    public static void combinate(char[] chars, short subsetSize) {
        combinate(chars, subsetSize, (short)0, new StringBuilder());
    }

    public static void combinateDuplicate(char[] chars, short subsetSize) {
        // char count map
        Map<Character, Integer> map = new HashMap<>();
        for(char c: chars) {
            if (map.get(c)==null) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c)+1);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(char c: map.keySet()) {
            sb.append(c);
        }

        // get unique char array
        chars = sb.toString().toCharArray();

        combinateDuplicate(chars, subsetSize, (short)0, map, new StringBuilder());
    }

    private static void combinate(char[] chars, short subsetSize, short startIndex, StringBuilder sb) {
        if (sb.length() == subsetSize) {
            System.out.println(sb.toString());
            return;
        }

        for (short i=startIndex; i<chars.length; i++) {
            sb.append(chars[i]);
            combinate(chars, subsetSize, (short)(i+1), sb);

            sb.setLength(sb.length()-1);
        }
    }

    private static void combinate2(char[] chars, short subsetSize, short startIndex, String sb) {
        if (sb.length() == subsetSize) {
            System.out.println(sb.toString());
            return;
        }

        for (short i=startIndex; i<chars.length; i++) {
            combinate2(chars, subsetSize, (short)(i+1), sb + chars[i]);
        }
    }

    private static void combinateDuplicate(char[] chars, short subsetSize, short startIndex, Map<Character, Integer> map, StringBuilder sb) {
        if (sb.length() == subsetSize) {
            System.out.println(sb.toString());
            return;
        }

        for (short i=startIndex; i<chars.length; i++) {
            sb.append(chars[i]);
            map.put(chars[i], map.get(chars[i])-1);
            if (map.get(chars[i]) > 0) {
                combinateDuplicate(chars, subsetSize, (short) (i), map, sb);
            } else {
                combinateDuplicate(chars, subsetSize, (short) (i+1), map, sb);
            }
            map.put(chars[i], map.get(chars[i])+1);

            sb.setLength(sb.length()-1);
        }
    }

    @Test
    public void testRun() {
        combinate("12345".toCharArray(), (short)3);
    }

    @Test
    public void testCombnate2() {
        combinate("1234".toCharArray(), (short)3);
    }

    @Test
    public void testCombinationWithDuplicate() {
        combinateDuplicate("122345".toCharArray(), (short)3);
    }
}
