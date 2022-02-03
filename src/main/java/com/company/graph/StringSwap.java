package com.company.graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class StringSwap {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int[] disjointSet = new int[s.length()];
        for(int i=0; i<s.length(); i++) {
            disjointSet[i] = i;
        }

        for(List<Integer> pair: pairs) {
            union(disjointSet, pair.get(0), pair.get(1));
        }

        Map<Integer, String> groupChars = new HashMap<>();
        for(int i=0; i<s.length(); i++) {
            String chars = groupChars.get(find(disjointSet,i));
            if (chars == null) {
                chars = "";
            }

            chars += s.charAt(i);
            groupChars.put(disjointSet[i], chars);
        }

        Map<Integer, Integer> groupIndex = new HashMap<Integer, Integer>();
        for(Integer i: groupChars.keySet()) {
            char[] chars = groupChars.get(i).toCharArray();
            Arrays.sort(chars);
            groupChars.put(i, new String(chars));
            groupIndex.put(i, 0);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<s.length(); i++) {
            int group = disjointSet[i];
            int index = groupIndex.get(group);
            sb.append(groupChars.get(group).charAt(index));
            index++;
            groupIndex.put(group, index);
        }

        return sb.toString();
    }

    public int find(int[] disjointSet, int x) {
        if (x == disjointSet[x]) {
            return x;
        }
        return disjointSet[x] = find(disjointSet, disjointSet[x]);
    }

    // connect i and j along with all their neighbors
    public void union(int[] disjointSet, int i, int j) {
        int rootI = find(disjointSet, i);
        int rootJ = find(disjointSet, j);
        if (rootI != rootJ) {
            disjointSet[rootI] = rootJ;
        }
    }

    @Test
    public void testRun1() {
        String s = "dcab";
        List<List<Integer>> pairs = new ArrayList<>();
        List<Integer> pair = new ArrayList<>();
        pair.add(0);
        pair.add(3);
        pairs.add(pair);

        pair = new ArrayList<>();
        pair.add(1);
        pair.add(2);
        pairs.add(pair);

        pair = new ArrayList<>();
        pair.add(0);
        pair.add(2);
        pairs.add(pair);

        StringSwap swap = new StringSwap();
        String result = swap.smallestStringWithSwaps(s, pairs);

        Assert.assertEquals("abcd", result);
    }
}
