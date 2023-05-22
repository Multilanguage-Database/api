package com.multiLanguageDB.multilanguageapi.customer;

import com.multiLanguageDB.multilanguageapi.address.AddressService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Builder
public class CustomerResourceAssembler {

    private final AddressService addressService;

    public CustomerResource toResource(Customer customer) {
        return CustomerResource.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .build();
    }
    
    public List<CustomerResource> toListResource(List<Customer> customers) {
        return customers.stream().map(this::toResource).collect(Collectors.toList());
    }
}
