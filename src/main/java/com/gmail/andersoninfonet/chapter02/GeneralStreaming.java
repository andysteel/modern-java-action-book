/*-
 * =====LICENSE-START=====
 * Java 17 Application
 * ------
 * Copyright (C) 2022 Anderson Dias
 * ------
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * =====LICENSE-END=====
 */
package com.gmail.andersoninfonet.chapter02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.gmail.andersoninfonet.chapter02.models.Trader;
import com.gmail.andersoninfonet.chapter02.models.Transaction;

public class GeneralStreaming {

    private static List<Transaction> transactions = new ArrayList<>();
    private static Trader raoul = new Trader("Raoul", "Cambridge");
    private static Trader mario = new Trader("Mario", "Milan");
    private static Trader alan = new Trader("Alan", "Cambridge");
    private static Trader brian = new Trader("Brian", "Cambridge");

    static {
        transactions.add(new Transaction(brian, 2011, 300));
        transactions.add(new Transaction(raoul, 2012, 1000));
        transactions.add(new Transaction(raoul, 2011, 400));
        transactions.add(new Transaction(mario, 2012, 710));
        transactions.add(new Transaction(mario, 2012, 700));
        transactions.add(new Transaction(alan, 2012, 950));
    }
    
    public static void main(String[] args) {

        transactions
            .stream()
            .filter(t -> t.getYear() == 2011)
            .sorted((t1, t2) -> Integer.min(t1.getValue(), t2.getValue()))
            .forEach(System.out::println);

        transactions
            .stream()
            .map(Transaction::getTrader)
            .map(Trader::getCity)
            .distinct()
            .forEach(System.out::println);

        transactions
            .stream()
            .map(Transaction::getTrader)
            .filter(t -> t.getCity().equals("Cambridge"))
            .distinct()
            .sorted((t1, t2) -> t1.getName().compareTo(t2.getName()))
            .forEach(System.out::println);

        var names =  transactions
            .stream()
            .map(Transaction::getTrader)
            .distinct()
            .sorted((t1, t2) -> t1.getName().compareTo(t2.getName()))
            .map(Trader::getName)
            .collect(Collectors.joining(","));

        System.out.println(names);

        transactions
            .stream()
            .map(Transaction::getTrader)
            .filter(t -> t.getCity().equals("Milan"))
            .findAny()
            .ifPresent(System.out::println);

        transactions
            .stream()
            .filter(t -> t.getTrader().getCity().equals("Cambridge"))
            .map(Transaction::getValue)
            .forEach(System.out::println);

        transactions
            .stream()
            .map(Transaction::getValue)
            // .max((v1,v2) -> v1.compareTo(v2))
            .reduce(Integer::max)
            .ifPresent(System.out::println);

        transactions
            .stream()
            .map(Transaction::getValue)
            // .min((v1,v2) -> v1.compareTo(v2))
            .reduce(Integer::min)
            .ifPresent(System.out::println);

        var max =transactions
            .stream()
            .mapToInt(Transaction::getValue)
            .sorted()
            .max()
            .getAsInt();

        System.out.println(max);


        //binary search example with complex object implementing Comparable
        transactions.sort((t1, t2) -> Integer.compare(t1.getValue(), t2.getValue()));        
        var transactionFound =  Collections.binarySearch(
            transactions,
            new Transaction(null, 0, 1000), 
            null);
        
        System.out.println("transaction found "+transactions.get(transactionFound));
    }
}
