package com.multiLanguageDB.multilanguageapi.paymentMethod;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Builder
public class PaymentMethodResource {
    @JsonProperty(value = "_id")
    private final UUID id;

    String locale;

    String name;

    String description;
}
