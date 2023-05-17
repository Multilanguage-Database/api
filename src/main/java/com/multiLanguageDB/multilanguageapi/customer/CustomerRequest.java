package com.multiLanguageDB.multilanguageapi.customer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Setter
public class CustomerRequest {
    String firstName;

    String lastName;

    String email;

    String password;

    public Customer toCustomer() {
        return Customer.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(String.valueOf(password.hashCode()))
                .build();
    }

    public Customer toCustomer(UUID id) {
        return Customer.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(String.valueOf(password.hashCode()))
                .build();
    }
}
