package com.multiLanguageDB.multilanguageapi.cart;

import com.multiLanguageDB.multilanguageapi.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {
    Optional<Cart> findById(@Param("id")UUID id);
    Optional<Cart> findByCustomer(Customer customer);

    @Query("SELECT c FROM Cart c WHERE c.customer.id = :customerId")
    Optional<Cart> findByCustomerId(@Param("customerId")UUID customerId);
}
