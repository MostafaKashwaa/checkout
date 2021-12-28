package com.kashwaa.checkout;

import com.kashwaa.checkout.domain.Basket;
import com.kashwaa.checkout.domain.BasketValidator;
import com.kashwaa.checkout.domain.Item;
import com.kashwaa.checkout.domain.Order;

import java.util.ArrayList;
import java.util.List;

public class Helper {
    public static Order createOrder(int availableCount, int unavailableCount) {
        var items = new ArrayList<Item>();
        items.addAll(createItems(true, 0, availableCount));
        items.addAll(createItems(false, availableCount, availableCount + unavailableCount));
        var valid = unavailableCount == 0;

        return new Order(items, valid, List.of("error"));
    }

    public static List<Item> createItems(boolean available, int startIndex, int availableCount) {
        var items = new ArrayList<Item>();
        for (int i = startIndex; i < availableCount; i++) {
            items.add(new Item("item " + i, 20, available));
        }
        return items;
    }

    public static Basket basketFromPrices(double... prices) {
        var items = new ArrayList<Item>();
        for (double price : prices) {
            items.add(new Item("undefined", price, true));
        }
        return new Basket(items);
    }

    public static Basket basketWithValidators(int numValid, int numInvalid) {
        var basket = basketFromPrices(100);
        var validators = new ArrayList<BasketValidator>();
        for (int i = 0; i < numValid; i++) {
            validators.add(b -> List.of());
        }
        for (int i = 0; i < numInvalid; i++) {
            validators.add(b -> List.of("error"));
        }
        basket.addAllValidators(validators);
        return basket;
    }

    public static Basket basketStub(double totalAmount) {
        return new Basket(List.of()) {
            @Override
            public double getTotal() {
                return totalAmount;
            }
        };
    }

    public static Basket basketStub(int availableCount, int unavailableCount) {
        var items = new ArrayList<Item>();

        items.addAll(createItems(true, 0, availableCount));
        items.addAll(createItems(false, availableCount, availableCount + unavailableCount));

        return new Basket(List.of()) {
            @Override
            public List<Item> getItems() {
                return items;
            }
        };
    }

    public static Basket basketStub(Order order) {
        return new Basket(List.of()) {
            @Override
            public Order checkout() {
                return order;
            }
        };
    }
}
