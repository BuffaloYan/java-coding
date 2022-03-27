package com.company.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class AccountMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<Integer, Set<String>> mapAccountId = new HashMap<>();
        // map accounts to account id
        for (int i=0; i<accounts.size(); i++) {
            Set<String> emailList = new TreeSet<>(accounts.get(i));
            // remove name
            emailList.remove(accounts.get(i).get(0));
            mapAccountId.put(i, emailList);
        }

        // email to account ids
        Map<String, List<Integer>> mapEmail = new HashMap<>();
        for(int i=0; i<accounts.size(); i++) {
            for (String email: accounts.get(i)) {
                if (email.indexOf("@") < 0) continue;
                List<Integer> ids = mapEmail.get(email);
                if (ids == null) {
                    ids = new ArrayList<>();
                    mapEmail.put(email, ids);
                }

                ids.add(i);
            }
        }


        // use disjoint set
        int[] accountIds = new int[accounts.size()];
        for (int i=0; i<accountIds.length; i++) {
            accountIds[i] = i;
        }

        // union
        for (String email: mapEmail.keySet()) {
            List<Integer> ids = mapEmail.get(email);
            if (ids.size() > 1) {
                for (int i=1; i<ids.size(); i++) {
                    int rootA = getRoot(accountIds, ids.get(i));
                    int rootB = getRoot(accountIds, ids.get(0));
                    if (rootA != rootB) {
                        accountIds[rootA] = rootB;
                    }

                }
            }
        }

        // merge
        for (int i=0; i<accountIds.length; i++) {
            int root = getRoot(accountIds, i);
            if (root != i) {
                // merge account i into root
                mapAccountId.get(root)
                        .addAll(mapAccountId.get(i));
                mapAccountId.get(i).clear();
            }
        }

        // consolidate result
        List<List<String>> result = new ArrayList<>();
        for (int i=0; i<accounts.size(); i++) {
            if (mapAccountId.get(i).size() > 0) {
                List<String> emails = new ArrayList<>();
                // add name as first
                emails.add(accounts.get(i).get(0));
                emails.addAll(mapAccountId.get(i));
                result.add(emails);
            }
        }

        return result;
    }

    private int getRoot(int[] groups, int index) {
        if (groups[index] == index) return index;

        return groups[index] = getRoot(groups, groups[index]);
    }

    @Test
    public void testRun() {
        AccountMerge merge = new AccountMerge();
        List<List<String>> accounts = new ArrayList<>();
        // [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
        // [["David","David0@m.co","David1@m.co"],["David","David3@m.co","David4@m.co"],["David","David4@m.co","David5@m.co"],["David","David2@m.co","David3@m.co"],["David","David1@m.co","David2@m.co"]]
        accounts.add(Arrays.asList(new String[]{"John","johnsmith@mail.com","john_newyork@mail.com"}));
        accounts.add(Arrays.asList(new String[]{"John","johnsmith@mail.com","john00@mail.com"}));
        accounts.add(Arrays.asList(new String[]{"Mary","mary@mail.com"}));
        accounts.add(Arrays.asList(new String[]{"John","johnnybravo@mail.com"}));

        List<List<String>> result = merge.accountsMerge(accounts);

        Assert.assertEquals(3, result.size());
    }

    @Test
    public void testRun2() {
        AccountMerge merge = new AccountMerge();
        List<List<String>> accounts = new ArrayList<>();
        // [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
        // [["David","David0@m.co","David1@m.co"]
        // ,["David","David3@m.co","David4@m.co"]
        // ,["David","David4@m.co","David5@m.co"]
        // ,["David","David2@m.co","David3@m.co"]
        // ,["David","David1@m.co","David2@m.co"]]
        accounts.add(Arrays.asList(new String[]{"David","David0@m.co","David1@m.co"}));
        accounts.add(Arrays.asList(new String[]{"David","David3@m.co","David4@m.co"}));
        accounts.add(Arrays.asList(new String[]{"David","David4@m.co","David5@m.co"}));
        accounts.add(Arrays.asList(new String[]{"David","David2@m.co","David3@m.co"}));
        accounts.add(Arrays.asList(new String[]{"David","David1@m.co","David2@m.co"}));

        List<List<String>> result = merge.accountsMerge(accounts);

        Assert.assertEquals(1, result.size());
    }

    @Test
    public void testRun3() {
        List<Integer> list = new ArrayList<>();

    }
}
