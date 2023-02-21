package com.gmail.andersoninfonet.chapter03.design.observer;

public interface Subject {
    
    void registerObserver(Observer observer);

    void notifyObservers(String tweet);
}
