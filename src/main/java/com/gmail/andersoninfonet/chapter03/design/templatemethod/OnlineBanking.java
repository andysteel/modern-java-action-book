package com.gmail.andersoninfonet.chapter03.design.templatemethod;

abstract class OnlineBanking {
    public void processCustomer(int id){
    Customer c = new Customer(id, "Anderson Dias", 40);
    makeCustomerHappy(c);
    }
    abstract void makeCustomerHappy(Customer c);
}
