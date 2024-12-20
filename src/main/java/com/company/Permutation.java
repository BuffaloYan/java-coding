package com.company;

import org.junit.jupiter.api.Test;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class Permutation {
    public static void permuate(char[] chars, int subsetSize) {
        BitSet mask = new BitSet(chars.length);
        permuate(chars, subsetSize, mask, new StringBuilder());
    }

    public static void permuateDuplicate(char[] chars, int subsetSize) {
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

        permuateDuplicate(chars, subsetSize, map, new StringBuilder());
    }

    private static void permuate(char[] chars, int subsetSize, BitSet mask, StringBuilder sb) {
        if (sb.length() == subsetSize) {
            System.out.println(sb.toString());
            return;
        }

        for (int i=0; i<chars.length; i++) {
            if (!mask.get(i)) {
                mask.set(i);
                sb.append(chars[i]);

                permuate(chars, subsetSize, mask, sb);
                mask.clear(i);
                sb.setLength(sb.length()-1);
            }
        }
    }

    private static void permuate2(char[] chars, int subsetSize, BitSet mask, String sb) {
        if (sb.length() == subsetSize) {
            System.out.println(sb.toString());
            return;
        }

        for (int i=0; i<chars.length; i++) {
            if (!mask.get(i)) {
                mask.set(i);

                permuate2(chars, subsetSize, mask, sb + chars[i]);
                mask.clear(i);

            }
        }
    }

    private static void permuateDuplicate(char[] chars, int subsetSize, Map<Character, Integer> map, StringBuilder sb) {
        if (sb.length() == subsetSize) {
            System.out.println(sb.toString());
            return;
        }

        for (int i=0; i<chars.length; i++) {
            int count = map.get(chars[i]);
            if (count>0) {
                map.put(chars[i], count-1);
                sb.append(chars[i]);

                permuateDuplicate(chars, subsetSize, map, sb);
                map.put(chars[i], count);
                sb.setLength(sb.length()-1);
            }
        }
    }

    @Test
    public void testRun() {
        permuate("12345".toCharArray(), 3);
    }

    @Test
    public void testPermutate2() {
        permuate("12345".toCharArray(), 3);
    }

    @Test
    public void testPermutateDuplicate() {
        permuateDuplicate("12245".toCharArray(), 3);
    }
}
