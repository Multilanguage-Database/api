package com.multiLanguageDB.multilanguageapi.productTranslation;

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
public class ProductTranslationService {

    @NonNull
    private final ProductTranslationRepository productTranslationRepository;

    public ProductTranslation create(ProductTranslation transientEntity) {
        return productTranslationRepository.saveAndFlush(transientEntity);
    }

    public List<ProductTranslation> findAll() {
        return productTranslationRepository.findAll();
    }

    public Optional<List<ProductTranslation>> findAllByLocale(String id) {
        return productTranslationRepository.findAllByLocale(id);
    }

    public Optional<ProductTranslation> findByIdAndLocale(UUID id, String locale) {
        return productTranslationRepository.findByIdAndLocale(id, locale);
    }

    public ProductTranslation update(ProductTranslation entity) {
        return productTranslationRepository.saveAndFlush(entity);
    }

    public void delete(ProductTranslation entity) {
        productTranslationRepository.delete(entity);
    }
}