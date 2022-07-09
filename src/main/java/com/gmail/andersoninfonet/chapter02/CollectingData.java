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

import static java.util.stream.Collectors.summarizingInt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import com.gmail.andersoninfonet.chapter02.models.Dish;
import com.gmail.andersoninfonet.chapter02.models.Dish.Type;

public class CollectingData {
    
    public static void main(String[] args) {
        IntSummaryStatistics intSummaryStatistics = Dishes.toList()
        .stream()
        .collect(summarizingInt(Dish::getCalories));
        System.out.println(intSummaryStatistics);

        var teste = new StringJoiner(", ");
        teste.add("Anderson");
        teste.add("Luciana");
        teste.add("Eduardo");
        teste.add("Nubia");

        System.out.println(teste.toString());

        Map<Type, List<Dish>> grouped = Dishes.toList()
        .stream()
        .collect(Collectors.groupingBy(Dish::getType));

        System.out.println(grouped);

        enum CaloricLevel { DIET, NORMAL, FAT }

    Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = Dishes.toList().stream().collect(
Collectors.groupingBy(dish -> {
            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
            else return CaloricLevel.FAT;
        }));

        System.out.println(dishesByCaloricLevel);

        Map<Dish.Type, List<Dish>> caloricDishesByType =
            Dishes.toList().stream()
            .collect(Collectors.groupingBy(Dish::getType,
            Collectors.filtering(dish -> dish.getCalories() > 500, Collectors.toList())));

        System.out.println(caloricDishesByType);

        Map<String, List<String>> dishTags = new HashMap<>();
        dishTags.put("pork", Arrays.asList("greasy", "salty"));
        dishTags.put("beef", Arrays.asList("salty", "roasted"));
        dishTags.put("chicken", Arrays.asList("fried", "crisp"));
        dishTags.put("french fries", Arrays.asList("greasy", "fried"));
        dishTags.put("rice", Arrays.asList("light", "natural"));
        dishTags.put("season fruit", Arrays.asList("fresh", "natural"));
        dishTags.put("pizza", Arrays.asList("tasty", "salty"));
        dishTags.put("prawns", Arrays.asList("tasty", "roasted"));
        dishTags.put("salmon", Arrays.asList("delicious", "fresh"));

        Map<Dish.Type, Set<String>> dishNamesByType =
            Dishes.toList().stream()
            .collect(
                Collectors.groupingBy(Dish::getType,
                Collectors.flatMapping(dish -> dishTags.get( dish.getName() ).stream(),
                Collectors.toSet())));

        System.out.println(dishNamesByType);

        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel =
            Dishes.toList().stream().collect(
                Collectors.groupingBy(Dish::getType,
                Collectors.groupingBy(dish -> {
                        if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                        else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                        else return CaloricLevel.FAT;
                    })
                )
            );

        dishesByTypeCaloricLevel.keySet()
        .stream()
        .forEach(k -> {
            System.out.println(k);
            Map<CaloricLevel, List<Dish>> calories = dishesByTypeCaloricLevel.get(k);
            calories.keySet()
            .stream()
            .forEach(c -> {
                System.out.println("***"+c);
                List<Dish> list = calories.get(c);
                list.forEach(d -> System.out.println("******"+d.getName()));
            });
        });

        Map<Dish.Type, Long> typesCount = 
        Dishes.toList().stream()
        .collect(
            Collectors.groupingBy(Dish::getType, Collectors.counting()));

        System.out.println(typesCount);

        Map<Dish.Type, Dish> mostCaloricByType =
            Dishes.toList()
            .stream()
            .collect(
                Collectors.groupingBy(Dish::getType,
                Collectors.collectingAndThen(
                Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                                        Optional::get)));

        System.out.println(mostCaloricByType);

        Map<Boolean, List<Dish>> partitionedMenu =
            Dishes.toList().stream().collect(Collectors.partitioningBy(Dish::isVegetarian));

        System.out.println(partitionedMenu);

        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType =
            Dishes.toList().stream()
            .collect(
                Collectors.partitioningBy(Dish::isVegetarian,
                Collectors.groupingBy(Dish::getType)));

        System.out.println(vegetarianDishesByType);

        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian =
            Dishes.toList().stream()
            .collect(
                Collectors.partitioningBy(Dish::isVegetarian,
                Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                Optional::get)));

        System.out.println(mostCaloricPartitionedByVegetarian);

        Map<Boolean, Long> countVegetarian = Dishes.toList().stream()
        .collect(
            Collectors.partitioningBy(Dish::isVegetarian,
            Collectors.counting()));

        System.out.println(countVegetarian);

        List<Dish> testCustomCollector = Dishes.toList()
            .stream()
            .collect(new CustomListCollector<Dish>());
        
        System.out.println(testCustomCollector);

        Dishes.toList()
            .stream()
            .collect(
                ArrayList::new, 
                (list, item) -> list.add(item), 
                ArrayList::addAll);
    }
}
