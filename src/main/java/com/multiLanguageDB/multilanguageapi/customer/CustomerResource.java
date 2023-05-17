package com.multiLanguageDB.multilanguageapi.customer;

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
public class CustomerResource {
    @JsonProperty(value = "_id")
    private final UUID id;

    String firstName;

    String lastName;

    String email;

    String password;
}
