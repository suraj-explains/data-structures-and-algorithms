package com.surajexplains.dsa.treemap;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;

public class TreeMapDemo {

    public static void main(String[] args) {
        TreeMap<Integer,Account> treeMap = new TreeMap<>(
                Collections.reverseOrder()
        );

        treeMap.put(1,new Account(1));
        treeMap.put(5,new Account(5));
        treeMap.put(4,new Account(4));
        treeMap.put(3,new Account(3));
        treeMap.put(2,new Account(2));

        treeMap.remove(1);

        SortedMap<Integer, Account> integerAccountSortedMap = treeMap.subMap(5, true,4,true);

        integerAccountSortedMap.entrySet().stream().forEach((e)->{
            System.out.println(e.getKey() + ":" + e.getValue().getAccountNumber());
        });


    }

    private static class Account{

        private Integer accountNumber;
        private BigDecimal balance;

        public Account(Integer accountNumber) {
            this.accountNumber = accountNumber;
        }

        public Integer getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(Integer accountNumber) {
            this.accountNumber = accountNumber;
        }

        public BigDecimal getBalance() {
            return balance;
        }

        public void setBalance(BigDecimal balance) {
            this.balance = balance;
        }
    }
}
