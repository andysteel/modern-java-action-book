package com.gmail.andersoninfonet.chapter03.design.templatemethod;

import java.util.function.Consumer;

public class OnlineBankingLambda {
    
    public void processCustomer(int id, Consumer<Customer> makeCustomerHAppy) {
        Customer c = new Customer(id, "Anderson Dias", 40);
        makeCustomerHAppy.accept(c);
    }
}
