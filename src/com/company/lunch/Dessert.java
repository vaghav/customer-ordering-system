package com.company.lunch;

import com.company.util.Constants;
import com.company.util.MenuItem;

import static com.company.util.Constants.CAKE_PRICE;

public enum Dessert implements MenuItem {
    CAKE(CAKE_PRICE) {
        @Override
        public String getName() {
            return Constants.CAKE;
        }
    };

    public int price;

    Dessert(int price) {
        this.price = price;
    }

}
