package com.gmail.andersoninfonet.chapter03.design.strategy;

public class TestStrategy {
    
    public static void main(String[] args) {
        Validator validatorLowercase = new Validator(new IsAllLowerCase());
        boolean b1 = validatorLowercase.validate("abbbccccdddddddeeefffff");

        Validator validatorNumeric = new Validator(new IsNumeric());
        boolean b2 = validatorNumeric.validate("bbbbbbbbbbbb");

        System.out.println(b1);
        System.out.println(b2);

        //using lambda
        Validator textValidator = new Validator(text -> text.matches("[a-z]+"));
        Validator numericValidator = new Validator(text -> text.matches("\\d+"));

        var b3 = textValidator.validate("ASDASD");
        var b4 = numericValidator.validate("12312312");

        System.out.println(b3);
        System.out.println(b4);
    }
}
