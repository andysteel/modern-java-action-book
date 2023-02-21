package com.gmail.andersoninfonet.chapter03.design.strategy;

public class Validator {
    
    private final ValidationText validationText;

    /**
     * @param validationText
     */
    public Validator(ValidationText validationText) {
        this.validationText = validationText;
    }

    public boolean validate(String text) {
        return this.validationText.execute(text);
    }
}
