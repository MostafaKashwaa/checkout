package com.kashwaa.checkout.validators;

import com.kashwaa.checkout.domain.Basket;
import com.kashwaa.checkout.domain.BasketValidator;

import java.util.ArrayList;
import java.util.List;

public class MinimumTotalValidator implements BasketValidator {
    @Override
    public List<String> validate(Basket basket) {
        List<String> messages = new ArrayList<>();
        if (basket.getTotal() < 100) {
            messages.add("Total basket money is less than 100\n");
        }
        return messages;
    }
}
