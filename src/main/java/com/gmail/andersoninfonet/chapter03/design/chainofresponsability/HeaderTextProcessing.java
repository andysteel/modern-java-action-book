package com.gmail.andersoninfonet.chapter03.design.chainofresponsability;

public class HeaderTextProcessing extends ProcessingObject<String> {

    @Override
    public String handleWork(String text) {
        return "From Raoul, Mario and Alan: " + text;
    }
}
