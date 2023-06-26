package com.multiLanguageDB.multilanguageapi.product;

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
@RequestMapping(path = "/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final TextContentService textContentService;

    private final TranslationService translationService;

    private final ProductResourceAssembler productResourceAssembler;

    @PostMapping
    public ResponseEntity<ProductResource> createProduct(@RequestBody ProductRequest productRequest) {
        TextContent title = textContentService.create(productRequest.toTextContent(productRequest.getTitle()));
        TextContent description = textContentService.create(productRequest.toTextContent(productRequest.getDescription()));
        Product product = productService.create(productRequest.toProduct(title, description));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id")
                .build(product.getId());

        return ResponseEntity.created(location).body(productResourceAssembler.toResource(product));
    }

    @PostMapping(path = "/{id}")
    public ResponseEntity<ProductResource> createProductTranslation(@RequestBody ProductRequest productRequest, @PathVariable("id") UUID id) {
        Optional<Product> product = productService.findByIdOptional(id);

        Translation title = new Translation();

        title.setLocale(productRequest.getLocale());
        title.setTranslation(productRequest.getTitle());
        title.setTextContent(product.get().getTitle());

        translationService.create(title);

        Translation description = new Translation();

        description.setLocale(productRequest.getLocale());
        description.setTranslation(productRequest.getDescription());
        description.setTextContent(product.get().getDescription());

        translationService.create(description);

        ProductResource productResource = productResourceAssembler.toResource(product.get());
        productResource.setLocale(productRequest.getLocale().getId());
        productResource.setTitle(title.getTranslation());
        productResource.setDescription(description.getTranslation());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .build(product.get().getId());

        return ResponseEntity.created(location).body(productResource);
    }

    @GetMapping(path = "/{locale}/{id}")
    public ResponseEntity<ProductResource> getProductTranslation(@PathVariable("locale") String locale, @PathVariable("id") UUID id) {
        Optional<Product> product = productService.findByIdOptional(id);

        ProductResource productResource = productResourceAssembler.toResource(product.get());
        Translation title = translationService.findTranslationByLocaleAndTextContentId(locale, product.get().getTitle().getId()).get();
        Translation description = translationService.findTranslationByLocaleAndTextContentId(locale, product.get().getDescription().getId()).get();

        productResource.setTitle(title.getTranslation());
        productResource.setDescription(description.getTranslation());
        productResource.setLocale(locale);

        return ResponseEntity.ok().body(productResource);
    }

    @GetMapping
    public ResponseEntity<List<ProductResource>> getProducts() {
        return ResponseEntity.ok(productResourceAssembler.toListResource(productService.findAll()));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductResource> getProduct(@PathVariable("id") UUID id) {
        Optional<Product> product = productService.findByIdOptional(id);

        return productService.findByIdOptional(id)
                .map(productResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProductResource> updateProduct(
            @PathVariable("id") Optional<Product> product,
            @RequestBody ProductRequest productRequest
    ) {
        return product
                .map(current -> current = productRequest.toProduct(product.get().getId(), product.get().getTitle(), product.get().getDescription()))
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
