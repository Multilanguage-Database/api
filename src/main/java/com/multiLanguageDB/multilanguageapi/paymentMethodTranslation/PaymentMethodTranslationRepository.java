package com.multiLanguageDB.multilanguageapi.paymentMethodTranslation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentMethodTranslationRepository extends JpaRepository<PaymentMethodTranslation, UUID> {
    Optional<PaymentMethodTranslation> findById(UUID id);

    @Query("SELECT pm FROM PaymentMethodTranslation pm WHERE pm.paymentMethod.id = :payment_id AND pm.locale = :locale")
    Optional<PaymentMethodTranslation> findByPaymentAndlocale(@Param("payment_id") UUID paymentId, @Param("locale") String locale);
}
