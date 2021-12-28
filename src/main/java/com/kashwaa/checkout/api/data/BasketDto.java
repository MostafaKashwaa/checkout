package com.kashwaa.checkout.api.data;

import com.kashwaa.checkout.domain.Basket;
import com.kashwaa.checkout.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketDto {
    UserDto user;
    List<Item> items = List.of();

    public Basket toDomain() {
        return new Basket(items);
    }
}
