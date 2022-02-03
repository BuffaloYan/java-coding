package com.company.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class Sum3 {
    public List<List<Integer>> threeSum(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
            Integer index = map.get(nums[i]);
            if(index == null || index < i) {
                map.put(nums[i], i);
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        Map<String, List<Integer>> resultMap = new HashMap<>();

        int[] triples = new int[3];
        for(int i=0; i<nums.length-2; i++) {
            for(int j=i+1; j<nums.length-1; j++) {
                int remain = 0 - nums[i] - nums[j];

                Integer listIndex = map.get(remain);
                if(listIndex != null && listIndex > j) {
                    // reuse array for efficiency
                    triples[0] = nums[i];
                    triples[1] = nums[j];
                    triples[2] = remain;
                    Arrays.sort(triples);
                    String key = triples[0]+","+triples[1]+","+triples[2];
                    if (resultMap.containsKey(key)) {
                        // skip duplicates
                        continue;
                    }

                    // had to create list to add to result
                    List<Integer> list = new ArrayList<>();
                    list.add(triples[0]);
                    list.add(triples[1]);
                    list.add(triples[2]);
                    resultMap.put(key, list);

                }
            }
        }

        result.addAll(resultMap.values());
        return result;
    }

    @Test
    public void testRun1() {

        Sum3 sum3 = new Sum3();

        int[] data = new int[]{-1,0,1,2,-1,-4};
        List<List<Integer>> result = sum3.threeSum(data);

        Assert.assertEquals(2, result.size());
    }

    @Test
    public void testRun2() {

        Sum3 sum3 = new Sum3();

        int[] data = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        List<List<Integer>> result = sum3.threeSum(data);

        Assert.assertEquals(1, result.size());

        String a = "";
        int level = (int) Math.ceil( Math.log(5));
    }
}
