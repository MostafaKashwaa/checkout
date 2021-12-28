package com.kashwaa.checkout;

import com.kashwaa.checkout.domain.Basket;
import com.kashwaa.checkout.domain.Order;
import com.kashwaa.checkout.validators.AvailabilityValidator;
import com.kashwaa.checkout.validators.FraudValidator;
import com.kashwaa.checkout.validators.MinimumTotalValidator;

public class ValidateTransaction implements Interactor {
    final private Basket basket;
    final private OrderPresenter orderPresenter;

    public ValidateTransaction(Basket basket, OrderPresenter orderPresenter) {
        this.basket = basket;
        this.basket.addValidator(new AvailabilityValidator());
        this.basket.addValidator(new MinimumTotalValidator());
        this.basket.addValidator(new FraudValidator());
        this.orderPresenter = orderPresenter;
    }

    @Override
    public void execute() {
        Order order = basket.checkout();
        orderPresenter.setOrder(order);
    }
}
