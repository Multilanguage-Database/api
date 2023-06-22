package com.multiLanguageDB.multilanguageapi.productTranslation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "productLocale")
@RequiredArgsConstructor
public class ProductTranslationController {

    private final ProductTranslationService productTranslationService;

    private final ProductTranslationResourceAssembler productTranslationResourceAssembler;

    @PostMapping
    public ResponseEntity<ProductTranslationResource> createProductTranslation(@RequestBody ProductTranslationRequest productTranslationRequest) {
        ProductTranslation productTranslation = productTranslationService.create(productTranslationRequest.toProductTranslation());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .build(productTranslation.getId());

        return ResponseEntity.created(location).body(productTranslationResourceAssembler.toResource(productTranslation));
    }

    @GetMapping
    public ResponseEntity<List<ProductTranslationResource>> getProductTranslations() {
        return ResponseEntity.ok(productTranslationResourceAssembler.toListResource(productTranslationService.findAll()));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProductTranslationResource> updateProductTranslation(
        @PathVariable("id")Optional<ProductTranslation> productTranslation,
        @RequestBody ProductTranslationRequest productTranslationRequest
    ) {
        return productTranslation
                .map(current -> current = productTranslationRequest.toProductTranslation(productTranslation.get().getId()))
                .map(productTranslationService::update)
                .map(productTranslationResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteProductTranslation(@PathVariable("id") Optional<ProductTranslation> productTranslation) {
        if (productTranslation.isPresent()) {
            productTranslationService.delete(productTranslation.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
