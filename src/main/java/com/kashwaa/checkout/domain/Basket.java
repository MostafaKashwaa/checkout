package com.kashwaa.checkout.domain;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private final List<Item> items;
    private final List<BasketValidator> validators;

    public Basket(List<Item> items) {
        this.items = items;
        this.validators = new ArrayList<>();
    }

    public void addValidator(BasketValidator validator) {
        this.validators.add(validator);
    }

    public void addAllValidators(List<BasketValidator> validators) {
        this.validators.addAll(validators);
    }

    public List<Item> getItems() {
        return items;
    }

    public double getTotal() {
        return items.stream().mapToDouble(Item::getPrice).sum();
    }

    public Order checkout() {
        var errors = this.validate();
        return new Order(items, errors.isEmpty(), errors);
    }

    private List<String> validate() {
        List<String> errors = new ArrayList<>();
        for (BasketValidator validator : validators) {
            errors.addAll(validator.validate(this));
        }
        return errors;
    }
}
