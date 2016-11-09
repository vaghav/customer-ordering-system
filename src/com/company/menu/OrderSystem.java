package com.company.menu;


import com.company.drinks.Beverage;
import com.company.drinks.Drink;
import com.company.drinks.IceCubeGarnish;
import com.company.drinks.LemonGarnish;
import com.company.lunch.Dessert;
import com.company.lunch.Dish;
import com.company.lunch.Lunch;
import com.company.util.Constants;
import com.company.util.Menu;
import com.company.util.MenuItem;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.company.util.Constants.LUNCH;
import static com.company.util.Constants.DRINK;
import static com.company.util.Constants.LEMON;
import static com.company.util.Constants.ICE;

public class OrderSystem {

    public static void main(String[] args) {
        System.out.println("Would you like to order lunch or some drink?");
        Scanner scanner = new Scanner(System.in);
        String order;

        order = getCustomerGeneralOrder(scanner, "Please make correct order " + LUNCH + " or " + DRINK);

        int price;
        if (order.equalsIgnoreCase(LUNCH)) {
            price = makeLunchOrder(scanner).getLunch().getPrice();
        } else {
            price = makeDrinkOrder(scanner).getBeverage().getPrice();
        }

        System.out.printf("Total price of orderings : " + price);
    }

    private static CustomerOrder makeDrinkOrder(Scanner scanner) {
        String drink = getOrderedItem(scanner, "Please select beverage: " + getItems(Drink.values()), Menu.DRINK);
        String garnish = getOrderedItem(scanner, "Please select garnish: " + LEMON + " or/and " + ICE, Menu.DESSERT.GARNISH);
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

    private static CustomerOrder makeLunchOrder(Scanner scanner) {
        String dish = getOrderedItem(scanner, "Please select dish: " + getItems(Dish.values()), Menu.DISHES);
        String dessertSuggestionMessage = "Would you like something from dessert? We offer " + getItems(Dessert.values())
                + " Please enter desert name or leave it empty";
        String dessert = getOrderedItem(scanner, dessertSuggestionMessage, Menu.DESSERT);

        Dessert dessertValue = !dessert.isEmpty() ? Dessert.valueOf(dessert.toUpperCase()) : null;

        return new CustomerOrder.OrderBuilder()
                .setLunch(new Lunch(Dish.valueOf(dish.toUpperCase()), dessertValue))
                .build();
    }

    private static List<String> getItems(MenuItem[] values) {
        return Arrays.stream(values).map(i -> i.getName())
                .collect(Collectors.toList());
    }


    private static String getCustomerGeneralOrder(Scanner scanner, String warningMessage) {
        return getOrderedItem(scanner, warningMessage, null);
    }

    private static String getOrderedItem(Scanner scanner, String warningMessage, Menu menu) {
        String order;
        while (true) {
            System.out.println(warningMessage);
            order = scanner.nextLine();
            if (menu == null) {
                if (isValidOrder(order)) {
                    break;
                }
            } else {
                if (isValidItemFromMenu(order, menu)) {
                    break;
                }
            }
        }
        return order;
    }

    private static boolean isValidItemFromMenu(String order, Menu menu) {
        switch (menu) {
            case DISHES:
                return getItems(Dish.values()).contains(changeToCamelCase(order));
            case DESSERT:
                return getItems(Dessert.values()).contains(changeToCamelCase(order)) || order.isEmpty();
            case DRINK:
                return getItems(Drink.values()).contains(changeToCamelCase(order));
            case GARNISH:
                Pattern pattern = Pattern.compile("(?i)(\\blemon(\\b.*\\bice\\b)?)|\\bice\\b$");
                Matcher matcher = pattern.matcher(order);
                return matcher.matches() || order.isEmpty();
        }
        return false;
    }

    private static String changeToCamelCase(String order) {
        return !order.isEmpty() ? order.toLowerCase().replaceFirst(order.substring(0, 1), order.substring(0, 1).toUpperCase()) : order;
    }

    private static boolean isValidOrder(String order) {
        return order.equalsIgnoreCase(LUNCH) || order.equalsIgnoreCase(DRINK);
    }
}
