package com.kashwaa.checkout.domain;

import java.util.List;

public interface BasketValidator {
    List<String> validate(Basket basket);
}
