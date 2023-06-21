package com.multiLanguageDB.multilanguageapi.locale;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class LocaleService {

    @NonNull
    private final LocaleRepository localeRepository;

    public Locale create(Locale transientEntity) {
       return localeRepository.saveAndFlush(transientEntity);
    }

    public List<Locale> findAll() {
        return localeRepository.findAll();
    }

    public Optional<Locale> findByIdOptional(String id) {
        return localeRepository.findById(id);
    }

    public void delete(Locale entity) {
        localeRepository.delete(entity);
    }
}
