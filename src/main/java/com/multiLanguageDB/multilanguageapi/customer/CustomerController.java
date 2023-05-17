package com.multiLanguageDB.multilanguageapi.customer;

import lombok.RequiredArgsConstructor;
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

    private final CustomerResourceAssembler customerResourceAssembler;

    @PostMapping
    public ResponseEntity<CustomerResource> createCustomer (@RequestBody CustomerRequest customerRequest) {
        Customer customer = customerService.create(customerRequest.toCustomer());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .build(customer.getId());

        return ResponseEntity.created(location).body(customerResourceAssembler.toResource(customer));
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
            @RequestBody CustomerRequest customerRequest
    ) {
        return customer
                .map(current -> current = customerRequest.toCustomer(customer.get().getId()))
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
