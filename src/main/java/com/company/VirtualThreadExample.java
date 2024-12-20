package com.company;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VirtualThreadExample {
    // Define a ScopedValue for passing context across threads
    private static final ScopedValue<String> REQUEST_ID = ScopedValue.newInstance();

    public static void main(String[] args) {
        // Contextual data to propagate using ScopedValue
        String requestId = "REQ-" + System.currentTimeMillis();

        ScopedValue.where(REQUEST_ID, requestId).run(() -> {
            try {
                processRequest();
            } catch (Exception e) {
                System.err.println("Error processing request: " + e.getMessage());
            }
        });
    }

    private static void processRequest() throws InterruptedException, ExecutionException {
        // Use structured concurrency for task coordination
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {

            long startTime = System.currentTimeMillis();
            System.out.println("Start Time: " + startTime);
            // Spawn virtual threads
            var task1 = scope.fork(() -> fetchData("Service A"));
            var task2 = scope.fork(() -> fetchData("Service B"));
            var task3 = scope.fork(() -> fetchData("Service C"));

            // Await all tasks and collect results
            scope.join(); // Wait for all tasks to complete
            scope.throwIfFailed(); // Propagate any exceptions

            // Combine results
            String result = task1.get() + ", " + task2.get() + ", " + task3.get();
            System.out.println("All data fetched: " + result);
            long endTime = System.currentTimeMillis();
            System.out.println("Total Duration: " + (endTime - startTime));
        }
    }

    private static String fetchData(String serviceName) throws InterruptedException {
        // Access the contextual data from ScopedValue
        String requestId = REQUEST_ID.get();
        // random delay from 1 - 3 seconds
        int delay = (int) (Math.random() * 3) + 1;
        String msg = STR."""
            Fetching data from \{serviceName} with Request ID: \{requestId}, delay: \{delay} seconds
            """;
        System.out.println(msg);

        // Simulate fetching data with a delay
        //Thread.sleep(1000); // 1-second delay
        TimeUnit.SECONDS.sleep(delay);
        // LockSupport.parkNanos(1_000_000_000L); // 1-second delay
        return serviceName + " data";
    }

    static String processInputNew(String input) {
        String output = null;
        switch(input) {
            case null -> output = "Oops, null";
            case String s when s.equalsIgnoreCase("yes") -> output = "It's Yes";
            case "No" -> output = "It's No";
            default -> output = "Try Again";
        }
        return output;
    }

    @Test
    public void testProcessInputNew() {
        assertEquals("Oops, null", processInputNew(null));
        assertEquals("It's Yes", processInputNew("Yes"));
        assertEquals("It's No", processInputNew("No"));
        assertEquals("It's Yes", processInputNew("YES"));
        assertEquals("Try Again", processInputNew("Maybe"));

        Map.of("a", 1, "b", 2).forEach((k, v) -> System.out.println(STR."\{k} -> \{v}"));
    }
}
