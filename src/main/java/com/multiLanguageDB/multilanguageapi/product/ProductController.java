package com.multiLanguageDB.multilanguageapi.product;

import com.multiLanguageDB.multilanguageapi.productTranslation.ProductTranslationResourceAssembler;
import com.multiLanguageDB.multilanguageapi.productTranslation.ProductTranslation;
import com.multiLanguageDB.multilanguageapi.productTranslation.ProductTranslationResource;
import com.multiLanguageDB.multilanguageapi.productTranslation.ProductTranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final ProductTranslationService productTranslationService;

    private final ProductResourceAssembler productResourceAssembler;

    private final ProductTranslationResourceAssembler productTranslationResourceAssembler;

    @PostMapping
    public ResponseEntity<ProductTranslationResource> createProduct(@RequestBody ProductRequest productRequest) {
        Product product = productRequest.toProduct();
        ProductTranslation productTranslation = productRequest.toProductTranslation();
        productTranslation.setProduct(product);
        productService.create(product);
        productTranslationService.create(productTranslation);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .build(product.getId());

        return ResponseEntity.created(location).body(productTranslationResourceAssembler.toResource(productTranslation));
    }

    @GetMapping
    public ResponseEntity<List<ProductResource>> getProducts() {
        return ResponseEntity.ok(productResourceAssembler.toListResource(productService.findAll()));
    }

    @GetMapping(path = "/locale/{locale}")
    public ResponseEntity<List<ProductTranslationResource>> getProductsByLocale(@PathVariable("locale") String locale) {
        Optional<List<ProductTranslation>> productTranslations = productTranslationService.findAllByLocale(locale);

        return ResponseEntity.ok(productTranslationResourceAssembler.toListResource(productTranslations.get()));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductResource> getProduct(@PathVariable("id") UUID id) {
        Optional<Product> product = productService.findByIdOptional(id);

        return productService.findByIdOptional(id)
                .map(productResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/one/{id}/{locale}")
    public ResponseEntity<ProductTranslationResource> getProduct(@PathVariable("id") UUID id, @PathVariable("locale") String locale) {

        return productTranslationService.findByIdAndLocale(id, locale)
                .map(productTranslationResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProductResource> updateProduct(
            @PathVariable("id") Optional<Product> product,
            @RequestBody ProductRequest productRequest
    ) {
        return product
                .map(current -> current = productRequest.toProduct(product.get().getId()))
                .map(productService::update)
                .map(productResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Optional<Product> product) {
        if(product.isPresent()) {
            productService.delete(product.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
