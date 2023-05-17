package com.multiLanguageDB.multilanguageapi.paymentMethod;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Builder
public class PaymentMethodResourceAssembler {

    public PaymentMethodResource toResource(PaymentMethod paymentMethod) {
        return PaymentMethodResource.builder()
                .id(paymentMethod.getId())
                .name(paymentMethod.getName())
                .description(paymentMethod.getDescription())
                .build();
    }

    public List<PaymentMethodResource> toListResource(List<PaymentMethod> paymentMethods) {
        return paymentMethods.stream().map(this::toResource).collect(Collectors.toList());
    }
}
