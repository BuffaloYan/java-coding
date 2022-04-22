package com.company.multithreading;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class AddingNumbers {
    public static void main( String args[] ) throws InterruptedException {
        SumUpExample.runTest();
    }
}
class CustomRecursiveTask extends RecursiveTask<Long> {
    private long[] arr;

    private static final int THRESHOLD = 100_000_000;

    public CustomRecursiveTask(long[] arr) {
        this.arr = arr;
    }

    @Override
    protected Long compute() {
        if (arr[1]-arr[0] > THRESHOLD) {
            return ForkJoinTask.invokeAll(createSubtasks())
                    .stream()
                    .mapToLong(ForkJoinTask::join)
                    .sum();
        } else {
            return processing(arr);
        }
    }

    private Collection<CustomRecursiveTask> createSubtasks() {
        List<CustomRecursiveTask> dividedTasks = new ArrayList<>();
        long mid = (arr[0]+arr[1])/2;
        dividedTasks.add(new CustomRecursiveTask(new long[]{arr[0], mid}));
        dividedTasks.add(new CustomRecursiveTask(new long[]{mid+1, arr[1]}));
        return dividedTasks;
    }

    private Long processing(long[] arr) {
        long sum = 0;
        for (long i=arr[0]; i<=arr[1]; i++) {
            sum += i;
        }

        return sum;
    }
}
class SumUpExample {

    long startRange;
    long endRange;
    long counter = 0;
    static long MAX_NUM = Integer.MAX_VALUE;

    public SumUpExample(long startRange, long endRange) {
        this.startRange = startRange;
        this.endRange = endRange;
    }

    public void add() {

        for (long i = startRange; i <= endRange; i++) {
            counter += i;
        }
    }

    public void addParallel() {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        CustomRecursiveTask task = new CustomRecursiveTask(new long[]{startRange, endRange});
        counter = pool.invoke(task);
    }

    static public void twoThreads() throws InterruptedException {

        long start = System.currentTimeMillis();
        SumUpExample s1 = new SumUpExample(1, MAX_NUM / 2);
        SumUpExample s2 = new SumUpExample(1 + (MAX_NUM / 2), MAX_NUM);

        Thread t1 = new Thread(() -> {
            s1.add();
        });

        Thread t2 = new Thread(() -> {
            s2.add();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        long finalCount = s1.counter + s2.counter;
        long end = System.currentTimeMillis();
        System.out.println("Two threads final count = " + finalCount + " took " + (end - start));
    }

    static public void oneThread() {

        long start = System.currentTimeMillis();
        SumUpExample s = new SumUpExample(1, MAX_NUM );
        s.add();
        long end = System.currentTimeMillis();
        System.out.println("Single thread final count = " + s.counter + " took " + (end - start));
    }

    static public void parallel() {

        long start = System.currentTimeMillis();
        SumUpExample s = new SumUpExample(1, MAX_NUM );
        s.addParallel();
        long end = System.currentTimeMillis();
        System.out.println("Parallel forkJoinPool final count = " + s.counter + " took " + (end - start));
    }


    public static void runTest() throws InterruptedException {

        oneThread();
        twoThreads();
        parallel();
    }
}
