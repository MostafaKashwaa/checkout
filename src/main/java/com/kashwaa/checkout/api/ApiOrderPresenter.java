package com.kashwaa.checkout.api;

import com.kashwaa.checkout.OrderPresenter;
import com.kashwaa.checkout.domain.Order;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

public class ApiOrderPresenter implements OrderPresenter {
    private Order order = null;

    @Override
    public void setOrder(Order order) {
        this.order = order;
    }

    public Order getOrder() { return this.order; }

    public ResponseEntity<Object> getResponse() {
        if (order.isValid())
            return createResponse(200, "success", order.getSummary());

        return createResponse(
                422,
                "Unprocessable",
                String.join("\n", order.getErrors())
        );
    }

    private ResponseEntity<Object> createResponse(int code, String status, String message) {
        Map<String, Object> data = new HashMap<>();
        data.put("code", code);
        data.put("status", status);
        data.put("items", order.getItems());
        data.put("message", message);
        data.put("total", String.format("%.2f", order.getTotalAvailable()));
        return ResponseEntity.status(code).body(data);
    }
}
