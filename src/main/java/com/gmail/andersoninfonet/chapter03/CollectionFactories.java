package com.gmail.andersoninfonet.chapter03;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.gmail.andersoninfonet.chapter02.Dishes;
import com.gmail.andersoninfonet.chapter02.models.Dish;

public class CollectionFactories {
    
    public static void main(String[] args) {
        
        //Immutable list
        List<String> friends = List.of("Raphael", "Olivia", "Thibaut");
        System.out.println(friends);

        //Immutable Set
        Set<String> friendsSet = Set.of("Raphael", "Olivia", "Thibaut");
        System.out.println(friendsSet);

        //Immutable map
        Map<String, Integer> ageOfFriends = Map.of("Raphael", 30, "Olivia", 34, "Thibaut", 36);
        System.out.println(ageOfFriends);

        //remove items with predicate
        List<Dish> dishes = Dishes.toList();
        boolean isRemoved = dishes
        .removeIf(dish -> dish.getCalories() > 500);

        System.out.println("removed "+isRemoved);
        System.out.println(dishes);

        //replace all items
        dishes.replaceAll(dish -> new Dish(dish.getName().toUpperCase(), dish.isVegetarian(), 0, null));
        System.out.println(dishes);
    }
}
