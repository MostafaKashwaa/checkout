package com.kashwaa.checkout.validators;

import com.kashwaa.checkout.domain.Basket;
import com.kashwaa.checkout.domain.BasketValidator;

import java.util.ArrayList;
import java.util.List;

public class FraudValidator implements BasketValidator {
    @Override
    public List<String> validate(Basket basket) {
        List<String> messages = new ArrayList<>();
        if (basket.getTotal() > 1500) {
            messages.add("Seems like fraud. total money is more than 1500");
        }
        return messages;
    }
}
