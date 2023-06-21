package com.multiLanguageDB.multilanguageapi.productTranslation;

import com.multiLanguageDB.multilanguageapi.product.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Setter
public class ProductTranslationRequest {
    Product product;

    String locale;

    String title;

    String description;

    public ProductTranslation toProductTranslation() {
        return ProductTranslation.builder()
                .product(product)
                .locale(locale)
                .title(title)
                .description(description)
                .build();
    }

    public ProductTranslation toProductTranslation(UUID id) {
        return ProductTranslation.builder()
                .id(id)
                .product(product)
                .locale(locale)
                .title(title)
                .description(description)
                .build();
    }
}
