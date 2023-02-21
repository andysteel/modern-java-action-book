package com.gmail.andersoninfonet.chapter03.design.strategy;

public class IsNumeric implements ValidationText {

    @Override
    public boolean execute(String text) {
        return text.matches("\\d+");
    }
    
}
