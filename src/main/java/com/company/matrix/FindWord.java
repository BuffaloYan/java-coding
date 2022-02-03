package com.company.matrix;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindWord {
    public static final int[][] dir = {{0,1}, {1, 0}, {0,-1}, {-1,0}};

    public List<String> findWords(char[][] board, String[] words) {
        List<int[]>[] charLocations = new List[26];

        for (int i=0; i<26; i++) {
            charLocations[i] = new ArrayList<>();
        }

        // collection locations for each characters
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                int charIndex = board[i][j] - 'a';
                List<int[]> list = charLocations[charIndex];
                list.add(new int[]{i, j});
            }
        }

        List<String> result = new ArrayList<>();
        Set<String> resultSet = new HashSet<String>();
        boolean[][] visited;
        for (String w: words) {
            // try looking for word at each starting point
            for (int[] location: charLocations[w.charAt(0)-'a']) {
                visited = new boolean[board.length][board[0].length];
                findWord(board, location[0], location[1], w, 0, resultSet, visited);
            }
        }

        result.addAll(resultSet);
        return result;
    }

    public void findWord(char[][] board, int i, int j, String word, int index, Set<String> result, boolean[][] visited) {
        if (i<0 || i>=board.length || j<0 || j>=board[0].length || visited[i][j]) {
            // out of bound
            return;
        }

        if (board[i][j] != word.charAt(index)) {
            return;
        }

        visited[i][j] = true;

        if (index == word.length()-1) {
            result.add(word);
            return;
        }

        // DFS search each direction
        for (int k=0; k<dir.length; k++) {
            findWord(board, i+dir[k][0], j+dir[k][1], word, index+1, result, visited);
        }

        visited[i][j] = false;
    }

    @Test
    public void testRun() {
        // [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
        char[][] board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] words = {"oath","pea","eat","rain"};

        FindWord findWord = new FindWord();
        List<String> result = findWord.findWords(board, words);

        for (String s: result) {
            System.out.println(s);
        }
    }

    @Test
    public void testRun2() {
        // [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
        char[][] board = {{'a','b','c'},{'a','e','d'},{'a','f','g'}};
        String[] words = {"abcdefg","gfedcbaaa","eaabcdgfa","befa","dgc","ade"};

        FindWord findWord = new FindWord();
        List<String> result = findWord.findWords(board, words);

        for (String s: result) {
            System.out.println(s);
        }
    }

    //[["a","b","c"],["a","e","d"],["a","f","g"]]
    //["abcdefg","gfedcbaaa","eaabcdgfa","befa","dgc","ade"]
}
