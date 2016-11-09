package com.company.lunch;

import com.company.lunch.Dessert;
import com.company.lunch.Dish;

public class Lunch {

    private Dish dish;

    private Dessert dessert;

    public Lunch(Dish cuisine, Dessert dessert) {
        this.dish = cuisine;
        this.dessert = dessert;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Dessert getDessert() {
        return dessert;
    }

    public void setDessert(Dessert dessert) {
        this.dessert = dessert;
    }

    public int getPrice() {
        return (dish != null ? dish.price : 0)
                + (dessert != null ? dessert.price : 0);
    }
}
