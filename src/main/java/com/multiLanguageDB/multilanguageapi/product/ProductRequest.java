package com.multiLanguageDB.multilanguageapi.product;

import com.multiLanguageDB.multilanguageapi.locale.Locale;
import com.multiLanguageDB.multilanguageapi.textContent.TextContent;
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

    public Product toProduct (TextContent title, TextContent description) {
        return Product.builder()
                .title(title)
                .description(description)
                .quantity(quantity)
                .price(price)
                .build();
    }

    public Product toProduct (UUID id, TextContent title, TextContent description) {
        return Product.builder()
                .id(id)
                .title(title)
                .description(description)
                .quantity(quantity)
                .price(price)
                .build();
    }

    public TextContent toTextContent(String text) {
        return TextContent.builder()
                .locale(locale)
                .text(text)
                .build();
    }
}
