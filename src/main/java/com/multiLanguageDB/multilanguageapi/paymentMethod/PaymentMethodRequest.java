package com.multiLanguageDB.multilanguageapi.paymentMethod;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Setter
public class PaymentMethodRequest {
    String name;

    String description;

    public PaymentMethod toPaymentMethod() {
        return PaymentMethod.builder()
                .build();
    }

    public PaymentMethod toPaymentMethod(UUID id) {
        return PaymentMethod.builder()
                .id(id)
                .build();
    }
}
