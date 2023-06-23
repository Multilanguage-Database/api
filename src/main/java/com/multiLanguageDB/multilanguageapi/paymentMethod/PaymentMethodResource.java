package com.multiLanguageDB.multilanguageapi.paymentMethod;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.multiLanguageDB.multilanguageapi.textContent.TextContent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@EqualsAndHashCode(callSuper = false)
@Builder
public class PaymentMethodResource {
    @JsonProperty(value = "_id")
    private final UUID id;

    TextContent name;

    TextContent description;
}
