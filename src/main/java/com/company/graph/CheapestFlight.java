package com.company.graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class CheapestFlight {
    public static class Flight {
        int from, to;
        int price;
        public Flight(int from, int to, int price) {
            this.from = from;
            this.to = to;
            this.price = price;
        }
    }
    public static class City {
        int index;
        int dstPrice = Integer.MAX_VALUE; // (lowest) destination price from source, starts with MAX
        int dstLegs; // how many flights get to this city
        int from;
        List<Flight> flights;
        boolean visited;
        public City(int i) {
            index = i;
            flights = new ArrayList<>();
        }

        public String toString() {
            return ""+index+":"+visited;
        }
    }

    City[] cities;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        cities = new City[n];
        for (int i=0; i<n; i++) {
            cities[i] = new City(i);
        }

        // build graph
        for (int i=0; i<flights.length; i++) {
            int[] f = flights[i];
            Flight flight = new Flight(f[0], f[1], f[2]);

            // from city
            City city = cities[f[0]];
            city.flights.add(flight);
        }

        // searching from src to dst using dijkstra algorithm
        City source = cities[src];
        source.dstPrice = 0;

        City destination = cities[dst];
        Map<String, Integer> df = new HashMap<>();

        int result = dfs(source, destination, k, df);


        return result == Integer.MAX_VALUE? -1 : result;
    }

    int count = 0;
    int dfs(City s, City d, int legs, Map<String, Integer> df) {
        if (s == d) return 0;

        if (legs < 0) return Integer.MAX_VALUE;

        String dfKey = s.index+","+legs;
        if (df.containsKey(dfKey)) return df.get(dfKey);

        count++;
        int min = Integer.MAX_VALUE;
        s.visited = true;
        for (Flight f: s.flights) {
            //if (!cities[f.to].visited) {
                cities[f.to].from = f.from;
                int price = dfs(cities[f.to], d, legs-1, df);
                min = Math.min(min, price == Integer.MAX_VALUE? price : price + f.price);
            //}
        }

        s.visited = false;
        df.put(dfKey, min);

        return min;
    }


    @Test
    public void testRun() {
        int n = 100;
        int source = 1;
        int destination = 99;
        int stops = 99;

        int[][] data = new int[99*98/2][];
        int index = 0;
        for (int i=0; i<98; i++) {
            for (int j=i+1; j<99; j++) {
                //if (i==1 && j==99) continue;
                data[index++] = new int[] {i, j, 1};
            }
        }
        CheapestFlight flight = new CheapestFlight();
        System.out.println(System.currentTimeMillis());
        int result = flight.findCheapestPrice(n, data, source, destination, stops);
        System.out.println(System.currentTimeMillis());
        System.out.println(flight.count);
        Assert.assertEquals(-1, result);
        List<long[]> list = new ArrayList<>();
        LinkedHashSet<Integer> set = new LinkedHashSet<>();

    }
}
