package com.multiLanguageDB.multilanguageapi.cartProduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, UUID> {
    @Query("SELECT cp FROM CartProduct cp WHERE cp.cart.id = :cartId")
    List<Optional<CartProduct>> findByCartId(@Param("cartId") UUID cartId);
}
