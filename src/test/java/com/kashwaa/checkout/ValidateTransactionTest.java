package com.kashwaa.checkout;

import com.kashwaa.checkout.domain.Order;
import org.junit.jupiter.api.Test;
import static com.kashwaa.checkout.Helper.basketStub;
import static com.kashwaa.checkout.Helper.createOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidateTransactionTest {

    @Test
    void ExecuteSetsBasketGeneratedOrderToPresenter() {
        var presenter = new TestPresenter();
        var order = createOrder(5, 0);
        var basket = basketStub(order);
        var transaction = new ValidateTransaction(basket, presenter);

        transaction.execute();

        assertEquals(order, presenter.getOrder());
    }

    static class TestPresenter implements OrderPresenter{

        Order order = null;

        @Override
        public void setOrder(Order order) {
            this.order = order;
        }

        Order getOrder() {
            return this.order;
        }
    }
}