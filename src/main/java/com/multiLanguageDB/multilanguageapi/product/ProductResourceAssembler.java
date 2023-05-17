package com.multiLanguageDB.multilanguageapi.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Builder
public class ProductResourceAssembler {

    public ProductResource toResource(Product product) {
        return ProductResource.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .build();
    }

    public List<ProductResource> toListResource(List<Product> products) {
        return products.stream().map(this::toResource).collect(Collectors.toList());
    }
}
