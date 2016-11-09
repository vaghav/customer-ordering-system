package com.company.drinks;

import com.company.util.Constants;
import com.company.util.MenuItem;

import static  com.company.util.Constants.COLA_PRICE;
import static  com.company.util.Constants.SPRITE_PRICE;

public enum Drink implements Beverage, MenuItem {
    COLA(COLA_PRICE) {
        @Override
        public String getName() {
            return Constants.COLA;
        }
    }, SPRITE(SPRITE_PRICE) {
        @Override
        public String getName() {
            return Constants.SPRITE;
        }
    };

    public int price;

    Drink(int price) {
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }

}