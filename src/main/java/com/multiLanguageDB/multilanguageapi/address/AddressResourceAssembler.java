package com.multiLanguageDB.multilanguageapi.address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Builder
public class AddressResourceAssembler {

    public AddressResource toResource(Address address) {
        return AddressResource.builder()
                .id(address.getId())
                .street(address.getStreet())
                .house(address.getHouse())
                .zip(address.getZip())
                .city(address.getCity())
                .country(address.getCountry())
                .build();
    }

    public List<AddressResource> toListResource(List<Address> addresses) {
        return addresses.stream().map(this::toResource).collect(Collectors.toList());
    }
}
