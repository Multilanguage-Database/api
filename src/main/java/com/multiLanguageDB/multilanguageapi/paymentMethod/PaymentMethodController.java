package com.multiLanguageDB.multilanguageapi.paymentMethod;

import com.multiLanguageDB.multilanguageapi.textContent.TextContent;
import com.multiLanguageDB.multilanguageapi.textContent.TextContentService;
import com.multiLanguageDB.multilanguageapi.translation.Translation;
import com.multiLanguageDB.multilanguageapi.translation.TranslationService;
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

    private final TextContentService textContentService;

    private final TranslationService translationService;

    private final PaymentMethodResourceAssembler paymentMethodResourceAssembler;

    @PostMapping
    public ResponseEntity<PaymentMethodResource> createPaymentMethod(@RequestBody PaymentMethodRequest paymentMethodRequest) {
        TextContent name = textContentService.create(paymentMethodRequest.toTextContent(paymentMethodRequest.getName()));
        TextContent description = textContentService.create(paymentMethodRequest.toTextContent(paymentMethodRequest.getDescription()));
        PaymentMethod paymentMethod = paymentMethodService.create(paymentMethodRequest.toPaymentMethod(name, description));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .build(paymentMethod.getId());

        return ResponseEntity.created(location).body(paymentMethodResourceAssembler.toResource(paymentMethod));
    }

    @PostMapping(path = "/{id}")
    public ResponseEntity<PaymentMethodResource> createPaymentMethodTranslation(@RequestBody PaymentMethodRequest paymentMethodRequest, @PathVariable("id") UUID id) {
        Optional<PaymentMethod> paymentMethod = paymentMethodService.findByIdOptional(id);

        Translation name = new Translation();

        name.setLocale(paymentMethodRequest.getLocale());
        name.setTranslation(paymentMethodRequest.getName());
        name.setTextContent(paymentMethod.get().getName());

        translationService.create(name);

        Translation description = new Translation();

        description.setLocale(paymentMethodRequest.getLocale());
        description.setTranslation(paymentMethodRequest.getDescription());
        description.setTextContent(paymentMethod.get().getDescription());

        translationService.create(description);

        PaymentMethodResource paymentMethodResource = paymentMethodResourceAssembler.toResource(paymentMethod.get());
        paymentMethodResource.setLocale(paymentMethodRequest.getLocale().getId());
        paymentMethodResource.setName(name.getTranslation());
        paymentMethodResource.setDescription(description.getTranslation());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .build(paymentMethod.get().getId());

        return ResponseEntity.created(location).body(paymentMethodResource);
    }

    @GetMapping(path = "/{locale}/{id}")
    public ResponseEntity<PaymentMethodResource> getPaymentMethodTranslation(@PathVariable("locale") String locale, @PathVariable("id") UUID id) {
        Optional<PaymentMethod> paymentMethod = paymentMethodService.findByIdOptional(id);

        PaymentMethodResource paymentMethodResource = paymentMethodResourceAssembler.toResource(paymentMethod.get());
        Translation name = translationService.findTranslationByLocaleAndTextContentId(locale, paymentMethod.get().getName().getId()).get();
        Translation description = translationService.findTranslationByLocaleAndTextContentId(locale, paymentMethod.get().getDescription().getId()).get();

        paymentMethodResource.setName(name.getTranslation());
        paymentMethodResource.setDescription(description.getTranslation());
        paymentMethodResource.setLocale(locale);

        return ResponseEntity.ok().body(paymentMethodResource);
    }

    /*
    * These are absolute Nopes. They are possible but ugly. Really a lot of joins and checks. Too much, not good
    *


    @GetMapping(path = "/{locale}")
    public ResponseEntity<List<PaymentMethodResource>> getPaymentMethodsByLocale(@PathVariable("locale") String locale) {
        List<PaymentMethod> paymentMethods = paymentMethodService.findAll();

        //get all Payment Methods where Name.getLocale = locale
        //find translations that are names that have the locale
        return ResponseEntity.ok(paymentMethodResourceAssembler.toListResource(paymentMethods));
    }

    @GetMapping
    public ResponseEntity<List<PaymentMethodResource>> getPaymentMethodsAndTranslations() {

        return ResponseEntity.ok(paymentMethodResourceAssembler.toListResource(paymentMethods));
    }
     */

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
                .map(current -> current = paymentMethodRequest.toPaymentMethod(paymentMethod.get().getId(), paymentMethod.get().getName(), paymentMethod.get().getDescription()))
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
