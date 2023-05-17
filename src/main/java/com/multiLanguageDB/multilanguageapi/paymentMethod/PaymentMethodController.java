package com.multiLanguageDB.multilanguageapi.paymentMethod;

import lombok.RequiredArgsConstructor;
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

    private final PaymentMethodResourceAssembler paymentMethodResourceAssembler;

    @PostMapping
    public ResponseEntity<PaymentMethodResource> createPaymentMethod(@RequestBody PaymentMethodRequest paymentMethodRequest) {
        PaymentMethod paymentMethod = paymentMethodService.create(paymentMethodRequest.toPaymentMethod());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .build(paymentMethod.getId());

        return ResponseEntity.created(location).body(paymentMethodResourceAssembler.toResource(paymentMethod));
    }

    @GetMapping
    public ResponseEntity<List<PaymentMethodResource>> getPaymentMethods() {
        return ResponseEntity.ok(paymentMethodResourceAssembler.toListResource(paymentMethodService.findAll()));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PaymentMethodResource> getPaymentMethod(@PathVariable("id") UUID id) {
        Optional<PaymentMethod> paymentMethod = paymentMethodService.findByIdOptional(id);

        return paymentMethodService.findByIdOptional(id)
                .map(paymentMethodResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/{id}")
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
}
