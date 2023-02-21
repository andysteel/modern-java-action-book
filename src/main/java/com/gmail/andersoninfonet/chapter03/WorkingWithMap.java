package com.gmail.andersoninfonet.chapter03;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class WorkingWithMap {

    private static byte[] calculateDigest(String key ) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        return messageDigest.digest(key.getBytes(StandardCharsets.UTF_8));
    }
    
    public static void main(String[] args)  {

        Map<String, Integer> ageOfFriends = Map.of("Raphael", 30, "Olivia", 34, "Thibaut", 36);
        //forEach in map
        ageOfFriends.forEach((friend, age) -> System.out.println(friend + " is " + age + " years old"));

        //sorting map
        Map<String, String> favouriteMovies = Map.ofEntries(new SimpleEntry("Raphael", "Star Wars"),
        new SimpleEntry<String,String>("Cristina", "Matrix"),
        new SimpleEntry("Olivia","James Bond"));

        favouriteMovies
            .entrySet()
            .stream()
            .sorted(Entry.comparingByKey())
            .forEachOrdered(System.out::println);
        System.out.println("**************************");
        favouriteMovies
            .entrySet()
            .stream()
            .sorted(Entry.comparingByValue())
            .forEachOrdered(System.out::println);

        System.out.println("**************************");

        //Default values for null keys
        System.out.println(favouriteMovies.getOrDefault("Olivia", "Matrix"));
        System.out.println(favouriteMovies.getOrDefault("Thibaut", "Matrix"));

        System.out.println("**************************");

        //computeIfAbsent
        //computeIfPresent
        Map<String, byte[]> dataToHash = new HashMap<>();
        List<String> lines = List.of("linha1","linha2","linha3");

        lines
            .forEach(line -> dataToHash.computeIfAbsent(line, t -> {
                try {
                    return calculateDigest(t);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                return new byte[]{};
            }));

        dataToHash.forEach((key, value) -> System.out.println(key + " - " + value));

        //remove from map
        dataToHash.remove("linha1", "[B@7cca494b");

        //replaceAll
        Map<String, String> favouriteMovies2 = new HashMap<>();
        favouriteMovies2.put("Raphael", "Star Wars");
        favouriteMovies2.put("Olivia", "james bond");
        favouriteMovies2.replaceAll((friend, movie) -> movie.toUpperCase());
        System.out.println(favouriteMovies2);

        System.out.println("**************************");

        //merge Maps
        Map<String, String> everyMovies = new HashMap<>(favouriteMovies);
        everyMovies.putAll(favouriteMovies2);
        System.out.println(everyMovies);

    }
}
