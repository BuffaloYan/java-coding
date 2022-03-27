package com.company.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MinWindowSubString {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";

        if (s.indexOf(t) >= 0) return t;

        Map<Character, Integer> targetMap = new HashMap<>();

        for (char ch: t.toCharArray()) {
            Integer count = targetMap.get(ch);
            if (count == null) {
                targetMap.put(ch, 1);
            } else {
                targetMap.put(ch, count+1);
            }
        }

        Map<Character, Integer> trackMap = new HashMap<>();
        int start = 0;
        int end = -1;
        int minSize = Integer.MAX_VALUE;
        int minStart = -1;
        int minEnd = 0;
        for (int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if (targetMap.containsKey(ch)) {
                Integer count = trackMap.getOrDefault(ch, 0);
                trackMap.put(ch, count+1);

                end = i;

                // found a match
                while (true) {
                    // make sure start align at maching char
                    while (!targetMap.containsKey(s.charAt(start))){
                        start++;
                    }

                    if (!isMatch(targetMap, trackMap)) {
                        break;
                    }

                    if (minSize > end-start+1) {
                        minSize = end-start+1;
                        minEnd = end;
                        minStart = start;
                    }

                    ch = s.charAt(start++);
                    count = trackMap.get(ch);
                    count--;
                    if (count == 0) {
                        // that's far enough
                        trackMap.remove(ch);
                        break;
                    } else {
                        trackMap.put(ch, count);
                    }

                }

            }

        }

        return minStart >= 0? s.substring(minStart, minEnd+1) : "";
    }

    private boolean isMatch(Map<Character, Integer> targetMap, Map<Character, Integer> trackMap) {
        if (targetMap.size() == trackMap.size()) {
            for (Character ch: trackMap.keySet()) {
                if (trackMap.get(ch).intValue() < targetMap.get(ch).intValue()) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    @Test
    public void testRun() {
        MinWindowSubString minWindowSubString = new MinWindowSubString();
        String ans = minWindowSubString.minWindow(
                "ADOBECODEBANC",
                "ABC");

        Assert.assertEquals("BANC", ans);
    }
}
