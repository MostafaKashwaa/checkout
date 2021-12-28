package com.kashwaa.checkout.api;

import com.kashwaa.checkout.ValidateTransaction;
import com.kashwaa.checkout.CreatePaymentKeyService;
import com.kashwaa.checkout.api.data.BasketDto;
import com.kashwaa.checkout.api.data.paymob.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("unused")
@RestController
public class OrderController {
    ApiOrderPresenter presenter = new ApiOrderPresenter();

    @Autowired
    CreatePaymentKeyService paymentService;

    @PostMapping("/validate")
    public ResponseEntity<Object> validate(@RequestBody BasketDto basket) {
        new ValidateTransaction(basket.toDomain(), presenter).execute();
        return presenter.getResponse();
    }

    @PostMapping("/checkout")
    public PaymentFrame checkout(@RequestBody BasketDto basket) {
        new ValidateTransaction(basket.toDomain(), presenter).execute();
        return paymentService.execute(presenter.getOrder());
    }
}

