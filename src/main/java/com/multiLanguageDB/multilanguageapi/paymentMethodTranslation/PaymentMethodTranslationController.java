package com.multiLanguageDB.multilanguageapi.paymentMethodTranslation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "paymentLocale")
@RequiredArgsConstructor
public class PaymentMethodTranslationController {

    private final PaymentMethodTranslationService paymentMethodTranslationService;

    private final PaymentMethodTranslationResourceAssembler paymentMethodTranslationResourceAssembler;

    @PostMapping
    public ResponseEntity<PaymentMethodTranslationResource> createPaymentMethodTranslation(@RequestBody PaymentMethodTranslationRequest paymentMethodTranslationRequest) {
        PaymentMethodTranslation paymentMethodTranslation = paymentMethodTranslationService.create(paymentMethodTranslationRequest.toPaymentMethodTranslation());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .build(paymentMethodTranslation.getId());

        return ResponseEntity.created(location).body(paymentMethodTranslationResourceAssembler.toResource(paymentMethodTranslation));
    }

    @GetMapping
    public ResponseEntity<List<PaymentMethodTranslationResource>> getPaymentMethodTranslations() {
        return ResponseEntity.ok(paymentMethodTranslationResourceAssembler.toListResource(paymentMethodTranslationService.findAll()));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PaymentMethodTranslationResource> updatePaymentMethodTranslation(
            @PathVariable("id") Optional<PaymentMethodTranslation> paymentMethodTranslation,
            @RequestBody PaymentMethodTranslationRequest paymentMethodTranslationRequest
    ) {
        return paymentMethodTranslation
                .map(current -> current = paymentMethodTranslationRequest.toPaymentMethodTranslation(paymentMethodTranslation.get().getId()))
                .map(paymentMethodTranslationService::update)
                .map(paymentMethodTranslationResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletePaymentMethodTranslation(@PathVariable("id") Optional<PaymentMethodTranslation> paymentMethodTranslation) {
        if(paymentMethodTranslation.isPresent()) {
            paymentMethodTranslationService.delete(paymentMethodTranslation.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
