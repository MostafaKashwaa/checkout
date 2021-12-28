package com.kashwaa.checkout.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.kashwaa.checkout.Helper.createOrder;
import static org.junit.jupiter.api.Assertions.*;

class ApiOrderPresenterTest {

    private ApiOrderPresenter presenter;

    @BeforeEach
    void setUp() {
        presenter = new ApiOrderPresenter();
    }

    @Test
    void invalidOrderCreatesResponseCode_422() {
        presenter.setOrder(createOrder(2, 2));

        assertEquals(422, presenter.getResponse().getStatusCode().value());
    }

    @Test
    void validOrderCreatesResponseCode_200() {
        presenter.setOrder(createOrder(4, 0));

        assertEquals(200, presenter.getResponse().getStatusCode().value());
    }
}