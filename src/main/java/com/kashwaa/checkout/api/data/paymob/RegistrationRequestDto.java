package com.kashwaa.checkout.api.data.paymob;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RegistrationRequestDto {
    @JsonProperty("auth_token") String authToken;
    @JsonProperty("delivery_needed") String deliveryNeeded;
    @JsonProperty("amount_cents") String amountCents;
    String currency;
    List<ItemDto> items;
}

