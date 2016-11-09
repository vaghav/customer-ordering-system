package com.company.drinks;

import static com.company.util.Constants.ICE_PRICE;

public class IceCubeGarnish implements Beverage {

    private Beverage drink;

    public IceCubeGarnish(Beverage drink) {
        this.drink = drink;
    }

    @Override
    public int getPrice() {
        return drink != null ? ICE_PRICE + drink.getPrice() : ICE_PRICE;
    }
}

