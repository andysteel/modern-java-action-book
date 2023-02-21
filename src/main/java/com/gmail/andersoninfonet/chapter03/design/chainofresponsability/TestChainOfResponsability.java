package com.gmail.andersoninfonet.chapter03.design.chainofresponsability;

import java.util.function.UnaryOperator;

public class TestChainOfResponsability {
    
    public static void main(String[] args) {
        
        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckerProcessing();
        p1.setSuccessor(p2);
        String result = p1.handle("Aren't labdas really sexy?!!");
        System.out.println(result);

        //using lambdas
        UnaryOperator<String> headerProcessing = (String text) -> "From Raoul, Mario and Alan: " + text;
        UnaryOperator<String> spellCheckerProcessing = (String text) -> text.replaceAll("labda", "lambda");        
        String chainResult = headerProcessing.andThen(spellCheckerProcessing).apply("Aren't labdas really sexy?!!");
        System.out.println(chainResult);
    }
}
