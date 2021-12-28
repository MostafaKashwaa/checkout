package com.kashwaa.checkout.api.data.paymob;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class PaymentRequestDto {
    @JsonProperty("auth_token")
    String authToken;
    @JsonProperty("amount_cents")
    String amountCents;
    @JsonProperty("expiration")
    int expiration;
    @JsonProperty("order_id")
    String orderId;
    @JsonProperty("billing_data")
    BillingData billingData;
    @JsonProperty("currency")
    String currency;
    @JsonProperty("integration_id")
    int integration_id;
    @JsonProperty("lock_order_when_paid")
    String lockOrderWhenPaid;
}

