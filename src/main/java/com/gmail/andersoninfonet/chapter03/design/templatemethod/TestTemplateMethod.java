package com.gmail.andersoninfonet.chapter03.design.templatemethod;

public class TestTemplateMethod {
    
    public static void main(String[] args) {
        
        var banking = new BrandBanking();
        banking.processCustomer(848);

        //using Lambda
        new OnlineBankingLambda()
        .processCustomer(434, customer -> System.out.println("id " + customer.id() + " name: " + customer.name() + " age: " + customer.age()));
    }
}
