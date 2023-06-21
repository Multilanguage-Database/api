package com.multiLanguageDB.multilanguageapi.paymentMethodTranslation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentMethodTranslationRepository extends JpaRepository<PaymentMethodTranslation, UUID> {
    Optional<PaymentMethodTranslation> findById(UUID id);
}
