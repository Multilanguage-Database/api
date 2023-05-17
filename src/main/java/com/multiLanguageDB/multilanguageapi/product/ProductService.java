package com.multiLanguageDB.multilanguageapi.product;

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
public class ProductService {

    @NonNull
    private final ProductRepository productRepository;

    public Product create(Product transientEntity) {
        return productRepository.saveAndFlush(transientEntity);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findByIdOptional(UUID id) {
        return productRepository.findById(id);
    }

    public Product update(Product entity) {
        return productRepository.saveAndFlush(entity);
    }

    public void delete(Product entity) {
        productRepository.delete(entity);
    }
}
