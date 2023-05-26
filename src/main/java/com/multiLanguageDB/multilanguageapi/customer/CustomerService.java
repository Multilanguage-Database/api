package com.multiLanguageDB.multilanguageapi.customer;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomerService {
    @NonNull
    private final CustomerRepository customerRepository;

    public Customer create(Customer transientEntity) {
        return customerRepository.saveAndFlush(transientEntity);
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findByIdOptional(UUID id) {
        return customerRepository.findById(id);
    }

    public Optional<Customer> findByEmailAndPassword(String email, String password) {
        return customerRepository.findByEmailAndPassword(email, password);
    }

    public Optional<Customer>findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public Customer update(Customer entity) {
        return customerRepository.saveAndFlush(entity);
    }

    public void delete(Customer entity) {
        customerRepository.delete(entity);
    }
}
