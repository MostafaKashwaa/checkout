package com.kashwaa.checkout.api.data.paymob;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kashwaa.checkout.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    String name;
    @JsonProperty("amount_cents")
    String amountCents;
    String description;
    String quantity;

    public static List<ItemDto> fromDomain(List<Item> items) {
        return items.stream().map(ItemDto::fromDomain).collect(Collectors.toList());
    }

    public static ItemDto fromDomain(Item item) {
        return new ItemDto(item.getName(),
                String.format("%.0f", item.getPrice() * 100),
                "",
                "1"
        );
    }
}
