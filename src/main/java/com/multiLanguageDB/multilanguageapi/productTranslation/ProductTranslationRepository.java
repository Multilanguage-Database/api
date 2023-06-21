package com.multiLanguageDB.multilanguageapi.productTranslation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductTranslationRepository extends JpaRepository<ProductTranslation, UUID> {
    Optional<ProductTranslation> findById(UUID id);

    @Query("SELECT p FROM ProductTranslation p WHERE p.product.id = :product_id AND p.locale = :locale")
    Optional<ProductTranslation> findByProductAndLocale(@Param("product_id")UUID productId, @Param("locale") String locale);
}
