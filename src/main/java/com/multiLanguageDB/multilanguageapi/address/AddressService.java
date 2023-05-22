package com.multiLanguageDB.multilanguageapi.address;

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
public class AddressService {

    @NonNull
    private final AddressRepository addressRepository;

    public Address create(Address transientEntity) {
        return addressRepository.saveAndFlush(transientEntity);
    }

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public Optional<Address> findByIdOptional(UUID id) {
        return addressRepository.findByCustomerId(id);
    }

    public Address update(Address entity) {
        return addressRepository.saveAndFlush(entity);
    }

    public void delete(Address entity) {
        addressRepository.delete(entity);
    }
}
