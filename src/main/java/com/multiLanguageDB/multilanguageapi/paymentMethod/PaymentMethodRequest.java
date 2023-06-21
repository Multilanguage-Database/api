package com.multiLanguageDB.multilanguageapi.paymentMethod;

import com.multiLanguageDB.multilanguageapi.locale.Locale;
import com.multiLanguageDB.multilanguageapi.paymentMethodTranslation.PaymentMethodTranslation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Setter
public class PaymentMethodRequest {

    Locale locale;

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

    public PaymentMethodTranslation toPaymentMethodTranslation() {
        return PaymentMethodTranslation.builder()
                .locale(locale)
                .name(name)
                .description(description)
                .build();
    }
}
