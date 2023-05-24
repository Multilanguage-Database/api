package com.multiLanguageDB.multilanguageapi.cartProduct;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CartProductService {

    @NonNull
    private final CartProductRepository cartProductRepository;

    public CartProduct create(CartProduct transientEntity) {
        return cartProductRepository.saveAndFlush(transientEntity);
    }

    public List<CartProduct> findAll() {
        return cartProductRepository.findAll();
    }

    public List<Optional<CartProduct>> findByCartId(UUID id) {
        return cartProductRepository.findByCartId(id);
    }

    public CartProduct update(CartProduct entity) {
        return cartProductRepository.saveAndFlush(entity);
    }

    public void delete(CartProduct entity) {
        cartProductRepository.delete(entity);
    }
}
