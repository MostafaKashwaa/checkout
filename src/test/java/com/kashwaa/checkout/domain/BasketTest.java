package com.kashwaa.checkout.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.kashwaa.checkout.Helper.basketFromPrices;
import static com.kashwaa.checkout.Helper.basketWithValidators;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BasketTest {

    @Test
    void getItemsReturnCorrectListOfItems() {
        var basket = basketFromPrices(1.0, 10.0, 20.0);

        assertEquals(3, basket.getItems().size());
        assertEquals(1.0, basket.getItems().get(0).getPrice());
        assertEquals(10.0, basket.getItems().get(1).getPrice());
        assertEquals(20.0, basket.getItems().get(2).getPrice());
    }

    @Test
    void getTotalReturnsZeroForEmptyBasket() {
        var basket = basketFromPrices();

        assertEquals(0, basket.getTotal());
    }

    @Test
    void getTotalReturnsItemPriceForOneItem() {
        var basket = basketFromPrices(90.0);

        assertEquals(90, basket.getTotal());
    }

    @Test
    void getTotalReturnsSumOfItemsPrices() {
        var basket = basketFromPrices(10.0, 20.0, 100.0, 20.0);

        assertEquals(150.0, basket.getTotal());
    }

    @Test
    void checkoutWithNoValidatorsReturnOrderWithNoErrors() {
        var basket = basketWithValidators(0, 0);

        assertTrue(basket.checkout().getErrors().isEmpty());
    }

    @Test
    void checkoutWithValidValidatorsReturnOrderWithNoErrors() {
        var basket = basketWithValidators(3, 0);

        assertTrue(basket.checkout().getErrors().isEmpty());
    }

    @Test
    void checkoutWithOneInvalidValidatorReturnOrderWithOneError() {
        var basket = basketWithValidators(0, 1);

        assertEquals(1, basket.checkout().getErrors().size());
    }

    @Test
    void checkoutWithInvalidValidatorsReturnCorrectNumOfErrors() {
        var basket = basketWithValidators(1, 3);

        assertEquals(3, basket.checkout().getErrors().size());

        basket.addValidator(b -> List.of("error1", "error2"));

        assertEquals(5, basket.checkout().getErrors().size());
    }
}