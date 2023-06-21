package com.multiLanguageDB.multilanguageapi.paymentMethodTranslation;

import com.multiLanguageDB.multilanguageapi.paymentMethod.PaymentMethod;
import com.multiLanguageDB.multilanguageapi.paymentMethod.PaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/paymentLocale")
@RequiredArgsConstructor
public class PaymentMethodTranslationController {

    private final PaymentMethodTranslationService paymentMethodTranslationService;

    private final PaymentMethodService paymentMethodService;

    private final PaymentMethodTranslationResourceAssembler paymentMethodTranslationResourceAssembler;

    @PostMapping
    public ResponseEntity<PaymentMethodTranslationResource> createPaymentMethodTranslation(@RequestBody PaymentMethodTranslationRequest paymentMethodTranslationRequest) {
        PaymentMethodTranslation paymentMethodTranslation = paymentMethodTranslationService.create(paymentMethodTranslationRequest.toPaymentMethodTranslation());

        URI localtion = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(("/{id}"))
                .build(paymentMethodTranslation.getId());

        return ResponseEntity.created(localtion).body(paymentMethodTranslationResourceAssembler.toResource(paymentMethodTranslation));
    }

    @GetMapping
    public ResponseEntity<List<PaymentMethodTranslationResource>> getPaymentMethodTranslations() {
        return ResponseEntity.ok(paymentMethodTranslationResourceAssembler.toListResource(paymentMethodTranslationService.findAll()));
    }

    @GetMapping(path = "/{locale}/{id}")
    public ResponseEntity<PaymentMethodTranslationResource> getPaymentMehtodTranslation(@PathVariable("locale") String locale, @PathVariable("id") UUID id) {
        Optional<PaymentMethodTranslation> paymentMethodTranslation = paymentMethodTranslationService.findIdOptional(id, locale);

        if(!paymentMethodTranslation.isPresent()) {
            Optional<PaymentMethod> paymentMethod = paymentMethodService.findByIdOptional(id);

            PaymentMethodTranslation paymentMethodTranslationDefault = new PaymentMethodTranslation(null, "default", paymentMethod.get().getName(), paymentMethod.get().getDescription(), paymentMethod.get());
            return ResponseEntity.ok(paymentMethodTranslationResourceAssembler.toResource(paymentMethodTranslationDefault));
        }

        return paymentMethodTranslation
                .map(paymentMethodTranslationResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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
