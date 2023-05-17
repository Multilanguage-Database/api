package com.multiLanguageDB.multilanguageapi.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@EqualsAndHashCode(callSuper = false)
@Builder
public class ProductResource {
    @JsonProperty(value = "_id")
    private final UUID id;

    String title;

    String description;

    int quantity;

    String price;
}
