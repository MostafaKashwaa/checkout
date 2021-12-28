package com.kashwaa.checkout.validators;

import com.kashwaa.checkout.domain.Basket;
import com.kashwaa.checkout.domain.BasketValidator;
import com.kashwaa.checkout.domain.Item;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class AvailabilityValidator implements BasketValidator {
    @Override
    public List<String> validate(Basket basket) {
        final List<String> messages = new ArrayList<>();
        for (Item item : basket.getItems()) {
            if (!item.isAvailable())
                messages.add(String.format("%s is currently not available\n", item.getName()));
        }
        return messages;
    }
}
