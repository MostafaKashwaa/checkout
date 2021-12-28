package com.kashwaa.checkout.api.data.paymob;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RegistrationResponseDto {
    int id;
    @JsonProperty("created_at") String createdAt;
    @JsonProperty("deliver_needed") String deliveryNeeded;
    MerchantDto merchant;
    String collector;
    @JsonProperty("amount_cents") int amountCents;
    String currency;
    @JsonProperty("is_payment_locked") String isPaymentLocked;
    @JsonProperty("merchant_order_id") String merchantOrderId;
    @JsonProperty("wallet_notification") String walletNotification;
    @JsonProperty("paid_amount_cents") int paidAmountCents;
    List<ItemDto> items;
}

@Data
@AllArgsConstructor
class MerchantDto {
    int id;
    @JsonProperty("created_at") String createdAt;
    List<String> phones;
    @JsonProperty("company_emails") List<String> companyEmails;
    @JsonProperty("company_name") String companyName;
    String state;
    String country;
    String city;
    @JsonProperty("postal_code") String postalCode;
    String street;
}
