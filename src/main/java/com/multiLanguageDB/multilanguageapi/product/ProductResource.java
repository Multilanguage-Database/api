package com.multiLanguageDB.multilanguageapi.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Builder
public class ProductResource {
    @JsonProperty(value = "_id")
    private final UUID id;

    String locale;

    String title;

    String description;

    int quantity;

    String price;
}
