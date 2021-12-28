package com.kashwaa.checkout.api.data.paymob;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentResponse {
   String frameUrl;
   int orderId;
}
