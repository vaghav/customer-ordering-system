package com.company.menu;

import com.company.drinks.Beverage;
import com.company.lunch.Lunch;

public class CustomerOrder {

    private final Lunch lunch;

    private final Beverage beverage;

    private CustomerOrder(OrderBuilder orderBuilder) {
        this.lunch = orderBuilder.lunch;
        this.beverage = orderBuilder.beverage;
    }

    public Lunch getLunch() {
        return lunch;
    }

    public Beverage getBeverage() {
        return beverage;
    }

    public static class OrderBuilder {

        private Lunch lunch;

        private Beverage beverage;

        public OrderBuilder setLunch(Lunch lunch) {
            this.lunch = lunch;
            return this;
        }

        public OrderBuilder setBeverage(Beverage beverage) {
            this.beverage = beverage;
            return this;
        }

        public CustomerOrder build() {
            return new CustomerOrder(this);
        }
    }
}
