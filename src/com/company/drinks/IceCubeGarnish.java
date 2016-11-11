package com.company.drinks;

import com.company.menu.MenuItem;
import com.company.util.Constants;

import static com.company.util.Constants.ICE_PRICE;

public class IceCubeGarnish implements MenuItem {

    private MenuItem drink;

    public IceCubeGarnish(MenuItem drink) {
        this.drink = drink;
    }

    @Override
    public int getPrice() {
        return drink != null ? ICE_PRICE + drink.getPrice() : ICE_PRICE;
    }

    @Override
    public String getName() {
        return Constants.BEVERAGE;
    }
}

