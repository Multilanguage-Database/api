package com.multiLanguageDB.multilanguageapi.cartProduct;

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
public class CartProductResource {

    @JsonProperty(value = "_id")
    UUID id;

    UUID cart;

    UUID product;

    String product_title;

    int quantity;
}
