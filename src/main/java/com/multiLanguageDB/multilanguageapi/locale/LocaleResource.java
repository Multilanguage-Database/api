package com.multiLanguageDB.multilanguageapi.locale;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode(callSuper = false)
@Builder
public class LocaleResource {
    @JsonProperty(value = "_id")
    private final String id;

    String name;
}