package com.company.menu;

import com.company.lunch.Lunch;

import java.util.List;

public class CustomerOrder {

    private  List<MenuItem> customerOrders;

    public List<MenuItem> getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(List<MenuItem> customerOrders) {
        this.customerOrders = customerOrders;
    }
}
