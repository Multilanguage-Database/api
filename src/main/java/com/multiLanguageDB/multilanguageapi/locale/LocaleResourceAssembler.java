package com.multiLanguageDB.multilanguageapi.locale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Builder
public class LocaleResourceAssembler {

    public LocaleResource toResource(Locale locale) {
        return LocaleResource.builder()
                .id(locale.getId())
                .name(locale.getName())
                .build();
    }

    public List<LocaleResource> toListResource(List<Locale> locales) {
        return locales.stream().map(this::toResource).collect(Collectors.toList());
    }
}
