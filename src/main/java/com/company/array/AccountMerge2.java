package com.company.array;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountMerge2 {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, List<Set<String>>> accountEmails = new HashMap<>();

        // parse accounts
        for (List<String> account : accounts) {
            String name = account.get(0);
            List<String> emails = account.subList(1, account.size());
            if (!accountEmails.containsKey(name)) {
                accountEmails.put(name, new ArrayList<>());
            }
            accountEmails.get(name).add(new HashSet<>(emails));
        }

        // merge accounts, accounts that have common email will be merged
        for (String key : accountEmails.keySet()) {
            List<Set<String>> emails = accountEmails.get(key);
            for (int i=0; i<emails.size(); i++) {
                for (int j=i+1; j<emails.size(); j++) {
                    Set<String> emailSet1 = emails.get(i);
                    Set<String> emailSet2 = emails.get(j);
                    if (emailSet1.stream().anyMatch(emailSet2::contains)) {
                        emailSet1.addAll(emailSet2);
                        emails.remove(j);
                        j--;
                    }
                }
            }
        }

        // convert to result
        List<List<String>> result = new ArrayList<>();
        for (String key : accountEmails.keySet()) {
            List<Set<String>> emails = accountEmails.get(key);
            for (Set<String> emailSet : emails) {
                List<String> emailList = new ArrayList<>();
                emailList.add(key);
                emailList.addAll(emailSet);
                result.add(emailList);
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
        AccountMerge2 merge = new AccountMerge2();
        List<List<String>> accounts = new ArrayList<>();
        // [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
        // [["David","David0@m.co","David1@m.co"],["David","David3@m.co","David4@m.co"],["David","David4@m.co","David5@m.co"],["David","David2@m.co","David3@m.co"],["David","David1@m.co","David2@m.co"]]
        accounts.add(Arrays.asList(new String[]{"John","johnsmith@mail.com","john_newyork@mail.com"}));
        accounts.add(Arrays.asList(new String[]{"John","johnsmith@mail.com","john00@mail.com"}));
        accounts.add(Arrays.asList(new String[]{"Mary","mary@mail.com"}));
        accounts.add(Arrays.asList(new String[]{"John","johnnybravo@mail.com"}));

        List<List<String>> result = merge.accountsMerge(accounts);

        System.out.println(result);
        //[[John, john00@mail.com, john_newyork@mail.com, johnsmith@mail.com], [Mary, mary@mail.com], [John, johnnybravo@mail.com]]
        assertEquals(3, result.size());
    }

    @Test
    public void testRun2() {
        AccountMerge2 merge = new AccountMerge2();
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

        assertEquals(1, result.size());
    }

    @Test
    public void testRun3() {
        List<Integer> list = new ArrayList<>();

    }
}
