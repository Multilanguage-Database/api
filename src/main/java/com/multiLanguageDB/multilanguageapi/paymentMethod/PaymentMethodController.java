package com.multiLanguageDB.multilanguageapi.paymentMethod;

import com.multiLanguageDB.multilanguageapi.paymentMethodTranslation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/payment")
@RequiredArgsConstructor
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    private final PaymentMethodTranslationService paymentMethodTranslationService;

    private final PaymentMethodResourceAssembler paymentMethodResourceAssembler;

    private final PaymentMethodTranslationResourceAssembler paymentMethodTranslationResourceAssembler;

    @PostMapping
    public ResponseEntity<PaymentMethodTranslationResource> createPaymentMethod(@RequestBody PaymentMethodRequest paymentMethodRequest) {
        PaymentMethod paymentMethod = paymentMethodRequest.toPaymentMethod();
        PaymentMethodTranslation paymentMethodTranslation = paymentMethodRequest.toPaymentMethodTranslation();
        paymentMethodTranslation.setPaymentMethod(paymentMethod);
        paymentMethodService.create(paymentMethod);
        paymentMethodTranslationService.create(paymentMethodTranslation);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .build(paymentMethod.getId());

        return ResponseEntity.created(location).body(paymentMethodTranslationResourceAssembler.toResource(paymentMethodTranslation));
    }

    @GetMapping
    public ResponseEntity<List<PaymentMethodResource>> getPaymentMethods() {
        return ResponseEntity.ok(paymentMethodResourceAssembler.toListResource(paymentMethodService.findAll()));
    }

    @GetMapping(path = "/locale/{locale}")
    public ResponseEntity<List<PaymentMethodTranslationResource>> getPaymentMethodsByLocale(@PathVariable("locale") String locale) {
        Optional<List<PaymentMethodTranslation>> paymentMethodTranslations = paymentMethodTranslationService.findAllByLocale(locale);

        return ResponseEntity.ok(paymentMethodTranslationResourceAssembler.toListResource(paymentMethodTranslations.get()));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PaymentMethodResource> getPaymentMethod(@PathVariable("id") UUID id) {
        Optional<PaymentMethod> paymentMethod = paymentMethodService.findByIdOptional(id);

        return paymentMethodService.findByIdOptional(id)
                .map(paymentMethodResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/one/{id}/{locale}")
    public ResponseEntity<PaymentMethodTranslationResource> getPaymentMethod(@PathVariable("id") UUID id, @PathVariable("locale") String locale) {
        Optional<PaymentMethodTranslation> paymentMethodTranslation = paymentMethodTranslationService.findByIdAndLocale(id, locale);

        return paymentMethodTranslationService.findByIdAndLocale(id, locale)
                .map(paymentMethodTranslationResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/{id}/{locale}")
    public ResponseEntity<PaymentMethodResource> updatePaymentMethod(
            @PathVariable("id") Optional<PaymentMethod> paymentMethod,
            @RequestBody PaymentMethodRequest paymentMethodRequest
    ) {
        return paymentMethod
                .map(current -> current = paymentMethodRequest.toPaymentMethod(paymentMethod.get().getId()))
                .map(paymentMethodService::update)
                .map(paymentMethodResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletePaymentMethod(@PathVariable("id") Optional<PaymentMethod> paymentMethod) {
        if(paymentMethod.isPresent()) {
            paymentMethodService.delete(paymentMethod.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @AllArgsConstructor
    @Getter
    @Setter
    private class PaymentMethodTranslationResponse {
        private PaymentMethod paymentMethod;

        private PaymentMethodTranslation paymentMethodTranslation;
    }
}
