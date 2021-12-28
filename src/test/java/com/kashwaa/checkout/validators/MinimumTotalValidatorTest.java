package com.kashwaa.checkout.validators;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.kashwaa.checkout.Helper.basketStub;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MinimumTotalValidatorTest {

    @Test
    void errorForEmptyBasket() {
        var errors = validateBasket(0);

        assertEquals(1, errors.size());
    }

    @Test
    void errorForBasketValueLessThan_100() {
        var errors = validateBasket(99);

        assertEquals(1, errors.size());
    }

    @Test
    void noErrorForBasketValue_100() {
        var errors = validateBasket(100);

        assertTrue(errors.isEmpty());
    }

    @Test
    void noErrorForBasketValueMoreThan_100() {
        var errors = validateBasket(200);

        assertTrue(errors.isEmpty());
    }

    private List<String> validateBasket(double totalAmount) {
        var validator = new MinimumTotalValidator();
        var basket = basketStub(totalAmount);
        return validator.validate(basket);
    }
}