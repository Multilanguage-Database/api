package com.multiLanguageDB.multilanguageapi.translation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@EqualsAndHashCode(callSuper = false)
@Builder
public class TranslationResource {
    @JsonProperty(value = "_id")
    private final UUID id;

    String locale;

    String translation;
}
