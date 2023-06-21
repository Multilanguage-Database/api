package com.multiLanguageDB.multilanguageapi.paymentMethodTranslation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Builder
public class PaymentMethodTranslationResourceAssembler {

    public PaymentMethodTranslationResource toResource(PaymentMethodTranslation paymentMethodTranslation) {
        return PaymentMethodTranslationResource.builder()
                .id(paymentMethodTranslation.getId())
                .locale(paymentMethodTranslation.getLocale().getId())
                .name(paymentMethodTranslation.getName())
                .description(paymentMethodTranslation.getDescription())
                .build();
    }

    public List<PaymentMethodTranslationResource> toListResource(List<PaymentMethodTranslation> paymentMethodTranslations) {
        return paymentMethodTranslations.stream().map(this::toResource).collect(Collectors.toList());
    }
}