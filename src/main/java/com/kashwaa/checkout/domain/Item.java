package com.kashwaa.checkout.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {
    private String name;
    private double price;
    private boolean available;
}
