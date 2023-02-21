package com.gmail.andersoninfonet.chapter03.design.observer;

public class TestObserver {
    
    public static void main(String[] args) {
        
        Feed feed = new Feed();
        feed.registerObserver(new NYTimes());
        feed.registerObserver(new Guardian());
        feed.registerObserver(new LeMonde());

        feed.notifyObservers("The queen said her favourite book is Modern Java in Action!");

        //using Lambda
        feed.registerObserver((String tweet) -> {
            if(tweet != null && tweet.contains("tornado")){
                System.out.println("Breaking news in LA! " + tweet);
            }
        });
        feed.registerObserver((String tweet) -> {
            if(tweet != null && tweet.contains("metal")){
                System.out.println("The greatest show from... " + tweet);
            }
        });

        feed.notifyObservers("The big tornado arrives today");
    }
}
