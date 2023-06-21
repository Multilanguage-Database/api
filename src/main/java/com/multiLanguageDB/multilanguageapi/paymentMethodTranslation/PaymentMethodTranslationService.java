package com.multiLanguageDB.multilanguageapi.paymentMethodTranslation;

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
public class PaymentMethodTranslationService {

    @NonNull
    private final PaymentMethodTranslationRepository paymentMethodTranslationRepository;

    public PaymentMethodTranslation create(PaymentMethodTranslation transientEntity) {
        return paymentMethodTranslationRepository.saveAndFlush(transientEntity);
    }

    public List<PaymentMethodTranslation> findAll() {
        return paymentMethodTranslationRepository.findAll();
    }

    public Optional<List<PaymentMethodTranslation>> findAllByLocale(String id) {
        return paymentMethodTranslationRepository.findAllByLocale(id);
    }

    public Optional<PaymentMethodTranslation> findByIdAndLocale(UUID id, String locale) {
        return paymentMethodTranslationRepository.findByIdAndLocale(id, locale);
    }

    public PaymentMethodTranslation update(PaymentMethodTranslation entity) {
        return paymentMethodTranslationRepository.saveAndFlush(entity);
    }

    public void delete(PaymentMethodTranslation entity) {
        paymentMethodTranslationRepository.delete(entity);
    }
}