package com.multiLanguageDB.multilanguageapi.translation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Builder
public class TranslationResourceAssembler {

    public TranslationResource toResource(Translation translation) {
        return TranslationResource.builder()
                .locale(translation.getLocale().getId())
                .translation(translation.getTranslation())
                .build();
    }

    public List<TranslationResource> toListResource(List<Translation> translations) {
        return translations.stream().map(this::toResource).collect(Collectors.toList());
    }
}
