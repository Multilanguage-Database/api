package com.multiLanguageDB.multilanguageapi.paymentMethodTranslation;

import com.multiLanguageDB.multilanguageapi.paymentMethod.PaymentMethod;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Setter
public class PaymentMethodTranslationRequest {

    PaymentMethod paymentMethod;

    String locale;

    String name;

    String description;

    public PaymentMethodTranslation toPaymentMethodTranslation() {
        return PaymentMethodTranslation.builder()
                .paymentMethod(paymentMethod)
                .locale(locale)
                .name(name)
                .description(description)
                .build();
    }

    public PaymentMethodTranslation toPaymentMethodTranslation(UUID id) {
        return PaymentMethodTranslation.builder()
                .id(id)
                .paymentMethod(paymentMethod)
                .locale(locale)
                .name(name)
                .description(description)
                .build();
    }
}
