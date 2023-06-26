package com.multiLanguageDB.multilanguageapi.paymentMethodTranslation;

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
public class PaymentMethodTranslationResource {
    @JsonProperty(value = "_id")
    private final UUID id;

    UUID paymentMethod;

    String locale;

    String name;

    String description;
}