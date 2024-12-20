package com.company.array;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.*;

public class TaskCoolDown {
    Map<Character, Integer> charMap = new TreeMap<>();
    List<CharCount> countList = new ArrayList<>();
    int length, cooldown;

    public static class CharCount {
        Character ch;
        Integer count;
        public CharCount(Character ch, Integer count) {
            this.ch = ch;
            this.count = count;
        }
    }

    public int leastInterval(char[] tasks, int n) {
        length = tasks.length;
        cooldown = n;

        // count all characters
        for (char ch: tasks) {
            Integer count = charMap.getOrDefault(ch, 0);
            charMap.put(ch, count+1);
        }


        for (char ch: charMap.keySet()) {
            countList.add(new CharCount(ch, charMap.get(ch)));
        }
        Collections.sort(countList, (a, b) -> b.count-a.count);

        // greedy
        // assign first group of task
        List<List<Character>> list = new ArrayList<>();
        for (int i=0; i<countList.get(0).count; i++) {
            list.add(new ArrayList<>());
            list.get(i).add(countList.get(0).ch);
        }

        // task left to be assigned
        int taskCount = tasks.length - countList.get(0).count;
        int charIndex = 1;
        int listIndex = 0;
        while (taskCount-- > 0) {
            if (countList.get(charIndex).count == 0) {
                charIndex++;
            }

            list.get(listIndex).add(countList.get(charIndex).ch);
            countList.get(charIndex).count--;
            // rotating listInex
            listIndex = (listIndex+1) % list.size();
        }

        // consolidate
        int totalCount = 0;
        for (int i=0; i<list.size(); i++) {
            while (i<(list.size()-1) && (list.get(i).size()-1) < cooldown) list.get(i).add(' ');
            totalCount += list.get(i).size();
        }

        return totalCount;
    }

    @Test
    public void testRun() {
        TaskCoolDown coolDown = new TaskCoolDown();
        int result = coolDown.leastInterval("AAABBB".toCharArray(), 2);

        assertEquals(8, result);
    }
}
