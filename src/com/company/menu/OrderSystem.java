package com.company.menu;


import com.company.drinks.Beverage;
import com.company.drinks.Drink;
import com.company.drinks.IceCubeGarnish;
import com.company.drinks.LemonGarnish;
import com.company.lunch.Dessert;
import com.company.lunch.Dish;
import com.company.lunch.Lunch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.company.util.Constants.*;

public class OrderSystem {

    private static final String GARNISH_REGEX = "(?i)(\\blemon(\\b.*\\bice\\b)?)|\\bice\\b$";

    public static void main(String[] args) {
        System.out.println("Would you like to order lunch or drink something?");
        Scanner scanner = new Scanner(System.in);
        List<CustomerOrder> orders = new ArrayList<>();

        //TODO: Several lunch or drinks ordering functionality should be added in future
        orders.add(getLunchOrder(scanner));
        orders.add(getBeverageOrder(scanner));
        calculatePrice(orders);
    }

    private static CustomerOrder getBeverageOrder(Scanner scanner) {
        System.out.println("Would you like to drink something? Please enter '" + BEVERAGE + "' or leave it empty otherwise");
        String beverage = getOrderedItem(scanner, "Please make correct order entering '" + BEVERAGE + "'", Menu.BEVERAGES);
        if (!beverage.isEmpty()) {
            String drink = getOrderedItem(scanner, "Please select drink: " + getItemNames(Drink.values()), Menu.DRINK);
            String garnish = getOrderedItem(scanner, "Please select garnish: " + LEMON + " or/and " + ICE, Menu.GARNISH);
            return buildDrinkOrder(drink, garnish);
        }
        return null;
    }

    private static CustomerOrder getLunchOrder(Scanner scanner) {
        String lunch = getOrderedItem(scanner, "Please make correct order and enter '" + LUNCH
                + "' or leave empty if you want to order some beverage", Menu.LUNCH);

        if (!lunch.isEmpty()) {
            String dish = getOrderedItem(scanner, "Please select dish: " + getItemNames(Dish.values()), Menu.DISHES);
            String dessertSuggestionMessage = "Would you like something from dessert? We offer " + getItemNames(Dessert.values())
                    + " Please enter desert name or leave it empty";
            String dessert = getOrderedItem(scanner, dessertSuggestionMessage, Menu.DESSERT);
            return buildLunchOrder(dish, dessert);
        }
        return null;
    }

    private static void calculatePrice(List<CustomerOrder> customerOrders) {
        int price = 0;
        for (CustomerOrder order : customerOrders) {
            if (order != null) {
                price = order.getLunch() != null ? order.getLunch().getPrice() : price;
                System.out.println("Price for a lunch : " + price);

                int beveragePrice = order.getBeverage() != null ? order.getBeverage().getPrice() : 0;
                price += beveragePrice;
                System.out.println("Price for a beverage : " + beveragePrice);
            }
        }
        System.out.println("====Total price of orderings : " + price + " =====Thank you!======");
    }

    private static CustomerOrder buildDrinkOrder(String drink, String garnish) {

        Beverage orderedBeverage = Drink.valueOf(drink.toUpperCase());

        if (garnish.toUpperCase().contains(LEMON.toUpperCase())) {
            orderedBeverage = new LemonGarnish(Drink.valueOf(drink.toUpperCase()));
        }

        if (garnish.toUpperCase().contains(ICE.toUpperCase())) {
            orderedBeverage = new IceCubeGarnish(orderedBeverage);
        }

        return new CustomerOrder.OrderBuilder()
                .setBeverage(orderedBeverage)
                .build();
    }

    private static CustomerOrder buildLunchOrder(String dish, String dessert) {

        Dessert dessertValue = !dessert.isEmpty() ? Dessert.valueOf(dessert.toUpperCase()) : null;

        return new CustomerOrder.OrderBuilder()
                .setLunch(new Lunch(Dish.valueOf(dish.toUpperCase()), dessertValue))
                .build();
    }

    private static List<String> getItemNames(MenuItem[] values) {
        return Arrays.stream(values).map(i -> i.getName().toLowerCase())
                .collect(Collectors.toList());
    }

    private static String getOrderedItem(Scanner scanner, String warningMessage, Menu menu) {
        String itemName;
        while (true) {
            System.out.println(warningMessage);
            itemName = scanner.nextLine();
            if (isValidItemFromMenu(itemName, menu)) {
                break;
            }
        }
        return itemName;
    }

    private static boolean isValidItemFromMenu(String order, Menu menu) {
        //TODO: Should be removed for getting rid of this switch case
        switch (menu) {
            case DISHES:
                return getItemNames(Dish.values()).contains(order);
            case DESSERT:
                return getItemNames(Dessert.values()).contains(order) || order.isEmpty();
            case DRINK:
                return getItemNames(Drink.values()).contains(order);
            case GARNISH:
                //TODO: Regex should be removed
                Pattern pattern = Pattern.compile(GARNISH_REGEX);
                Matcher matcher = pattern.matcher(order);
                return matcher.matches() || order.isEmpty();
            case LUNCH:
                return order.equalsIgnoreCase(LUNCH) || order.isEmpty();
            case BEVERAGES:
                return order.equalsIgnoreCase(BEVERAGE) || order.isEmpty();
        }
        return false;
    }
}
