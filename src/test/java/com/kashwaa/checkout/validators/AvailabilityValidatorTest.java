package com.kashwaa.checkout.validators;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.kashwaa.checkout.Helper.basketStub;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AvailabilityValidatorTest {

    @Test
    void noErrorsForEmptyBasket() {
        var errors = validateBasket(0, 0);

        assertTrue(errors.isEmpty());
    }

    @Test
    void noErrorsForAllAvailableItems() {
        var errors = validateBasket(3, 0);

        assertTrue(errors.isEmpty());
    }

    @Test
    void oneErrorForOneUnavailableItem() {
        var errors = validateBasket(2, 1);

        assertEquals(1, errors.size());
    }

    @Test
    void basketLengthErrorsForAllUnavailableItems() {
        var errors = validateBasket(0, 5);

        assertEquals(5, errors.size());
    }

    @Test
    void errorsContainUnavailableItemNames() {
        var errors = validateBasket(3, 3);

        assertTrue(errors.get(0).contains("item 3"));
        assertTrue(errors.get(1).contains("item 4"));
        assertTrue(errors.get(2).contains("item 5"));
    }

    private List<String> validateBasket(int availableCount, int unavailableCount) {
        var validator = new AvailabilityValidator();
        var basket = basketStub(availableCount, unavailableCount);
        return validator.validate(basket);
    }
}