package com.company.drinks;

import static com.company.util.Constants.LEMON_PRICE;

public class LemonGarnish implements Beverage {

    private Beverage drink;

    public LemonGarnish(Beverage drink) {
        this.drink = drink;
    }

    @Override
    public int getPrice() {
        return drink != null ? LEMON_PRICE + drink.getPrice() : LEMON_PRICE;
    }

}
