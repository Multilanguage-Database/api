package com.multiLanguageDB.multilanguageapi.customer;

import com.multiLanguageDB.multilanguageapi.cart.CartRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CustomerRepository extends CrudRepository<Customer, UUID> {
}
