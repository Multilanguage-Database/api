package com.multiLanguageDB.multilanguageapi.customer;

import com.multiLanguageDB.multilanguageapi.address.Address;
import com.multiLanguageDB.multilanguageapi.address.AddressResource;
import com.multiLanguageDB.multilanguageapi.address.AddressResourceAssembler;
import com.multiLanguageDB.multilanguageapi.address.AddressService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    private final AddressService addressService;

    private final AddressResourceAssembler addressResourceAssembler;

    private final CustomerResourceAssembler customerResourceAssembler;

    @PostMapping
    public ResponseEntity<CustomerAddressResponse> createCustomer (@RequestBody CustomerAddressRequest customerAddressRequest) {
        Customer customer = customerAddressRequest.toCustomer();
        Address address = customerAddressRequest.toAddress();
        customer.setAddress(address);
        address.setCustomer(customer);
        customerService.create(customer);
        addressService.create(address);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .build(customer.getId());

        CustomerAddressResponse response = new CustomerAddressResponse(customerResourceAssembler.toResource(customer), addressResourceAssembler.toResource(address));
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<CustomerResource>> getCustomers() {
        return ResponseEntity.ok(customerResourceAssembler.toListResource(customerService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResource> getCustomer(@PathVariable("id")UUID id) {

        return customerService.findByIdOptional(id)
                .map(customerResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<CustomerResource> getCustomer(@RequestParam("email") String email,
                                                        @RequestParam("password") String password) {
        password = String.valueOf(password.hashCode());

        return customerService.findByEmailAndPassword(email, password)
                .map(customerResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //TODO: Frontend -> User will have to reenter password on change
    @PutMapping(path = "/{id}")
    public ResponseEntity<CustomerResource> updateCustomer(
            @PathVariable("id") Optional<Customer> customer,
            @RequestBody CustomerAddressRequest customerAddressRequest
    ) {
        return customer
                .map(current -> current = customerAddressRequest.toCustomer(customer.get().getId()))
                .map(customerService::update)
                .map(customerResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Optional<Customer> customer) {
        if(customer.isPresent()) {
            customerService.delete(customer.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

@AllArgsConstructor
@Getter
@Setter
class CustomerAddressResponse {
    private CustomerResource customer;
    private AddressResource address;
}