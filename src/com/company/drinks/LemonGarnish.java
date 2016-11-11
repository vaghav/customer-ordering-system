package com.company.drinks;

import com.company.menu.MenuItem;
import com.company.util.Constants;

import static com.company.util.Constants.LEMON_PRICE;

public class LemonGarnish implements MenuItem {

    private MenuItem drink;

    public LemonGarnish(Drink drink) {
        this.drink = drink;
    }

    @Override
    public int getPrice() {
        return drink != null ? LEMON_PRICE + drink.getPrice() : LEMON_PRICE;
    }

    @Override
    public String getName() {
        return Constants.LEMON;
    }

}
