package com.kashwaa.checkout;

import com.kashwaa.checkout.api.data.paymob.*;
import com.kashwaa.checkout.domain.InvalidOrderException;
import com.kashwaa.checkout.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Collectors;

@Service
public class CreatePaymentKeyService {
    private final RestTemplate restTemplate;
    private final String paymobApiKey;
    private final String authUrl;
    private final String regUrl;
    private final String payUrl;
    private final int cardIntegration;
    private final int iframeId;

    @Autowired
    public CreatePaymentKeyService(
            RestTemplate restTemplate,
            @Value("${paymob.api.key}") String paymobApiKey,
            @Value("${paymob.auth.url}") String authUrl,
            @Value("${paymob.reg.url}") String regUrl,
            @Value("${paymob.pay.url}") String payUrl,
            @Value("${paymob.integration.card}") int cardIntegration,
            @Value("${paymob.iframe.id}") int iframeId
    ) {
        this.restTemplate = restTemplate;
        this.paymobApiKey = paymobApiKey;
        this.authUrl = authUrl;
        this.regUrl = regUrl;
        this.payUrl = payUrl;
        this.cardIntegration = cardIntegration;
        this.iframeId = iframeId;
    }

    public PaymentFrame execute(Order order) {
        if (!order.isValid()) {
            var msg = order.getErrors().stream().collect(Collectors.joining("\n"));
            throw new InvalidOrderException(msg);
        }
        var token = authenticateRequest().getToken();
        var registration = registerOrder(token, order);
        return createPaymentKey(token, order, String.valueOf(registration.getId()));
    }

    public AuthenticationResponseDto authenticateRequest() {
        return this.restTemplate.postForObject(
                authUrl,
                new AuthenticationRequestDto(paymobApiKey),
                AuthenticationResponseDto.class);
    }

    public RegistrationResponseDto registerOrder(String token, Order order) {
        return restTemplate.postForObject(
                regUrl,
                prepareOrderDto(order, token),
                RegistrationResponseDto.class);
    }

    public PaymentFrame createPaymentKey(String token, Order order, String orderId) {
        var result = restTemplate.postForObject(
                payUrl,
                preparePayDto(order, token, orderId),
                PaymentResponseDto.class
        );
        var url = String.format("https://accept.paymob.com/api/acceptance/iframes/%d?payment_token=%s",
                iframeId, result.getToken());
        return new PaymentFrame(url);
    }

    private RegistrationRequestDto prepareOrderDto(Order order, String token) {
        return new RegistrationRequestDto(
                token,
                "false",
                String.format("%.0f", order.getTotalAvailable() * 100),
                "EGP",
                ItemDto.fromDomain(order.getItems())
        );
    }

    private PaymentRequestDto preparePayDto(Order order, String token, String orderId) {
        return new PaymentRequestDto(
                token,
                String.format("%.0f", order.getTotalAvailable() * 100),
                3600,
                orderId,
                new BillingData("Ali", "Hamed", "Ali@Hamed.com", "01001001010"),
                "EGP",
                cardIntegration,
                "false"
        );
    }
}
