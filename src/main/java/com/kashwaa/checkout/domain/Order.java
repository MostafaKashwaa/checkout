package com.kashwaa.checkout.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Order {
    private List<Item> items;
    private boolean valid;
    private List<String> errors;

    public String getSummary() {
        StringBuilder summary = new StringBuilder();
        for (Item item : items) {
            summary.append(String.format(
                            "%s\t%.2f\t%s\n",
                            item.getName(),
                            item.getPrice(),
                            item.isAvailable() ? "available" : "unavailable"
                    )
            );
        }
        summary.append(String.format("Total: %.2f", getTotalAvailable()));
        return summary.toString();
    }

    public double getTotalAvailable() {
        return items.stream()
                .filter(Item::isAvailable)
                .mapToDouble(Item::getPrice)
                .sum();
    }
}
