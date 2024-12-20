package com.company.recursive;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CoinCombination {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int[] currentChoices =new int[candidates.length];
        Arrays.sort(candidates);
        combine(candidates, target, currentChoices, 0, 0, result);

        return result;
    }

    private void combine(int[] candidates, int target, int[] currentChoices, int currentCandidate, int currentSum, List<List<Integer>> result) {
        if (currentSum >= target) {
            if (currentSum == target) {
                // find a solution
                List<Integer> copy = new ArrayList<>();
                for (int i=0; i<currentChoices.length; i++) {
                    if (currentChoices[i]>0) {
                        for (int j=0; j<currentChoices[i]; j++) {
                            copy.add(candidates[i]);
                        }
                    }
                }

                result.add(copy);
            }

            return;
        }

        if (currentCandidate > candidates.length-1) {
            return;
        }

        // try without current choice
        combine(candidates, target, currentChoices.clone(), currentCandidate+1, currentSum, result);

        while (candidates[currentCandidate] <= target - currentSum) {
            currentChoices[currentCandidate]++;
            currentSum += candidates[currentCandidate];
            combine(candidates, target, currentChoices.clone(), currentCandidate+1, currentSum, result);

        }

    }

    @Test
    public void testRun1() {
        int[] data = {2, 3, 6, 7};
        CoinCombination coinCombination = new CoinCombination();
        List<List<Integer>> result = coinCombination.combinationSum(data, 7);
        for (List<Integer> choices: result) {
            List<String> sList = choices.stream().map( c -> c.toString() ).collect(Collectors.toList());
            System.out.println( "[" + String.join(", ", sList) + "]");
        }
    }
}
