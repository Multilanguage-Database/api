package com.multiLanguageDB.multilanguageapi.product;

import com.multiLanguageDB.multilanguageapi.locale.Locale;
import com.multiLanguageDB.multilanguageapi.productTranslation.ProductTranslation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Setter
public class ProductRequest {

    Locale locale;

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

    public ProductTranslation toProductTranslation() {
        return ProductTranslation.builder()
                .locale(locale)
                .title(title)
                .description(description)
                .build();
    }
}
