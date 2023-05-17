package com.multiLanguageDB.multilanguageapi.paymentMethod;

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
public class PaymentMethodService {
    @NonNull
    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethod create(PaymentMethod transientEntity) {
        return paymentMethodRepository.saveAndFlush(transientEntity);
    }

    public List<PaymentMethod> findAll() {
        return paymentMethodRepository.findAll();
    }

    public Optional<PaymentMethod> findByIdOptional(UUID id) {
        return paymentMethodRepository.findById(id);
    }

    public PaymentMethod update(PaymentMethod entity) {
        return paymentMethodRepository.saveAndFlush(entity);
    }

    public void delete(PaymentMethod entity) {
        paymentMethodRepository.delete(entity);
    }
}
