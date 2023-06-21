package com.multiLanguageDB.multilanguageapi.productTranslation;

import com.multiLanguageDB.multilanguageapi.paymentMethodTranslation.PaymentMethodTranslationRepository;
import com.multiLanguageDB.multilanguageapi.product.Product;
import com.multiLanguageDB.multilanguageapi.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/productLocale")
@RequiredArgsConstructor
public class ProductTranslationController {

    private final ProductTranslationService productTranslationService;

    private final ProductService productService;

    private final ProductTranslationResourceAssembler productTranslationResourceAssembler;
    private final PaymentMethodTranslationRepository paymentMethodTranslationRepository;

    @PostMapping
    public ResponseEntity<ProductTranslationResource> createProductTranslation(@RequestBody ProductTranslationRequest productTranslationRequest) {
        ProductTranslation productTranslation = productTranslationService.create(productTranslationRequest.toProductTranslation());

        URI localtion = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(("/{id}"))
                .build(productTranslation.getId());

        return ResponseEntity.created(localtion).body(productTranslationResourceAssembler.toResource(productTranslation));
    }

    @GetMapping
    public ResponseEntity<List<ProductTranslationResource>> getProductTranslations() {
        return ResponseEntity.ok(productTranslationResourceAssembler.toListResource(productTranslationService.findAll()));
    }

    @GetMapping(path = "/{locale}/{id}")
    public ResponseEntity<ProductTranslationResource> getProductTranslation(@PathVariable("locale") String locale, @PathVariable("id")UUID productId) {
        Optional<ProductTranslation> productTranslation = productTranslationService.findIdOptional(productId, locale);

        if(!productTranslation.isPresent()) {
            Optional<Product> product = productService.findByIdOptional(productId);

            ProductTranslation productTranslationDefault = new ProductTranslation(null, "default", product.get().getTitle(), product.get().getDescription(), product.get());
            return ResponseEntity.ok(productTranslationResourceAssembler.toResource(productTranslationDefault));
        }

        return productTranslation
                .map(productTranslationResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProductTranslationResource> updateProductTranslation(
            @PathVariable("id") Optional<ProductTranslation> productTranslation,
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
    public ResponseEntity<Void> delteProductTranslation(
            @PathVariable("id") Optional<ProductTranslation> productTranslation
    ) {
        if(productTranslation.isPresent()) {
            productTranslationService.delete(productTranslation.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
