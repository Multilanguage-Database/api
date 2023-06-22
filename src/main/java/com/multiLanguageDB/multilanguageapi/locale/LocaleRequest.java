package com.multiLanguageDB.multilanguageapi.locale;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class LocaleRequest {

    String id;

    String name;

    public Locale toLocale() {
        return Locale.builder()
                .id(id)
                .name(name)
                .build();
    }
}