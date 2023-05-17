package com.multiLanguageDB.multilanguageapi.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Setter
public class ProductRequest {

    String title;

    String description;

    int quantity;

    String price;

    public Product toProduct () {
        return Product.builder()
                .title(title)
                .description(description)
                .quantity(quantity)
                .price(price)
                .build();
    }

    public Product toProduct (UUID id) {
        return Product.builder()
                .id(id)
                .title(title)
                .description(description)
                .quantity(quantity)
                .price(price)
                .build();
    }
}
