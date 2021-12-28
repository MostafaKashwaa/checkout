package com.kashwaa.checkout.api;

import com.kashwaa.checkout.CheckoutTransaction;
import com.kashwaa.checkout.api.data.BasketDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("unused")
@RestController
public class OrderController {
    ApiOrderPresenter presenter = new ApiOrderPresenter();

    @PostMapping("/checkout")
    public ResponseEntity<Object> checkout(@RequestBody BasketDto basket) {
        new CheckoutTransaction(basket.toDomain(), presenter).execute();
        return presenter.getResponse();
    }
}

