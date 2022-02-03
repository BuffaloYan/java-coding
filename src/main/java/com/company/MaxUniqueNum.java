package com.company;

import java.util.*;
public class MaxUniqueNum {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Deque<Integer> deque = new ArrayDeque<>();
        int n = in.nextInt();
        int m = in.nextInt();

        Map<Integer, Integer> map = new HashMap<>();
        int num;
        for (int i = 0; i < m; i++) {
            num = in.nextInt();
            addNum(map, num);
            deque.addLast(num);
        }

        int maxSize = map.size();
        for (int i = 0; i < n-m; i++) {
            num = in.nextInt();
            addNum(map, num);
            deque.addLast(num);

            num = deque.removeFirst();
            removeNum(map, num);

            if (map.size() > maxSize) {
                maxSize = map.size();

                if (maxSize > m) {
                    // maximum possible reached, no need to continue
                    break;
                }
            }

        }

        System.out.println(maxSize);

    }

    static void addNum(Map<Integer, Integer> map, int num) {
        Integer count = map.get(num);
        if( count == null ) {
            count = 1;
        } else {
            count++;
        }

        map.put(num, count);
    }

    static void removeNum(Map<Integer, Integer> map, int num) {
        Integer count = map.get(num);
        if( count != null ) {
            count--;

            if (count == 0) {
                map.remove(num);
            } else {
                map.put(num, count);
            }
        }

    }
}



