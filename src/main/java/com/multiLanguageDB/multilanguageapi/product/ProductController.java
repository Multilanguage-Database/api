package com.multiLanguageDB.multilanguageapi.product;

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

    private final ProductResourceAssembler productResourceAssembler;

    @PostMapping
    public ResponseEntity<ProductResource> createProduct(@RequestBody ProductRequest productRequest) {
        Product product = productService.create(productRequest.toProduct());
        //title,locale -> Create Text Content productRequest.getLocale()
        //desc,locale -> Create Text Content


        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id")
                .build(product.getId());

        return ResponseEntity.created(location).body(productResourceAssembler.toResource(product));
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
