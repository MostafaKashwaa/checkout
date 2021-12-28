package com.kashwaa.checkout.validators;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.kashwaa.checkout.Helper.basketStub;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FraudValidatorTest {

    @Test
    void emptyErrorsForEmptyOrder() {
        var errors = validateBasket(0);

        assertTrue(errors.isEmpty());
    }

    @Test
    void emptyErrorsForTotalOf_1500() {
        var errors = validateBasket(1500);

        assertTrue(errors.isEmpty());
    }

    @Test
    void noErrorsForTotalLessThan_1500() {
        var errors = validateBasket(800);

        assertTrue(errors.isEmpty());
    }

    @Test
    void oneErrorForTotalMoreThan_1500() {
        var errors = validateBasket(1501);

        assertEquals(1, errors.size());
    }

    private List<String> validateBasket(double totalAmount) {
        var validator = new FraudValidator();
        var basket = basketStub(totalAmount);
        return validator.validate(basket);
    }
}