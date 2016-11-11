package com.company.lunch;

import com.company.util.Constants;
import com.company.menu.MenuItem;

import static com.company.util.Constants.*;

public enum Dish implements MenuItem {
    ITALIAN(ITALIAN_PRICE) {
        @Override
        public String getName() {
            return Constants.ITALIAN;
        }
    }, MEXICAN(MEXICAN_PRICE) {
        @Override
        public String getName() {
            return Constants.MEXICAN;
        }
    }, POLISH(POLISH_PRICE) {
        @Override
        public String getName() {
            return Constants.POLISH;
        }
    };

    public int price;

    Dish(int price) {
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }
}
