package com.gmail.andersoninfonet.chapter03.design.templatemethod;

public class BrandBanking extends OnlineBanking {

    @Override
    void makeCustomerHappy(Customer c) {
        System.out.println("id " + c.id() + " name: " + c.name() + " age: " + c.age());
    }
    
}
