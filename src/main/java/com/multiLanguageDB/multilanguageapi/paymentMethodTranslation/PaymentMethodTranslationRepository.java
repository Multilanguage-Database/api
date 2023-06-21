package com.multiLanguageDB.multilanguageapi.paymentMethodTranslation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentMethodTranslationRepository extends JpaRepository<PaymentMethodTranslation, UUID> {
    Optional<PaymentMethodTranslation> findById(UUID id);

    @Query("SELECT pm FROM PaymentMethodTranslation pm WHERE pm.locale.id = :locale")
    Optional<List<PaymentMethodTranslation>> findAllByLocale(@Param("locale") String locale);

    @Query("SELECT pm FROM PaymentMethodTranslation pm WHERE pm.paymentMethod.id = :payment_id AND pm.locale.id = :locale")
    Optional<PaymentMethodTranslation> findByIdAndLocale(@Param("payment_id") UUID id, @Param("locale") String locale);
}
