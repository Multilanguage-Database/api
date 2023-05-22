package com.multiLanguageDB.multilanguageapi.customer;

import com.multiLanguageDB.multilanguageapi.address.Address;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Setter
public class CustomerAddressRequest {
    String firstName;

    String lastName;

    String email;

    String password;

    String street;

    String house;

    String zip;

    String city;

    String country;

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

    public Address toAddress() {
        return Address.builder()
                .street(street)
                .house(house)
                .zip(zip)
                .city(city)
                .country(country)
                .build();
    }
}
