package com.multiLanguageDB.multilanguageapi.address;

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
public class AddressResource {
    @JsonProperty(value = "_id")
    private final UUID id;

    String street;

    String house;

    String zip;

    String city;

    String country;
}
