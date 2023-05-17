package com.multiLanguageDB.multilanguageapi.paymentMethod;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PaymentMethodRepository extends CrudRepository<PaymentMethod, UUID> {
}
