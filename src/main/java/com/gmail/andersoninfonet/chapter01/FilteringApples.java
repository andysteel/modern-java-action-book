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
package com.gmail.andersoninfonet.chapter01;

import static com.gmail.andersoninfonet.chapter01.Color.RED;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;

public class FilteringApples {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
            new Apple(80, Color.GREEN),
            new Apple(155, Color.GREEN),
            new Apple(120, RED)
        );

        //[Apple[weight=80, color=GREEN], Apple[weight=155, color=GREEN]]
        List<Apple> greenApples = filteringApples(inventory, a -> a.color().equals(Color.GREEN));
        System.out.println(greenApples);

        //[]
        List<Apple> redApples = filteringApples(inventory, a -> a.color().equals(RED) && a.weight() > 150);
        System.out.println(redApples);

        //[Apple[weight=155, color=GREEN]]
        List<Apple> heavyApples = filteringApples(inventory, FilteringApples::isHeavyApple);
        System.out.println(heavyApples);

        //A Light GREEN Brazilian apple.
        //A Heavy GREEN Brazilian apple.
        //A Light RED Brazilian apple.
        prettyPrintApple(inventory, a -> {
            var level =  a.weight() > 150 ? "Heavy" : "Light";
            return "A " + level + " " + a.color() + " Brazilian apple.";
        });

        //[Apple[weight=80, color=GREEN], Apple[weight=120, color=RED], Apple[weight=155, color=GREEN]]
        inventory.sort(Comparator.comparing(Apple::weight));
        System.out.println(inventory);

        //[Apple[weight=120, color=RED]]
        List<Apple> redApplesFiltered = filter(inventory, (apple) -> RED.equals(apple.color()));
        System.out.println(redApplesFiltered);

        //Lambdas compositions examples
        Predicate<Apple> redApplePredicate = a -> a.color().equals(RED);
        Predicate<Apple> redAndHeavyAppleOrGreen = 
        redApplePredicate
        .and(apple -> apple.weight() > 110)
        .or(apple -> Color.GREEN.equals(apple.color()));
        List<Apple> redAndHeavyAppleOrGreenFiltered = filter(inventory, redAndHeavyAppleOrGreen);
        System.out.println(redAndHeavyAppleOrGreenFiltered);

        IntUnaryOperator f = v -> v + 1;
        IntUnaryOperator resultInt = f.andThen(x -> x * 2);
        System.out.println(resultInt.applyAsInt(3));

        //calculate integrate
        double result = integrate(x -> x + 10, 3, 7);
        System.out.println("Integrate result "+ result);

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> threadName = executorService.submit(() -> Thread.currentThread().getName());
        System.out.println(threadName);
    }

    static List<Apple> filteringApples(List<Apple> apples, Predicate<Apple> applePredicate) {
        var applesFiltered = new ArrayList<Apple>();
        for(Apple apple : apples) {
            if(applePredicate.test(apple)) {
                applesFiltered.add(apple);
            }
        }
        return applesFiltered;
    }

    static void prettyPrintApple(List<Apple> apples, Function<Apple, String> functionToPrint) {
        for (Apple apple : apples) {
            var output = functionToPrint.apply(apple);
            System.out.println(output);
        }
    }

    static double integrate(DoubleFunction<Double> f, double a, double b) {
        return (f.apply(a) + f.apply(b)) * (b - a) / 2.0;
    }
        

    static boolean isHeavyApple(Apple apple) {
        return apple.weight() > 150;
    }

    //Generic filter
    static <T> List<T> filter(List<T> list, Predicate<T> p) {
        var result = new ArrayList<T>();
        list.forEach( i -> {
            if(p.test(i)) {
                result.add(i);
            }
        });
        return result;
    }
}

record Apple(int weight, Color color) {}

enum Color { RED, GREEN }

interface ApplePredicate {
    boolean test(Apple apple);
}

class GreenApplePredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
        return apple.color().equals(Color.GREEN);
    }
    
}

class AppleRedAndHeavyPredicate implements ApplePredicate {
    public boolean test(Apple apple){
        return RED.equals(apple.color())
        && apple.weight() > 150;
    }
}
