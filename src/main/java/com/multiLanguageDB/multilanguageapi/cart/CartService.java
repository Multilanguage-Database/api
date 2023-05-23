package com.multiLanguageDB.multilanguageapi.cart;

import com.multiLanguageDB.multilanguageapi.customer.Customer;
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
public class CartService {

    @NonNull
    private final CartRepository cartRepository;

    public Cart create(Cart transientEntity) {
        return cartRepository.saveAndFlush(transientEntity);
    }

    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    public Optional<Cart> findByIdOptional(UUID id) {
        return cartRepository.findById(id);
    }

    public Optional<Cart> findByCustomer(Customer customer) {
        return cartRepository.findByCustomer(customer);
    }

    public Optional<Cart> findByCustomerId(UUID id) {
        return cartRepository.findByCustomerId(id);
    }

    public Cart update (Cart enitity) {
        return cartRepository.saveAndFlush(enitity);
    }

    public void delete(Cart entity) {
        cartRepository.delete(entity);
    }
}
