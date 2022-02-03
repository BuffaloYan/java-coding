package com.company.recursive;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class StackOfBoxes {
    public static class Box {
        int w,h,d;
        public Box(int w, int h, int d) {
            this.w = w;
            this.h = h;
            this.d = d;
        }

        public Integer edgeSum() {
            return w + h + d;
        }

        public boolean isStrictSmallerThan(Box target) {
            return w < target.w && h < target.h && d < target.d;
        }

        public String toString() {
            return "(" + w + "," + h + "," + d + ")";
        }
    }

    public static class BoxTracker {
        LinkedList<Box> stack;
        LinkedList<Box> maxStack;

        public BoxTracker() {
            stack = new LinkedList<>();
            maxStack = new LinkedList<>();
        }
    }

    public static List<Box> arrangeBoxes(List<Box> list) {
        // sort by desending order of edgeSum
        list.sort(new Comparator<Box>() {
            @Override
            public int compare(Box o1, Box o2) {
                return o2.edgeSum().compareTo(o1.edgeSum());
            }
        });

        BoxTracker tracker = new BoxTracker();
        arrangeBoxes(list, 0, tracker);

        return tracker.maxStack;
    }

    private static void arrangeBoxes(List<Box> list, int i, BoxTracker boxTracker) {
        if (i > list.size()-1) {
            // scaned to the end, check max
            if (boxTracker.stack.size() > boxTracker.maxStack.size()) {
                boxTracker.maxStack.clear();
                boxTracker.maxStack.addAll(boxTracker.stack);
            }
            return;
        }

        for (int j=i; j<list.size(); j++) {
            if (boxTracker.stack.isEmpty() || list.get(j).isStrictSmallerThan( boxTracker.stack.peek() ) ) {
                boxTracker.stack.add(list.get(j));
                arrangeBoxes(list, j+1, boxTracker);

                boxTracker.stack.removeLast();
            }
        }
    }

    @Test
    public void testRun() {
        List<Box> boxes = new ArrayList<>();
        boxes.add( new Box(10,3,3));
        boxes.add( new Box(8,2,2));
        boxes.add( new Box(5,11,5));
        boxes.add( new Box(4,10,4));
        boxes.add( new Box(7,7,10));
        boxes.add( new Box(5,5,9));
        boxes.add( new Box(3,8,2));
        boxes.add( new Box(2,2,2));

        List<Box> maxStack = arrangeBoxes(boxes);

        System.out.println("max stack size = " + maxStack.size());
        System.out.println( maxStack.stream()
                .map( box -> box.toString() )
                .reduce("", (s1, s2) -> s1.length()==0? s2 : s1 + " -> " + s2)
        );

    }
}
