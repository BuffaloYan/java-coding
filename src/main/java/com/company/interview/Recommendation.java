package com.company.interview;

/*

You are analyzing data for Aquaintly, a hot new social network.

One of the fun features of Aquaintly is that users can rate movies they have seen from 1 to 5. We want to use these ratings to make movie recommendations.

Ratings will be provided in the following format:
  [Member Name, Movie Name, Rating]

We consider two users to have similar taste in movies if they have both rated the same movie as 4 or 5.

A movie should be recommended to a user if:
- They haven't rated the movie
- A user with similar taste has rated the movie as 4 or 5

Example:
ratings = [
    ["Alice", "Frozen", "5"],
    ["Bob", "Mad Max", "5"],
    ["Charlie", "Lost In Translation", "4"],
    ["Charlie", "Inception", "4"],
    ["Bob", "All About Eve", "3"],
    ["Bob", "Lost In Translation", "5"],
    ["Dennis", "All About Eve", "5"],
    ["Dennis", "Mad Max", "4"],
    ["Charlie", "Topsy-Turvy", "2"],
    ["Dennis", "Topsy-Turvy", "4"],
    ["Alice", "Lost In Translation", "1"]
]
If we want to recommend a movie to Charlie, we would recommend "Mad Max" because:
- Charlie has not rated "Mad Max"
- Charlie and Bob have similar taste as they both rated "Lost in Translation" 4 or 5
- Bob rated "Mad Max" a 5

Write a function that takes the name of a user and a collection of ratings, and returns a collection of all movie recommendations that can be made for the given user.

All test cases:
recommendations("Charlie", ratings) => ["Mad Max"]
recommendations("Bob", ratings) => ["Inception", "Topsy-Turvy"]
recommendations("Dennis", ratings) => ["Lost In Translation"]
recommendations("Alice", ratings) => []

Complexity Variable:
R = number of ratings
M = number of movies
U = number of users

*/

import java.io.*;
import java.util.*;

public class Recommendation {
    public static void main(String[] argv) {
        String[][] ratings = {
                {"Alice", "Frozen", "5"},
                {"Bob", "Mad Max", "5"},
                {"Charlie", "Lost In Translation", "4"},
                {"Charlie", "Inception", "4"},
                {"Bob", "All About Eve", "3"},
                {"Bob", "Lost In Translation", "5"},
                {"Dennis", "All About Eve", "5"},
                {"Dennis", "Mad Max", "4"},
                {"Charlie", "Topsy-Turvy", "2"},
                {"Dennis", "Topsy-Turvy", "4"},
                {"Alice", "Lost In Translation", "1"}
        };
        System.out.println(recommendations("Charlie", ratings));
        // => ["Mad Max"]
        System.out.println(recommendations("Bob", ratings));
        // => ["Inception", "Topsy-Turvy"]
        System.out.println(recommendations("Dennis", ratings));
        // => ["Lost In Translation"]
        System.out.println(recommendations("Alice", ratings));
        // => []

    }

    public static List<String> recommendations(String user, String[][] ratings) {

        List<String> userRatedMovies = new ArrayList<>();
        Map<String, Set<String>> userToMovieMap = new HashMap<>();
        Map<String, Set<String>> movieToUserMap = new HashMap<>();
        for (String[] rating: ratings) {
            if (rating[0].equals(user)) {
                userRatedMovies.add(rating[1]);
            }

            if (rating[2].charAt(0) <= '3') continue;

            // map user to movies
            Set<String> umovies = userToMovieMap.get(rating[0]);
            if (umovies == null) {
                umovies = new HashSet<>();
                userToMovieMap.put(rating[0], umovies);
            }
            umovies.add(rating[1]);

            if (!rating[0].equals(user)) {
                // map movies to user
                Set<String> userSet = movieToUserMap.get(rating[1]);
                if (userSet == null) {
                    userSet = new HashSet<>();
                    movieToUserMap.put(rating[1], userSet);
                }

                userSet.add(rating[0]);
            }
        }

        // find other users with same interest
        Set<String> similarUsers = new HashSet<>();
        for (String movie: userToMovieMap.get(user)) {
            if (movieToUserMap.get(movie) != null) {
                similarUsers.addAll(movieToUserMap.get(movie));
            }
        }
        // return movie user haven't seen
        List<String> result = new ArrayList<>();
        Set<String> similarMovies = new HashSet<>();
        for (String s: similarUsers) {
            similarMovies.addAll(userToMovieMap.get(s));
        }

        similarMovies.removeAll(userRatedMovies);

        result.addAll(similarMovies);

        return result;

    }
}