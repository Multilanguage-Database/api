package com.multiLanguageDB.multilanguageapi.paymentMethod;

import com.multiLanguageDB.multilanguageapi.locale.Locale;
import com.multiLanguageDB.multilanguageapi.textContent.TextContent;
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

    public PaymentMethod toPaymentMethod(TextContent name, TextContent description) {
        return PaymentMethod.builder()
                .name(name)
                .description(description)
                .build();
    }

    public PaymentMethod toPaymentMethod(UUID id, TextContent name, TextContent description) {
        return PaymentMethod.builder()
                .id(id)
                .name(name)
                .description(description)
                .build();
    }

    public TextContent toTextContent(String text) {
        return TextContent.builder()
                .locale(locale)
                .text(text)
                .build();
    }
}
