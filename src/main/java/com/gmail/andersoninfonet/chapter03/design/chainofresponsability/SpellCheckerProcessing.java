package com.gmail.andersoninfonet.chapter03.design.chainofresponsability;

public class SpellCheckerProcessing extends ProcessingObject<String> {

    @Override
    public String handleWork(String text) {
        return text.replaceAll("labda", "lambda");
    }
}
