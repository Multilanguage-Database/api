package com.multiLanguageDB.multilanguageapi.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Setter
public class ProductRequest {

    String locale;

    String title;

    String description;

    int quantity;

    String price;

    public Product toProduct () {
        return Product.builder()
                .quantity(quantity)
                .price(price)
                .build();
    }

    public Product toProduct (UUID id) {
        return Product.builder()
                .id(id)
                .quantity(quantity)
                .price(price)
                .build();
    }
}
