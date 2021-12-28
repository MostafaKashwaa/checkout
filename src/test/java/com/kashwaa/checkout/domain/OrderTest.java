package com.kashwaa.checkout.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.kashwaa.checkout.Helper.createOrder;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void getSummaryContainsAllItems() {
        var order = createOrder(1, 2);

        assertTrue(order.getSummary().contains("item 0"));
        assertTrue(order.getSummary().contains("item 1"));
        assertTrue(order.getSummary().contains("item 2"));
    }

    @Test
    void getSummaryContainsTotal() {
        var order = createOrder(2, 2);

        assertTrue(order.getSummary().contains("Total: 40.00"));
    }

    @Test
    void getTotalAvailableReturnsTotalIfAllItemsAvailable() {
        var order = createOrder(5, 0);

        assertEquals(100, order.getTotalAvailable());
    }

    @Test
    void getTotalAvailableExcludesNonAvailableItems() {
        Order order = createOrder(3, 3);

        assertEquals(60, order.getTotalAvailable());
    }

    @Test
    void isValidReturnsPassedValue() {
        var validOrder = new Order(List.of(), true, List.of());
        var invalidOrder = new Order(List.of(), false, List.of());

        assertTrue(validOrder.isValid());
        assertFalse(invalidOrder.isValid());
    }

    @Test
    void getItemsReturnsAllItems() {
        var order = createOrder(2, 2);

        assertEquals(4, order.getItems().size());
    }
}