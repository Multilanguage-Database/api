package com.multiLanguageDB.multilanguageapi.productTranslation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Builder
public class ProductTranslationResourceAssembler {

    public ProductTranslationResource toResource(ProductTranslation productTranslation) {
        return ProductTranslationResource.builder()
                .id(productTranslation.getId())
                .locale(productTranslation.getLocale())
                .title(productTranslation.getTitle())
                .description(productTranslation.getDescription())
                .build();
    }

    public List<ProductTranslationResource> toListResource(List<ProductTranslation> productTranslations) {
        return productTranslations.stream().map(this::toResource).collect(Collectors.toList());
    }
}
